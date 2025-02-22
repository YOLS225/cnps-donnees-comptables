package CNPS.DONNEES_COMPTABLES.rest;
import CNPS.DONNEES_COMPTABLES.business_logic.feature.IBoardMember;
import CNPS.DONNEES_COMPTABLES.dto.*;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Activity;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Bank;
import CNPS.DONNEES_COMPTABLES.jpa.entity.BoardMember;
import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@Tag(name = "Ms-accounting-data")
@RequestMapping("/ms-accounting-data/board-members")
public class BoardMemberController {
    private final IBoardMember boardMemberService;

    public BoardMemberController(IBoardMember boardMemberService){
        this.boardMemberService=boardMemberService;
    }

    @Operation(summary = "Create a board-member")
    @PostMapping("")
    public ResponseEntity<RestResponse<BoardMember>> saveBoardMember(
            @RequestBody BoardMemberDTO boardMemberDTO) {
        RestResponse<BoardMember> restResponse;
        var result = boardMemberService.saveBoardMember(boardMemberDTO);
        if (result.isOk()) {
            restResponse = RestResponse.success(result.getMessage(), result.getResult());
        } else {
            restResponse = RestResponse.fail(result.getMessage(), null);
        }
        return ResponseEntity.ok(restResponse);
    }


    @GetMapping("")
    @Operation(summary = "Get all board-members")
    public RestResponse<List<BoardMember>> findAllBoardMembers() {
        List<BoardMember> value = boardMemberService.findAllBoardMembers();
        return RestResponse.success(value);
    }

    @GetMapping("/filter")
    @Operation(summary = "filter the board-members with a term")
    public List<BoardMember> filterBoardMembers(@RequestParam String searchTerm) {
        List<BoardMember> result=boardMemberService.filterBoardMembers(searchTerm);
        return result;
    }

    @PutMapping("/{id}")
    @Operation(summary = "update a board-member")
    public ResponseEntity<RestResponse<BoardMember>> updateBoardMember(
            @RequestParam UUID boardMemberId,@RequestBody BoardMemberDTO boardMemberDTO) {
        RestResponse<BoardMember> restResponse;
        var result = boardMemberService.updateBoardMember(boardMemberId,boardMemberDTO);
        if (result.isOk()) {
            restResponse = RestResponse.success(result.getMessage(), result.getResult());
        } else {
            restResponse = RestResponse.fail(result.getMessage(), null);
        }
        return ResponseEntity.ok(restResponse);
    }

    @PutMapping("delete/{id}")
    @Operation(summary = "delete a board-member")
    public ResponseEntity<RestResponse<BoardMember>> deleteBoardMember(
            @RequestParam UUID boardMemberId) {
        RestResponse<BoardMember> restResponse;
        var result = boardMemberService.deleteBoardMember(boardMemberId);
        if (result.isOk()) {
            restResponse = RestResponse.success(result.getMessage(), result.getResult());
        } else {
            restResponse = RestResponse.fail(result.getMessage(), null);
        }
        return ResponseEntity.ok(restResponse);
    }

}
