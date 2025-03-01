package CNPS.DONNEES_COMPTABLES.business_logic.feature;

import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.jpa.entity.AccountingPlan;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IAccountingPlan {
    Action<String>loadAccountingPlan(MultipartFile file);
    List<Map<String, Object>> sortAccountingPlan();
}
