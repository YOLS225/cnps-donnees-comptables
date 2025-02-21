package CNPS.DONNEES_COMPTABLES;

import CNPS.DONNEES_COMPTABLES.business_logic.StatusService;
import CNPS.DONNEES_COMPTABLES.business_logic.feature.IStatus;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Status;
import CNPS.DONNEES_COMPTABLES.jpa.repository.StatusRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Seed {
    private final StatusService statusService;
    private final StatusRepository statusRepository;

    Seed(StatusService statusService,
         StatusRepository statusRepository){
        this.statusService=statusService;
        this.statusRepository=statusRepository;
    }

    private void createStatusSeed(){
        List<Status> statusList = new ArrayList<>();
        statusList.add(Status.builder().label("ACTIVE").build());
        statusList.add(Status.builder().label("INACTIVE").build());
        statusList.add(Status.builder().label("NON_TREATED").build());
        statusList.add(Status.builder().label("IN_PROCESS").build());
        statusList.add(Status.builder().label("ARCHIVED").build());
        statusList.add(Status.builder().label("UNARCHIVED").build());
        statusList.add(Status.builder().label("VISIBLE").build());
        statusList.add(Status.builder().label("UNVISIBLE").build());
        statusList.add(Status.builder().label("FINISHED").build());
        statusRepository.saveAll(statusList);
    }

    public void run(){
        createStatusSeed();
    }
}
