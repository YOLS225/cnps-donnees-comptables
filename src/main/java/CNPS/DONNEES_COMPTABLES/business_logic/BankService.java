package CNPS.DONNEES_COMPTABLES.business_logic;

import CNPS.DONNEES_COMPTABLES.business_logic.feature.IBank;
import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.BankDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Bank;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Company;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Status;
import CNPS.DONNEES_COMPTABLES.jpa.repository.BankRepository;
import CNPS.DONNEES_COMPTABLES.jpa.repository.CompanyRepository;

import CNPS.DONNEES_COMPTABLES.jpa.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.*;

import jakarta.persistence.criteria.Predicate;


@Service
public class BankService implements IBank {

    private  final BankRepository bankRepository;
    private final CompanyRepository companyRepository;
    private final StatusRepository statusRepository;

    public BankService(BankRepository bankRepository,
                       CompanyRepository companyRepository,
                       StatusRepository statusRepository){
        this.bankRepository=bankRepository;
        this.companyRepository=companyRepository;
        this.statusRepository=statusRepository;
    }


    @Override
    public Action<Bank> saveBank(BankDTO bankDTO) {

        try {
            Optional<Company> recoveredCompany = companyRepository.findById(bankDTO.companyId());
            Optional<Status> recoveredStatus=statusRepository.findStatusByLabel(bankDTO.statusLabel());
            if (recoveredCompany.isPresent() && recoveredStatus.isPresent()){
                Company company = recoveredCompany.get();
                Status status=recoveredStatus.get();
            Bank bank = Bank.builder()
                    .name(bankDTO.name())
                    .code(bankDTO.code())
                    .windowsCode(bankDTO.windowsCode())
                    .accountNumber(bankDTO.accountNumber())
                    .keyRib(bankDTO.keyRib())
                    .iban(bankDTO.iban())
                    .status(status)
                    .company(company)
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

    @Override
    public Action<Bank> updateBank(UUID bankId, BankDTO bankDTO) {
        try {
            Optional<Bank> recoveredBank = bankRepository.findById(bankId);
            if (recoveredBank.isPresent()) {
                Bank bank = recoveredBank.get();
                bank.setName(bankDTO.name());
                bank.setCode(bankDTO.code());
                bank.setWindowsCode(bankDTO.windowsCode());
                bank.setAccountNumber(bankDTO.accountNumber());
                bank.setKeyRib(bankDTO.keyRib());
                bank.setIban(bankDTO.iban());

                return Action.success("Bank updated successfully", bankRepository.save(bank));
            } else {
                throw new RuntimeException("Bank not found with id: " + bankId);
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return Action.fail("Erreur : " + error.getMessage());
        }
    }

    @Override
    public Action<Bank> deleteBank(UUID bankId) {
        try {
            Optional<Bank> recoveredBank = bankRepository.findById(bankId);
            Optional<Status> recoveredStatus=statusRepository.findStatusByLabel("INACTIVE");
            if (recoveredBank.isPresent() && recoveredStatus.isPresent()) {
                Status status = recoveredStatus.get();
                Bank bank = recoveredBank.get();
                bank.setStatus(status);
                return Action.success("Bank updated successfully", bankRepository.save(bank));
            } else {
                throw new RuntimeException("Bank not found with id: " + bankId);
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return Action.fail("Erreur : " + error.getMessage());
        }
    }
}
