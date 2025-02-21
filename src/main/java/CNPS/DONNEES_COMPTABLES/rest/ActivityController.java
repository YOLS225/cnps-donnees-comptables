package CNPS.DONNEES_COMPTABLES.rest;
import CNPS.DONNEES_COMPTABLES.business_logic.feature.IActivity;
import CNPS.DONNEES_COMPTABLES.dto.ActivityDTO;
import CNPS.DONNEES_COMPTABLES.dto.BankDTO;
import CNPS.DONNEES_COMPTABLES.dto.RestResponse;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Activity;

import java.util.List;
import java.util.UUID;

import CNPS.DONNEES_COMPTABLES.jpa.entity.Bank;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@Tag(name = "Ms-accounting-data")
@RequestMapping("/ms-accounting-data/activities")
public class ActivityController {
    private final IActivity activityService;

    public ActivityController(IActivity activityService){
        this.activityService=activityService;
    }

    @Operation(summary = "Create activity")
    @PostMapping("")
    public ResponseEntity<RestResponse<Activity>> saveActivity(
            @RequestBody ActivityDTO activityDTO) {
        RestResponse<Activity> restResponse;
        var result = activityService.saveActivity(activityDTO);
        if (result.isOk()) {
            restResponse = RestResponse.success(result.getMessage(), result.getResult());
        } else {
            restResponse = RestResponse.fail(result.getMessage(), null);
        }
        return ResponseEntity.ok(restResponse);
    }


    @GetMapping("")
    @Operation(summary = "Get all activities")
    public RestResponse<List<Activity>> findAllActivities() {
        List<Activity> value = activityService.findAllActivities();
        return RestResponse.success(value);
    }

    @GetMapping("/filter")
    @Operation(summary = "filter the activities with a term")
    public List<Activity> filterActivities(@RequestParam String searchTerm) {
        List<Activity> result=activityService.filterActivities(searchTerm);
        return result;
    }

    @PutMapping("/{id}")
    @Operation(summary = "update an activity")
    public ResponseEntity<RestResponse<Activity>> updateActivity(
            @RequestParam UUID activityId, @RequestBody ActivityDTO activityDTO) {
        RestResponse<Activity> restResponse;
        var result = activityService.updateActivity(activityId,activityDTO);
        if (result.isOk()) {
            restResponse = RestResponse.success(result.getMessage(), result.getResult());
        } else {
            restResponse = RestResponse.fail(result.getMessage(), null);
        }
        return ResponseEntity.ok(restResponse);
    }
}
