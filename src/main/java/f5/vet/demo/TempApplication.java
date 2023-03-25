package f5.vet.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TempApplication {

    final RetrieveEnvironmentProperties retrieveEnvironmentProperties;

    public TempApplication(RetrieveEnvironmentProperties retrieveEnvironmentProperties) {
        this.retrieveEnvironmentProperties = retrieveEnvironmentProperties;
    }

    public static void main(String... args) {
        SpringApplication.run(TempApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            System.out.println("Hello World!");
            System.out.println(retrieveEnvironmentProperties.toString());
        };
    }
}
