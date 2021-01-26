package home.learning.voting;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import home.learning.voting.model.VoteEntity;
import home.learning.voting.service.PostgresService;
import home.learning.voting.service.RedisService;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = { "home.learning.voting.properties" })
@EnableConfigurationProperties
public class VotingConsoleApplication implements CommandLineRunner {

	private PostgresService postgresService;
	private RedisService redisService;
	
	public static void main(String[] args) {
		SpringApplication.run(VotingConsoleApplication.class, args);
	}
	
	@Autowired
	public VotingConsoleApplication(PostgresService postgresService, RedisService redisService) {
		this.postgresService = postgresService;
		this.redisService = redisService;
	}

	@Override
	public void run(String... args) throws Exception {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (!reader.ready())
		{
			var vote = redisService.dequeueNext();
			if (vote == null)
				continue;
			
			var voteEntity = new VoteEntity();
			voteEntity.setName(vote.getName());
			
			postgresService.saveVote(voteEntity);
			redisService.broadcastMessage("DB-Updated");
		}
	}

}
