package CNPS.DONNEES_COMPTABLES.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("CNPS DONNEES COMPTABLES")
                        .version("1.0")
                        .description("API pour la gestion des données comptable de la CNPS"));
    }
}
