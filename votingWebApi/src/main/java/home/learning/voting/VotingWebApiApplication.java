package home.learning.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = { "home.learning.voting.properties" })
@EnableConfigurationProperties
public class VotingWebApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingWebApiApplication.class, args);
	}
}
