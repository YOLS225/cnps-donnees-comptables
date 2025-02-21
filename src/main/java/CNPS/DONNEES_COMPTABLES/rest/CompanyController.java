package CNPS.DONNEES_COMPTABLES.rest;


import CNPS.DONNEES_COMPTABLES.business_logic.feature.ICompany;
import CNPS.DONNEES_COMPTABLES.dto.CompanyDTO;
import CNPS.DONNEES_COMPTABLES.dto.RestResponse;
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
@RequestMapping("/ms-accounting-data/companies")
public class CompanyController {
    private final ICompany companyService;

    public CompanyController(ICompany companyService){
        this.companyService=companyService;
    }

    @Operation(summary = "Create a company")
    @PostMapping("")
    public ResponseEntity<RestResponse<Company>> saveCompany(
            @RequestBody CompanyDTO companyDTO) {
        RestResponse<Company> restResponse;
        var result = companyService.saveCompany(companyDTO);
        if (result.isOk()) {
            restResponse = RestResponse.success(result.getMessage(), result.getResult());
        } else {
            restResponse = RestResponse.fail(result.getMessage(), null);
        }
        return ResponseEntity.ok(restResponse);
    }


    @GetMapping("{id}")
    @Operation(summary = "Get a company")
    public RestResponse<Optional<Company>> getRequest(@PathVariable UUID id) {
        Optional<Company> value = companyService.getCompanyById(id);
        return RestResponse.success(value);
    }
}
