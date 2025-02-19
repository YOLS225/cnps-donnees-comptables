package CNPS.DONNEES_COMPTABLES.business_logic.feature;

import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.BoardMemberDTO;

import CNPS.DONNEES_COMPTABLES.jpa.entity.BoardMember;

import java.util.List;

public interface IBoardMember {
    Action<BoardMember> saveBoardMember(BoardMemberDTO boardMemberDTO);
    List<BoardMember> findAllBoardMembers();
}
