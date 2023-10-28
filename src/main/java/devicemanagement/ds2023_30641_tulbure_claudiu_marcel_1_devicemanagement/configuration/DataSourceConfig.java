package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    @Value("${database.ip}")
    private String databaseUrlFlyway;
    @Value("${database.port}")
    private String databasePortFlyway;


    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://" + databaseUrlFlyway+":"+ databasePortFlyway +"/devicemanagement");
        config.setUsername("root");
        config.setPassword("12345678");
        return new HikariDataSource(config);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
