package CNPS.DONNEES_COMPTABLES.jpa.repository;

import CNPS.DONNEES_COMPTABLES.jpa.entity.AccountMistake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountMistakeRepository extends JpaRepository<AccountMistake, UUID> {
}
