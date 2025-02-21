package CNPS.DONNEES_COMPTABLES.business_logic;

import CNPS.DONNEES_COMPTABLES.business_logic.feature.IStatus;
import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.StatusDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Activity;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Company;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Status;
import CNPS.DONNEES_COMPTABLES.jpa.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService implements IStatus {
    private final StatusRepository statusRepository;
    public StatusService(StatusRepository statusRepository){
        this.statusRepository=statusRepository;
    }


    @Override
    public Action<Status> createStatus(StatusDTO statusDTO) {
        try {
            Status status = Status.builder()
                        .label(statusDTO.label())
                        .build();
            return Action.success("Status:created",statusRepository.save(status));
        } catch (Exception error) {
            System.out.println(error.getMessage());
            return Action.fail("erreur : "+error.getMessage(),null);
        }
    }

    @Override
    public List<Status> findAllStatus() {
        return statusRepository.findAll();
    }
}
