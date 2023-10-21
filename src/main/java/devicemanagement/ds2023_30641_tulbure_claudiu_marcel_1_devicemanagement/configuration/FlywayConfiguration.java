package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.configuration;

import lombok.Getter;
import lombok.Setter;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:flyway.yml")
@Getter
@Setter
public class FlywayConfiguration {

    @Autowired
    private DataSource dataSource;
    @Value("${locations}")
    private String path;

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        FluentConfiguration configuration = Flyway.configure()
                .baselineOnMigrate(true)
                .validateOnMigrate(true)
                .cleanOnValidationError(true)
                .outOfOrder(true)
                .locations(path.split(","))
                .dataSource(dataSource);
        return new Flyway(configuration);
    }


}
