package CNPS.DONNEES_COMPTABLES.jpa.repository;

import CNPS.DONNEES_COMPTABLES.jpa.entity.AccountingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccoutingPlanRepository extends JpaRepository<AccountingPlan, UUID> {

}
