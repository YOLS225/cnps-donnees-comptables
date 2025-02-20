package CNPS.DONNEES_COMPTABLES.business_logic;

import CNPS.DONNEES_COMPTABLES.business_logic.feature.IAccountClass;
import CNPS.DONNEES_COMPTABLES.jpa.entity.AccountClass;
import CNPS.DONNEES_COMPTABLES.jpa.repository.AccountClassRepository;
import org.springframework.stereotype.Service;

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
public class AccountClassService implements IAccountClass {
    private final AccountClassRepository accountClassRepository;
    public AccountClassService(AccountClassRepository accountClassRepository){
        this.accountClassRepository=accountClassRepository;
    }

    @Override
    public Action<String> loadAccountClass(MultipartFile file) {
        List<AccountClass> accountClasses = new ArrayList<>();
        try (Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                AccountClass classes = new AccountClass();
                classes.setReference(csvRecord.get(0)); // Numéro de compte
                classes.setLabel(csvRecord.get(1)); // Description
                classes.setParent(csvRecord.get(2)); // Sens (C ou D)
                accountClasses.add(classes);
            }
            // Sauvegarde en base
            accountClassRepository.saveAll(accountClasses);

            return Action.success("File loaded and data saved successfully");
        } catch (IOException e) {
            return Action.fail("Error loading file: " + e.getMessage(), null);
        }
    }

}

