package CNPS.DONNEES_COMPTABLES.business_logic;

import CNPS.DONNEES_COMPTABLES.business_logic.feature.IActivity;
import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.ActivityDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Activity;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Bank;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Company;
import CNPS.DONNEES_COMPTABLES.jpa.repository.ActivityRepository;
import CNPS.DONNEES_COMPTABLES.jpa.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ActivityService implements IActivity {

    private final CompanyRepository companyRepository;
    private final ActivityRepository activityRepository;

    public ActivityService(CompanyRepository companyRepository,
                           ActivityRepository activityRepository){
        this.companyRepository=companyRepository;
        this.activityRepository=activityRepository;
    }


    @Override
    public Action<Activity> saveActivity(ActivityDTO activityDTO) {
        try {
            Optional<Company> company = companyRepository.findById(activityDTO.companyId());
            if (company.isPresent()){
                Company company1 = company.get();
                Activity activity = Activity.builder()
                        .name(activityDTO.name())
                        .code(activityDTO.code())
                        .company(company1)
                        .build();
                return Action.success("Activity:created",activityRepository.save(activity));}
            else {
                throw new RuntimeException("Company not found with id: " + activityDTO.companyId());
            }
        }catch (Exception error){
            System.out.println(error.getMessage());
            return Action.fail("erreur : "+error.getMessage());
        }
    }

    @Override
    public List<Activity> findAllActivities() {
        List<Activity> result = activityRepository.findAll();
        return result;
    }
}
