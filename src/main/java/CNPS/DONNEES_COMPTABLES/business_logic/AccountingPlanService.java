package CNPS.DONNEES_COMPTABLES.business_logic;

import CNPS.DONNEES_COMPTABLES.business_logic.feature.IAccountingPlan;
import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.jpa.entity.AccountingPlan;
import CNPS.DONNEES_COMPTABLES.jpa.repository.AccountClassRepository;
import CNPS.DONNEES_COMPTABLES.jpa.repository.AccoutingPlanRepository;
import jakarta.persistence.criteria.Predicate;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountingPlanService implements IAccountingPlan {
    private final AccoutingPlanRepository accoutingPlanRepository;
    private final AccountClassRepository accountClassRepository;
    public AccountingPlanService(AccoutingPlanRepository accoutingPlanRepository,
                                 AccountClassRepository accountClassRepository){
        this.accoutingPlanRepository=accoutingPlanRepository;
        this.accountClassRepository=accountClassRepository;
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


    public List<Map<String, Object>> sortAccountingPlan() {
        List<List<String>> rawParents = accountClassRepository.findDistinctParent();
        List<AccountingPlan> recoveredAccountingPlan = accoutingPlanRepository.findAll();

        // Utiliser LinkedHashMap pour conserver l'ordre
        Map<String, Map<String, Object>> nodeMap = new LinkedHashMap<>();
        Set<String> childReferences = new HashSet<>();

        // Trier les parents par référence
        rawParents.sort(Comparator.comparing(row -> row.get(0)));

        for (List<String> row : rawParents) {
            String reference = row.get(0);
            String label = row.get(1);
            String parent = row.size() > 2 ? row.get(2) : null;

            Map<String, Object> node = nodeMap.computeIfAbsent(reference, k -> new LinkedHashMap<>());
            node.put("reference", reference);
            node.put("label", label);
            node.putIfAbsent("children", new ArrayList<Map<String, Object>>());
            node.putIfAbsent("accounts", new ArrayList<Map<String, Object>>());

            if (parent != null && !parent.isEmpty()) {
                Map<String, Object> parentNode = nodeMap.computeIfAbsent(parent, k -> new LinkedHashMap<>());
                List<Map<String, Object>> children = (List<Map<String, Object>>) parentNode.computeIfAbsent("children", k -> new ArrayList<>());
                children.add(node);
                childReferences.add(reference);
            }
        }

        // Trier les comptes récupérés
        recoveredAccountingPlan.sort(Comparator.comparing(AccountingPlan::getNumberAccount));

        for (AccountingPlan account : recoveredAccountingPlan) {
            String numberAccount = account.getNumberAccount();
            String bestMatchReference = null;

            for (String reference : nodeMap.keySet()) {
                if (numberAccount.startsWith(reference) && (bestMatchReference == null || reference.length() > bestMatchReference.length())) {
                    bestMatchReference = reference;
                }
            }

            if (bestMatchReference != null) {
                Map<String, Object> parentNode = nodeMap.get(bestMatchReference);
                List<Map<String, Object>> accounts = (List<Map<String, Object>>) parentNode.get("accounts");

                Map<String, Object> accountNode = new LinkedHashMap<>();
                accountNode.put("numberAccount", account.getNumberAccount());
                accountNode.put("description", account.getDescription());
                accountNode.put("type", account.getType());

                accounts.add(accountNode);
            }
        }

        // Trier les enfants de chaque nœud par référence
        for (Map<String, Object> node : nodeMap.values()) {
            List<Map<String, Object>> children = (List<Map<String, Object>>) node.get("children");
            children.sort(Comparator.comparing(child -> (String) child.get("reference"), Comparator.nullsLast(String::compareTo)));
        }

        // Retourner uniquement les nœuds parents
        return nodeMap.entrySet()
                .stream()
                .filter(entry -> !childReferences.contains(entry.getKey()) &&
                        (!((List<?>) entry.getValue().get("children")).isEmpty() ||
                                !((List<?>) entry.getValue().get("accounts")).isEmpty()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }










}
