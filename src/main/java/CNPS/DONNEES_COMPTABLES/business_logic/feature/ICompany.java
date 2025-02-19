package CNPS.DONNEES_COMPTABLES.business_logic.feature;

import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.CompanyDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICompany {
    Action<Company> saveCompany(CompanyDTO companyDTO);
    Optional<Company> getCompanyById(UUID id);
}
