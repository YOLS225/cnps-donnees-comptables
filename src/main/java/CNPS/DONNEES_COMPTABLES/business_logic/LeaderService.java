package CNPS.DONNEES_COMPTABLES.business_logic;

import CNPS.DONNEES_COMPTABLES.business_logic.feature.ILeader;
import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.LeaderDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Activity;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Company;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Leader;
import CNPS.DONNEES_COMPTABLES.jpa.repository.CompanyRepository;
import CNPS.DONNEES_COMPTABLES.jpa.repository.LeaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaderService implements ILeader {
    private final CompanyRepository companyRepository;
    private final LeaderRepository leaderRepository;

    public LeaderService(CompanyRepository companyRepository,
                         LeaderRepository leaderRepository){
        this.companyRepository=companyRepository;
        this.leaderRepository=leaderRepository;
    }


    @Override
    public Action<Leader> saveLeader(LeaderDTO leaderDTO) {
        try {
            Optional<Company> company = companyRepository.findById(leaderDTO.companyId());
            if (company.isPresent()){
                Company company1 = company.get();
                Leader leader = Leader.builder()
                        .firstName(leaderDTO.firstName())
                        .lastName(leaderDTO.lastName())
                        .function(leaderDTO.function())
                        .address(leaderDTO.adress())
                        .function(leaderDTO.function())
                        .nationality(leaderDTO.nationality())
                        .fiscalId(leaderDTO.fiscalId())
                        .company(company1)
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
}
