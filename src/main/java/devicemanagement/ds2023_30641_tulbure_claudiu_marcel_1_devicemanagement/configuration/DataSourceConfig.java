package devicemanagement.ds2023_30641_tulbure_claudiu_marcel_1_devicemanagement.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {



    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/devicemanagement");
        config.setUsername("root");
        config.setPassword("12345678");
        return new HikariDataSource(config);
    }


}
