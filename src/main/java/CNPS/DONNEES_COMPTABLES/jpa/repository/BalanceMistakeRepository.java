package CNPS.DONNEES_COMPTABLES.jpa.repository;

import CNPS.DONNEES_COMPTABLES.jpa.entity.BalanceMistake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BalanceMistakeRepository extends JpaRepository<BalanceMistake, UUID> {
}
