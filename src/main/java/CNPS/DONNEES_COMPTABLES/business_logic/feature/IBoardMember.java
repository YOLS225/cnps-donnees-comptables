package CNPS.DONNEES_COMPTABLES.business_logic.feature;

import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.BoardMemberDTO;

import CNPS.DONNEES_COMPTABLES.jpa.entity.BoardMember;

import java.util.List;
import java.util.UUID;

public interface IBoardMember {
    Action<BoardMember> saveBoardMember(BoardMemberDTO boardMemberDTO);
    List<BoardMember> findAllBoardMembers();
    List<BoardMember> filterBoardMembers(String searchTerm);
    Action<BoardMember> updateBoardMember(UUID boardMemberId, BoardMemberDTO boardMemberDTO);
    Action<BoardMember> deleteBoardMember(UUID boardMemberId);
}
