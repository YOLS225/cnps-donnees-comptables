package CNPS.DONNEES_COMPTABLES.jpa.repository;

import CNPS.DONNEES_COMPTABLES.jpa.entity.AccountClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AccountClassRepository extends JpaRepository<AccountClass, UUID> {
    @Query("SELECT DISTINCT a.reference,a.label,a.parent FROM AccountClass a ORDER BY a.parent ASC")
    List<List<String>> findDistinctParent();
}
