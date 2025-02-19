package CNPS.DONNEES_COMPTABLES.jpa.repository;

import CNPS.DONNEES_COMPTABLES.jpa.entity.CoherenceMistake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoherenceMistakeRepository extends JpaRepository<CoherenceMistake, UUID> {
}
