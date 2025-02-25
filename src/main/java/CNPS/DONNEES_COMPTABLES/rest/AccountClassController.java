package CNPS.DONNEES_COMPTABLES.rest;
import CNPS.DONNEES_COMPTABLES.business_logic.feature.IAccountClass;
import CNPS.DONNEES_COMPTABLES.business_logic.feature.IAccountingPlan;
import CNPS.DONNEES_COMPTABLES.business_logic.feature.IBoardMember;
import CNPS.DONNEES_COMPTABLES.dto.*;
import CNPS.DONNEES_COMPTABLES.jpa.entity.BoardMember;
import java.util.List;

import CNPS.DONNEES_COMPTABLES.jpa.entity.Leader;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "Ms-accounting-data")
@RequestMapping("/ms-accounting-data/account-classes")
public class AccountClassController {
    private final IAccountClass accountClassService;
    public AccountClassController(IAccountClass accountClassService){
        this.accountClassService=accountClassService;
    }

    @Operation(summary = "Import the class of account from CSV file")
    @PostMapping(path = "",consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestResponse<String>> loadClasses(
            @RequestParam("file") MultipartFile file) {
        RestResponse<String> restResponse;
        var result = accountClassService.loadAccountClass(file);
        if (result.isOk()) {
            restResponse = RestResponse.success(result.getMessage(), result.getResult());
        } else {
            restResponse = RestResponse.fail(result.getMessage(), null);
        }
        return ResponseEntity.ok(restResponse);
    }

    @GetMapping("")
    @Operation(summary = "Get all Parents from accounting-plan")
    public RestResponse<List<List<String>>> findAllParents() {
        List<List<String>> value = accountClassService.findAllParent();

//        for (int i=0;i<value.size();i++){
//            List<String> element= value.get(i);
//            System.out.println("*****************voir element :"+element+" *****************");
//            System.out.println("*****************voir subelement1 :"+element.get(0)+"voir subelement2:"+element.get(1)+"voir subelement3:"+element.get(2)+ "*****************");
//        }
        return RestResponse.success(value);
    }
}
