package CNPS.DONNEES_COMPTABLES.rest;
import CNPS.DONNEES_COMPTABLES.business_logic.feature.IActivity;
import CNPS.DONNEES_COMPTABLES.business_logic.feature.ILeader;
import CNPS.DONNEES_COMPTABLES.dto.ActivityDTO;
import CNPS.DONNEES_COMPTABLES.dto.BankDTO;
import CNPS.DONNEES_COMPTABLES.dto.LeaderDTO;
import CNPS.DONNEES_COMPTABLES.dto.RestResponse;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Activity;

import java.util.List;
import java.util.UUID;

import CNPS.DONNEES_COMPTABLES.jpa.entity.Bank;
import CNPS.DONNEES_COMPTABLES.jpa.entity.BoardMember;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Leader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@Tag(name = "Ms-accounting-data")
@RequestMapping("/ms-accounting-data/leaders")
public class LeaderController {
    private final ILeader leaderService;

    public LeaderController(ILeader leaderService){
        this.leaderService=leaderService;
    }

    @Operation(summary = "Create a leader")
    @PostMapping("")
    public ResponseEntity<RestResponse<Leader>> saveLeader(
            @RequestBody LeaderDTO leaderDTO) {
        RestResponse<Leader> restResponse;
        var result = leaderService.saveLeader(leaderDTO);
        if (result.isOk()) {
            restResponse = RestResponse.success(result.getMessage(), result.getResult());
        } else {
            restResponse = RestResponse.fail(result.getMessage(), null);
        }
        return ResponseEntity.ok(restResponse);
    }


    @GetMapping("")
    @Operation(summary = "Get all Leaders")
    public RestResponse<List<Leader>> findAllLeaders() {
        List<Leader> value = leaderService.findAllLeaders();
        return RestResponse.success(value);
    }

    @GetMapping("/filter")
    @Operation(summary = "filter the leaders with a term")
    public List<Leader> filterLeaders(@RequestParam String searchTerm) {
        List<Leader> result=leaderService.filterLeaders(searchTerm);
        return result;
    }

    @PutMapping("/{id}")
    @Operation(summary = "update a leader")
    public ResponseEntity<RestResponse<Leader>> updateLeader(
            @RequestParam UUID leaderId, @RequestBody LeaderDTO leaderDTO) {
        RestResponse<Leader> restResponse;
        var result = leaderService.updateLeader(leaderId,leaderDTO);
        if (result.isOk()) {
            restResponse = RestResponse.success(result.getMessage(), result.getResult());
        } else {
            restResponse = RestResponse.fail(result.getMessage(), null);
        }
        return ResponseEntity.ok(restResponse);
    }
}
