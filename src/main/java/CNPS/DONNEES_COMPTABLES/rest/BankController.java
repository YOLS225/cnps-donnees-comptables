package CNPS.DONNEES_COMPTABLES.rest;

import CNPS.DONNEES_COMPTABLES.business_logic.feature.IBank;
import CNPS.DONNEES_COMPTABLES.business_logic.feature.ICompany;
import CNPS.DONNEES_COMPTABLES.dto.BankDTO;
import CNPS.DONNEES_COMPTABLES.dto.CompanyDTO;
import CNPS.DONNEES_COMPTABLES.dto.RestResponse;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Bank;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Company;

import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@Tag(name = "Ms-accounting-data")
@RequestMapping("/ms-accounting-data/banks")
public class BankController {
    private final IBank bankService;

    public BankController(IBank bankService){
        this.bankService=bankService;
    }

    @Operation(summary = "Create bank")
    @PostMapping("")
    public ResponseEntity<RestResponse<Bank>> saveCompany(
            @RequestBody BankDTO bankDTO) {
        RestResponse<Bank> restResponse;
        var result = bankService.saveBank(bankDTO);
        if (result.isOk()) {
            restResponse = RestResponse.success(result.getMessage(), result.getResult());
        } else {
            restResponse = RestResponse.fail(result.getMessage(), null);
        }
        return ResponseEntity.ok(restResponse);
    }


    @GetMapping("")
    @Operation(summary = "Get all banks")
    public RestResponse<List<Bank>> findAllBanks() {
        List<Bank> value = bankService.findAllBanks();
        return RestResponse.success(value);
    }

}
