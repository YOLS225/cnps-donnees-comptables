package CNPS.DONNEES_COMPTABLES.jpa.repository;

import CNPS.DONNEES_COMPTABLES.jpa.entity.AccountClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountClassRepository extends JpaRepository<AccountClass, UUID> {
}
