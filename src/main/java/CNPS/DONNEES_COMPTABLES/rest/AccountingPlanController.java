package CNPS.DONNEES_COMPTABLES.rest;
import CNPS.DONNEES_COMPTABLES.business_logic.feature.IAccountingPlan;
import CNPS.DONNEES_COMPTABLES.business_logic.feature.IBoardMember;
import CNPS.DONNEES_COMPTABLES.dto.*;
import CNPS.DONNEES_COMPTABLES.jpa.entity.AccountingPlan;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Activity;
import CNPS.DONNEES_COMPTABLES.jpa.entity.BoardMember;
import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "Ms-accounting-data")
@RequestMapping("/ms-accounting-data/accouting-plan")
public class AccountingPlanController {
    private final IAccountingPlan accountingPlanService;
    public AccountingPlanController(IAccountingPlan accountingPlanService){
        this.accountingPlanService=accountingPlanService;
    }

    @Operation(summary = "Import an accounting-plan from CSV file")
    @PostMapping(path = "",consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestResponse<String>> saveAccountingPlan(
            @RequestParam("file") MultipartFile file) {
        RestResponse<String> restResponse;
        var result = accountingPlanService.loadAccountingPlan(file);
        if (result.isOk()) {
            restResponse = RestResponse.success(result.getMessage(), result.getResult());
        } else {
            restResponse = RestResponse.fail(result.getMessage(), null);
        }
        return ResponseEntity.ok(restResponse);
    }

    @Operation(summary = "sort a account-plan")
    @GetMapping("")
    public List<Map<String, Object>> saveAccountingPlan() {
        List<Map<String, Object>> value =accountingPlanService.sortAccountingPlan();
        return value;
    }





}
