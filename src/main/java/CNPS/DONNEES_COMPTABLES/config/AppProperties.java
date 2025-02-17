package CNPS.DONNEES_COMPTABLES.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {
  private String appEnv;
}
