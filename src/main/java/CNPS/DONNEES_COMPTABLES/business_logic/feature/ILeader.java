package CNPS.DONNEES_COMPTABLES.business_logic.feature;

import CNPS.DONNEES_COMPTABLES.dto.Action;

import CNPS.DONNEES_COMPTABLES.dto.LeaderDTO;

import CNPS.DONNEES_COMPTABLES.jpa.entity.Leader;

import java.util.List;
import java.util.UUID;

public interface ILeader {
    Action<Leader> saveLeader(LeaderDTO leaderDTO);
    List<Leader> findAllLeaders();
    List<Leader> filterLeaders(String searchTerm);
    Action<Leader> updateLeader(UUID leaderId, LeaderDTO leaderDTO);
}
