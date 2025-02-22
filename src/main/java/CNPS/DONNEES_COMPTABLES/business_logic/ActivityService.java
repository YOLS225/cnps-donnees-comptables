package CNPS.DONNEES_COMPTABLES.business_logic;

import CNPS.DONNEES_COMPTABLES.business_logic.feature.IActivity;
import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.ActivityDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Activity;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Bank;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Company;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Status;
import CNPS.DONNEES_COMPTABLES.jpa.repository.ActivityRepository;
import CNPS.DONNEES_COMPTABLES.jpa.repository.CompanyRepository;
import CNPS.DONNEES_COMPTABLES.jpa.repository.StatusRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ActivityService implements IActivity {

    private final CompanyRepository companyRepository;
    private final ActivityRepository activityRepository;
    private final StatusRepository statusRepository;

    public ActivityService(CompanyRepository companyRepository,
                           ActivityRepository activityRepository,
                           StatusRepository statusRepository){
        this.companyRepository=companyRepository;
        this.activityRepository=activityRepository;
        this.statusRepository=statusRepository;
    }


    @Override
    public Action<Activity> saveActivity(ActivityDTO activityDTO) {
        try {
            Optional<Company> recoveredCompany = companyRepository.findById(activityDTO.companyId());
            Optional<Status> recoveredStatus=statusRepository.findStatusByLabel(activityDTO.statusLabel());
            if (recoveredCompany.isPresent() && recoveredStatus.isPresent()){
                Company company = recoveredCompany.get();
                Status status=recoveredStatus.get();
                Activity activity = Activity.builder()
                        .name(activityDTO.name())
                        .code(activityDTO.code())
                        .status(status)
                        .company(company)
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

    @Override
    public List<Activity> filterActivities(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return activityRepository.findAll();
        }
        String likePattern = "%" + searchTerm + "%";
        return activityRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.like(root.get("name"), likePattern));
            predicates.add(cb.like(root.get("code"), likePattern));
            return cb.or(predicates.toArray(new Predicate[0]));
        });
    }

    @Override
    public Action<Activity> updateActivity(UUID activityId, ActivityDTO activityDTO) {
        try {
            Optional<Activity> recoveredActivity=activityRepository.findById(activityId);
            if (recoveredActivity.isPresent()){
                Activity activity= recoveredActivity.get();
                //MAJ des informations
                activity.setName(activityDTO.name());
                activity.setCode(activityDTO.code());
                return Action.success("Activity updated successfully", activityRepository.save(activity));
            }else {
                throw new RuntimeException("Activity not found with id: " + activityId);
            }
        }catch (Exception error) {
            System.out.println(error.getMessage());
            return Action.fail("Erreur : " + error.getMessage());
        }
    }

    @Override
    public Action<Activity> deleteActivity(UUID activityId) {
        try {
            Optional<Activity> recoveredActivity=activityRepository.findById(activityId);
            Optional<Status> recoveredStatus=statusRepository.findStatusByLabel("INACTIVE");
            if (recoveredActivity.isPresent() && recoveredStatus.isPresent()){
                Activity activity= recoveredActivity.get();
                Status status = recoveredStatus.get();

                //MAJ des informations
                activity.setStatus(status);
                return Action.success("Activity deleted successfully", activityRepository.save(activity));
            }else {
                throw new RuntimeException("Activity not found with id: " + activityId);
            }
        }catch (Exception error) {
            System.out.println(error.getMessage());
            return Action.fail("Erreur : " + error.getMessage());
        }
    }
}
