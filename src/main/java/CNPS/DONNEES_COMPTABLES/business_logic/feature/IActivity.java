package CNPS.DONNEES_COMPTABLES.business_logic.feature;

import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.ActivityDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Activity;

import java.util.List;
import java.util.UUID;

public interface IActivity {
    Action<Activity> saveActivity(ActivityDTO activityDTO);
    List<Activity> findAllActivities();
    List<Activity> filterActivities(String searchTerm);
    Action<Activity> updateActivity(UUID activityId, ActivityDTO activityDTO);
    Action<Activity> deleteActivity(UUID activityId);
}
