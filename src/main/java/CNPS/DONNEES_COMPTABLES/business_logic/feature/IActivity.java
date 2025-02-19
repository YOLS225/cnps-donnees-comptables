package CNPS.DONNEES_COMPTABLES.business_logic.feature;

import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.ActivityDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Activity;

import java.util.List;

public interface IActivity {
    Action<Activity> saveActivity(ActivityDTO activityDTO);
    List<Activity> findAllActivities();
}
