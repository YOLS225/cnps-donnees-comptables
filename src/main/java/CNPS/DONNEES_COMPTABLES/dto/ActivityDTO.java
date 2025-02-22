package CNPS.DONNEES_COMPTABLES.dto;

import java.util.UUID;

public record ActivityDTO(
        String name,
        String code,
        String statusLabel,
        UUID companyId
) {
}
