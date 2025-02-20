package CNPS.DONNEES_COMPTABLES.business_logic;

import CNPS.DONNEES_COMPTABLES.business_logic.feature.IAccountingPlan;
import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.jpa.entity.AccountingPlan;
import CNPS.DONNEES_COMPTABLES.jpa.repository.AccoutingPlanRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountingPlanService implements IAccountingPlan {
    private final AccoutingPlanRepository accoutingPlanRepository;
    public AccountingPlanService(AccoutingPlanRepository accoutingPlanRepository){
        this.accoutingPlanRepository=accoutingPlanRepository;
    }

    @Override
    public Action<String> loadAccountingPlan(MultipartFile file) {
        List<AccountingPlan> accountingPlans = new ArrayList<>();
        try (Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.ISO_8859_1);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                AccountingPlan plan = new AccountingPlan();
                plan.setNumberAccount(csvRecord.get(0)); // Numéro de compte
                plan.setDescription(csvRecord.get(1)); // Description
                plan.setType(csvRecord.get(2)); // Sens (C ou D)
                accountingPlans.add(plan);
            }
            // Sauvegarde en base
            accoutingPlanRepository.saveAll(accountingPlans);

            return Action.success("File loaded and data saved successfully");
        } catch (IOException e) {
            return Action.fail("Error loading file: " + e.getMessage(), null);
        }
    }
}
