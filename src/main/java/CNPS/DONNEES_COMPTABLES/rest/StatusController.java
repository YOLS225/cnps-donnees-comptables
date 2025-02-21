package CNPS.DONNEES_COMPTABLES.rest;


import CNPS.DONNEES_COMPTABLES.business_logic.feature.ICompany;
import CNPS.DONNEES_COMPTABLES.business_logic.feature.IStatus;
import CNPS.DONNEES_COMPTABLES.dto.CompanyDTO;
import CNPS.DONNEES_COMPTABLES.dto.RestResponse;
import CNPS.DONNEES_COMPTABLES.dto.StatusDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Bank;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Company;

import java.util.List;
import java.util.Optional;

import CNPS.DONNEES_COMPTABLES.jpa.entity.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Ms-accounting-data")
@RequestMapping("/ms-accounting-data/status")
public class StatusController {
    private final IStatus statusService;
    public StatusController(IStatus statusService){
        this.statusService=statusService;
    }

    @Operation(summary = "Create a Status")
    @PostMapping("")
    public ResponseEntity<RestResponse<Status>> saveCompany(
            @RequestBody StatusDTO statusDTO) {
        RestResponse<Status> restResponse;
        var result = statusService.createStatus(statusDTO);
        if (result.isOk()) {
            restResponse = RestResponse.success(result.getMessage(), result.getResult());
        } else {
            restResponse = RestResponse.fail(result.getMessage(), null);
        }
        return ResponseEntity.ok(restResponse);
    }

    @GetMapping("")
    @Operation(summary = "Get all Status")
    public RestResponse<List<Status>> findAllStatus() {
        List<Status> value = statusService.findAllStatus();
        return RestResponse.success(value);
    }
}
