package CNPS.DONNEES_COMPTABLES.jpa.repository;

import CNPS.DONNEES_COMPTABLES.jpa.entity.Activity;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Associates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AssociateRepository extends JpaRepository<Associates, UUID> {
    Optional<Activity> findActivityByFirstName(String firstName);
    Optional<Activity> findActivityByFunction(String function);
}
