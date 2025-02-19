package CNPS.DONNEES_COMPTABLES.dto;


import java.time.LocalDate;

public record CompanyDTO(
        String name,
        String activity,
        String acronym,
        String socialSecurityNumber,
        String capital,
        String postalAddress,
        String country,
        String geographicAddress,
        String city,
        String region,
        String phoneNumber,
        String fax,
        String email,
        String controlEntity,
        String addressSignatory,
        String webSite,
        String functionSignatory,
        String addressContactPerson,
        String ncc,
        String functionContactPerson,
        String nameSignatory,
        String importatorCode,
        String nrc,
        String nameContactPerson,
        String codeMainActivity,
        String teleDeclarantNumber,
        String countryHeadOffice,
        String registry,
        String juridicForm,
        String numberEstablishementIn,
        LocalDate startExercice,
        String numberEstablishementOut,
        String fiscalRow,
        String logo
) {
}
