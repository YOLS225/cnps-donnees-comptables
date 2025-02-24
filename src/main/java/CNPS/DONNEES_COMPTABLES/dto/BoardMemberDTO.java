package CNPS.DONNEES_COMPTABLES.dto;

import java.util.UUID;

public record BoardMemberDTO(
        String lastName,
        String firstName,
        String function,
        String structure,
        Float percentage,
        String adress,
        String nationality,
        UUID companyId
) {
}
