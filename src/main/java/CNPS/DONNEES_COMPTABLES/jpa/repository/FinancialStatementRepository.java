package CNPS.DONNEES_COMPTABLES.jpa.repository;

import CNPS.DONNEES_COMPTABLES.jpa.entity.FinancialStatement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FinancialStatementRepository extends JpaRepository<FinancialStatement, UUID> {

    long count();
    Optional<FinancialStatement> findFinancialStatementByLabel(String label);
}
