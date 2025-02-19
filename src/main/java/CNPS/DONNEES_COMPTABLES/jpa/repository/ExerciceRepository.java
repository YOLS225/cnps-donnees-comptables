package CNPS.DONNEES_COMPTABLES.jpa.repository;

import CNPS.DONNEES_COMPTABLES.jpa.entity.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ExerciceRepository extends JpaRepository<Exercice, UUID> {
    long count();
    Optional<Exercice> findExerciceByLabel(String label);
}
