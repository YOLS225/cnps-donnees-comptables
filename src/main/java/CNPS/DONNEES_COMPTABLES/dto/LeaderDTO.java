package CNPS.DONNEES_COMPTABLES.dto;

import java.util.UUID;

public record LeaderDTO(
        String lastName,
        String firstName,
        String function,
        String nationality,
        String fiscalId,
        String adress,
        String statusLabel,
        UUID companyId
) {
}
