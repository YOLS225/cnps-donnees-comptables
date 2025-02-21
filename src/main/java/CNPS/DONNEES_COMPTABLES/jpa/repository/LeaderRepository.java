package CNPS.DONNEES_COMPTABLES.jpa.repository;

import CNPS.DONNEES_COMPTABLES.jpa.entity.Leader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface LeaderRepository extends JpaRepository<Leader, UUID>, JpaSpecificationExecutor<Leader> {
    Optional<Leader> findLeadersByFunction(String function);
}
