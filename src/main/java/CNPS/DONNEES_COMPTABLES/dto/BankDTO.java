package CNPS.DONNEES_COMPTABLES.dto;

import CNPS.DONNEES_COMPTABLES.jpa.entity.Company;

import java.util.UUID;

public record BankDTO(
      String name,
      String code,
      String windowsCode,
      String accountNumber,
      String keyRib,
      String iban,
      UUID companyId
) {
}
