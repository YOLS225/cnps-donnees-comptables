package CNPS.DONNEES_COMPTABLES.business_logic;

import CNPS.DONNEES_COMPTABLES.business_logic.feature.IBank;
import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.BankDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Bank;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Company;
import CNPS.DONNEES_COMPTABLES.jpa.repository.BankRepository;
import CNPS.DONNEES_COMPTABLES.jpa.repository.CompanyRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.persistence.criteria.Predicate;


@Service
public class BankService implements IBank {

    private  final BankRepository bankRepository;
    private final CompanyRepository companyRepository;

    public BankService(BankRepository bankRepository,
                       CompanyRepository companyRepository){
        this.bankRepository=bankRepository;
        this.companyRepository=companyRepository;
    }


    @Override
    public Action<Bank> saveBank(BankDTO bankDTO) {

        try {
            Optional<Company> company = companyRepository.findById(bankDTO.companyId());
            if (company.isPresent()){
                Company company1 = company.get();
            Bank bank = Bank.builder()
                    .name(bankDTO.name())
                    .code(bankDTO.code())
                    .windowsCode(bankDTO.windowsCode())
                    .accountNumber(bankDTO.accountNumber())
                    .keyRib(bankDTO.keyRib())
                    .iban(bankDTO.iban())
                    .company(company1)
                    .build();
            return Action.success("Bank:created",bankRepository.save(bank));}
            else {
                throw new RuntimeException("Company not found with id: " + bankDTO.companyId());
            }
        }catch (Exception error){
            System.out.println(error.getMessage());
            return Action.fail("erreur : "+error.getMessage());
        }
    }

    @Override
    public List<Bank> findAllBanks() {
        List<Bank> result = bankRepository.findAll();
        return result;
    }

    @Override
    public List<Bank> filterBanks(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return bankRepository.findAll();
        }
        String likePattern = "%" + searchTerm + "%";
        return bankRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.like(root.get("name"), likePattern));
            predicates.add(cb.like(root.get("accountNumber"), likePattern));
            predicates.add(cb.like(root.get("code"), likePattern));
            predicates.add(cb.like(root.get("iban"), likePattern));
            predicates.add(cb.like(root.get("keyRib"), likePattern));
            predicates.add(cb.like(root.get("windowsCode"), likePattern));

            return cb.or(predicates.toArray(new Predicate[0]));
        });
    }
}
