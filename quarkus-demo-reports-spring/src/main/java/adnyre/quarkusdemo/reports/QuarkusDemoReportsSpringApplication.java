package adnyre.quarkusdemo.reports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class QuarkusDemoReportsSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuarkusDemoReportsSpringApplication.class, args);
    }

    @Bean
    public RestTemplate httpClient() {
        return new RestTemplate();
    }

}
