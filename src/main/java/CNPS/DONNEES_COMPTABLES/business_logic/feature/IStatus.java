package CNPS.DONNEES_COMPTABLES.business_logic.feature;

import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.StatusDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Status;

import java.util.List;

public interface IStatus {
    Action<Status> createStatus(StatusDTO statusDTO);
    List<Status> findAllStatus();
}
