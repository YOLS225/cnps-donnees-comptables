package CNPS.DONNEES_COMPTABLES.business_logic.feature;

import CNPS.DONNEES_COMPTABLES.dto.Action;
import org.springframework.web.multipart.MultipartFile;

public interface IAccountingPlan {
    Action<String>loadAccountingPlan(MultipartFile file);
}
