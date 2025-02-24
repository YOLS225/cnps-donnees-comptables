package CNPS.DONNEES_COMPTABLES.business_logic;

import CNPS.DONNEES_COMPTABLES.business_logic.feature.ILeader;
import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.LeaderDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.*;
import CNPS.DONNEES_COMPTABLES.jpa.repository.CompanyRepository;
import CNPS.DONNEES_COMPTABLES.jpa.repository.LeaderRepository;
import CNPS.DONNEES_COMPTABLES.jpa.repository.StatusRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LeaderService implements ILeader {
    private final CompanyRepository companyRepository;
    private final LeaderRepository leaderRepository;
    private final StatusRepository statusRepository;

    public LeaderService(CompanyRepository companyRepository,
                         LeaderRepository leaderRepository,
                         StatusRepository statusRepository){
        this.companyRepository=companyRepository;
        this.leaderRepository=leaderRepository;
        this.statusRepository=statusRepository;
    }


    @Override
    public Action<Leader> saveLeader(LeaderDTO leaderDTO) {
        try {
            Optional<Company> recoveredCompany = companyRepository.findById(leaderDTO.companyId());
            Optional<Status> recoveredStatus=statusRepository.findStatusByLabel("ACTIVE");
            if (recoveredCompany.isPresent() && recoveredStatus.isPresent()){
                Company company = recoveredCompany.get();
                Status status=recoveredStatus.get();
                Leader leader = Leader.builder()
                        .firstName(leaderDTO.firstName())
                        .lastName(leaderDTO.lastName())
                        .function(leaderDTO.function())
                        .address(leaderDTO.adress())
                        .function(leaderDTO.function())
                        .nationality(leaderDTO.nationality())
                        .fiscalId(leaderDTO.fiscalId())
                        .status(status)
                        .company(company)
                        .build();
                return Action.success("Leader:created",leaderRepository.save(leader));}
            else {
                throw new RuntimeException("Company not found with id: " + leaderDTO.companyId());
            }
        }catch (Exception error){
            System.out.println(error.getMessage());
            return Action.fail("erreur : "+error.getMessage());
        }
    }

    @Override
    public List<Leader> findAllLeaders() {
        List<Leader> result = leaderRepository.findAll();
        return result;
    }

    @Override
    public List<Leader> filterLeaders(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return leaderRepository.findAll();
        }
        String likePattern = "%" + searchTerm + "%";
        return leaderRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.like(root.get("lastName"), likePattern));
            predicates.add(cb.like(root.get("firstName"), likePattern));
            predicates.add(cb.like(root.get("function"), likePattern));
            predicates.add(cb.like(root.get("fiscalId"), likePattern));
            predicates.add(cb.like(root.get("percentage"), likePattern));
            predicates.add(cb.like(root.get("adress"), likePattern));
            predicates.add(cb.like(root.get("nationality"), likePattern));
            return cb.or(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    public Action<Leader> updateLeader(UUID leaderId, LeaderDTO leaderDTO) {
        try {
            Optional<Leader> recoveredLeader=leaderRepository.findById(leaderId);
            if (recoveredLeader.isPresent()){
                Leader leader = recoveredLeader.get();
                leader.setFirstName(leaderDTO.firstName());
                leader.setLastName(leaderDTO.lastName());
                leader.setFunction(leaderDTO.function());
                leader.setAddress(leaderDTO.adress());
                leader.setNationality(leaderDTO.nationality());
                return Action.success("Leader updated successfully", leaderRepository.save(leader));
            }
            else {
                throw new RuntimeException("Leader not found with id: " + leaderId);
            }
        }catch (Exception error){
            System.out.println(error.getMessage());
            return Action.fail("Erreur : " + error.getMessage());
        }
    }

    @Override
    public Action<Leader> deleteLeader(UUID leaderId) {
        try {
            Optional<Leader> recoveredLeader=leaderRepository.findById(leaderId);
            Optional<Status> recoveredStatus=statusRepository.findStatusByLabel("INACTIVE");
            if (recoveredLeader.isPresent() && recoveredStatus.isPresent()){
                Leader leader = recoveredLeader.get();
                Status status = recoveredStatus.get();
                leader.setStatus(status);
                return Action.success("Leader deleted successfully", leaderRepository.save(leader));
            }
            else {
                throw new RuntimeException("Leader not found with id: " + leaderId);
            }
        }catch (Exception error){
            System.out.println(error.getMessage());
            return Action.fail("Erreur : " + error.getMessage());
        }
    }
}
