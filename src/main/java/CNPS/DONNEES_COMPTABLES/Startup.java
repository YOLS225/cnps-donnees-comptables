package CNPS.DONNEES_COMPTABLES;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Startup implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Startup.class);
    private final Seed seed;

    public Startup(Seed seed){
        this.seed=seed;
    }

    @Value("${server.port}")
    private int serverPort;

    @Override
    public void run(String... args) throws Exception {
        log.info("App Startup ...");
        System.out.println("Server   : http://localhost:" + serverPort);
        System.out.println("Swagger  : http://localhost:" + serverPort + "/swagger-ui/index.html");
        seed.run();
    }
}
