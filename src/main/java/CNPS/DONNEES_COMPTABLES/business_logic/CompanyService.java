package CNPS.DONNEES_COMPTABLES.business_logic;


import CNPS.DONNEES_COMPTABLES.business_logic.feature.ICompany;
import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.CompanyDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Company;
import CNPS.DONNEES_COMPTABLES.jpa.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService implements ICompany {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository=companyRepository;
    }

    @Override
    public Action<Company> saveCompany(CompanyDTO companyDTO) {
        try{
            Company company = Company.builder()
                    .name(companyDTO.name())
                    .acronym(companyDTO.acronym())
                    .activity(companyDTO.activity())
                    .socialSecurityNumber(companyDTO.socialSecurityNumber())
                    .capital(companyDTO.capital())
                    .postalAddress(companyDTO.postalAddress())
                    .country(companyDTO.country())
                    .geographicAddress(companyDTO.geographicAddress())
                    .city(companyDTO.city())
                    .region(companyDTO.region())
                    .phoneNumber(companyDTO.phoneNumber())
                    .fax(companyDTO.fax())
                    .email(companyDTO.email())
                    .controlEntity(companyDTO.controlEntity())
                    .addressSignatory(companyDTO.addressSignatory())
                    .webSite(companyDTO.webSite())
                    .functionSignatory(companyDTO.functionSignatory())
                    .addressContactPerson(companyDTO.addressContactPerson())
                    .ncc(companyDTO.ncc())
                    .functionContactPerson(companyDTO.functionContactPerson())
                    .nameSignatory(companyDTO.nameSignatory())
                    .importatorCode(companyDTO.importatorCode())
                    .nrc(companyDTO.nrc())
                    .nameContactPerson(companyDTO.nameContactPerson())
                    .codeMainActivity(companyDTO.codeMainActivity())
                    .teleDeclarantNumber(companyDTO.teleDeclarantNumber())
                    .countryHeadOffice(companyDTO.countryHeadOffice())
                    .registry(companyDTO.registry())
                    .juridicForm(companyDTO.juridicForm())
                    .numberEstablishementIn(companyDTO.numberEstablishementIn())
                    .startExercice(companyDTO.startExercice())
                    .numberEstablishementOut(companyDTO.numberEstablishementOut())
                    .fiscalRow(companyDTO.fiscalRow())
                    .logo(companyDTO.logo())
                    .build();
            return Action.success("Company:created",companyRepository.save(company));
        }catch (Exception error){
            System.out.println(error.getMessage());
            return Action.fail("erreur : "+error.getMessage());
        }
    }

    @Override
    public Optional<Company> getCompanyById(UUID id) {
        Optional<Company> result = companyRepository.findById(id);
        return result;
    }
}
