package CNPS.DONNEES_COMPTABLES.business_logic.feature;

import CNPS.DONNEES_COMPTABLES.dto.Action;

import CNPS.DONNEES_COMPTABLES.dto.LeaderDTO;

import CNPS.DONNEES_COMPTABLES.jpa.entity.Leader;

import java.util.List;

public interface ILeader {
    Action<Leader> saveLeader(LeaderDTO leaderDTO);
    List<Leader> findAllLeaders();
}
