package CNPS.DONNEES_COMPTABLES.jpa.repository;

import CNPS.DONNEES_COMPTABLES.jpa.entity.Leader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LeaderRepository extends JpaRepository<Leader, UUID> {
    Optional<Leader> findLeadersByFunction(String function);
}
