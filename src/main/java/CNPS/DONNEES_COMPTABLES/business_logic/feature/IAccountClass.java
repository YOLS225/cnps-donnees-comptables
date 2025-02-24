package CNPS.DONNEES_COMPTABLES.business_logic.feature;

import CNPS.DONNEES_COMPTABLES.dto.Action;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAccountClass {
    Action<String> loadAccountClass(MultipartFile file);
    List<String> findAllParent();
}
