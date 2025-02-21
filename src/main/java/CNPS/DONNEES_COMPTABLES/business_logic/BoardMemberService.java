package CNPS.DONNEES_COMPTABLES.business_logic;

import CNPS.DONNEES_COMPTABLES.business_logic.feature.IBoardMember;
import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.BoardMemberDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Activity;
import CNPS.DONNEES_COMPTABLES.jpa.entity.BoardMember;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Company;
import CNPS.DONNEES_COMPTABLES.jpa.repository.ActivityRepository;
import CNPS.DONNEES_COMPTABLES.jpa.repository.BoardMemberRepository;
import CNPS.DONNEES_COMPTABLES.jpa.repository.CompanyRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class BoardMemberService  implements IBoardMember {
    private final CompanyRepository companyRepository;
    private final BoardMemberRepository boardMemberRepository;

    public BoardMemberService(CompanyRepository companyRepository,
                       BoardMemberRepository boardMemberRepository){
        this.companyRepository=companyRepository;
        this.boardMemberRepository=boardMemberRepository;
    }
    @Override
    public Action<BoardMember> saveBoardMember(BoardMemberDTO boardMemberDTO) {
        try {
            Optional<Company> company = companyRepository.findById(boardMemberDTO.companyId());
            if (company.isPresent()){
                Company company1 = company.get();
                BoardMember boardMember = BoardMember.builder()
                        .firstName(boardMemberDTO.firstName())
                        .lastName(boardMemberDTO.lastName())
                        .structure(boardMemberDTO.structure())
                        .function(boardMemberDTO.function())
                        .address(boardMemberDTO.adress())
                        .nationality(boardMemberDTO.nationality())
                        .percentage(boardMemberDTO.percentage())
                        .company(company1)
                        .build();
                return Action.success("BoardMember:created",boardMemberRepository.save(boardMember));}
            else {
                throw new RuntimeException("Company not found with id: " + boardMemberDTO.companyId());
            }
        }catch (Exception error){
            System.out.println(error.getMessage());
            return Action.fail("erreur : "+error.getMessage());
        }
    }

    @Override
    public List<BoardMember> findAllBoardMembers() {
        List<BoardMember> result = boardMemberRepository.findAll();
        return result;
    }

    @Override
    public List<BoardMember> filterBoardMembers(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return boardMemberRepository.findAll();
        }
        String likePattern = "%" + searchTerm + "%";
        return boardMemberRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.like(root.get("lastName"), likePattern));
            predicates.add(cb.like(root.get("firstName"), likePattern));
            predicates.add(cb.like(root.get("function"), likePattern));
            predicates.add(cb.like(root.get("structure"), likePattern));
            predicates.add(cb.like(root.get("percentage"), likePattern));
            predicates.add(cb.like(root.get("adress"), likePattern));
            predicates.add(cb.like(root.get("nationality"), likePattern));
            return cb.or(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    public Action<BoardMember> updateBoardMember(UUID boardMemberId, BoardMemberDTO boardMemberDTO) {
        try {
            Optional<BoardMember> recoveredBoardMember=boardMemberRepository.findById(boardMemberId);
            if (recoveredBoardMember.isPresent()){
                BoardMember boardMember = recoveredBoardMember.get();
                boardMember.setFirstName(boardMemberDTO.firstName());
                boardMember.setLastName(boardMemberDTO.lastName());
                boardMember.setFunction(boardMemberDTO.function());
                boardMember.setAddress(boardMemberDTO.adress());
                boardMember.setStructure(boardMemberDTO.structure());
                boardMember.setNationality(boardMemberDTO.nationality());
                return Action.success("BoardMember updated successfully", boardMemberRepository.save(boardMember));
            }
            else {
                throw new RuntimeException("BoardMember not found with id: " + boardMemberId);
            }
        }catch (Exception error) {
            System.out.println(error.getMessage());
            return Action.fail("Erreur : " + error.getMessage());
        }
    }
}
