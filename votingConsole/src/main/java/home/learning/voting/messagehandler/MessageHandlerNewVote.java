package home.learning.voting.messagehandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.learning.voting.model.VoteEntity;
import home.learning.voting.service.PostgresService;
import home.learning.voting.service.RedisService;

@Service(value = "voting.MessageHandler.NewVote")
public class MessageHandlerNewVote implements BroadcastMessageHandler {

	private final PostgresService postgresService;
	private final RedisService redisService;

	@Autowired
	public MessageHandlerNewVote(PostgresService postgresService, RedisService redisService) {
		this.postgresService = postgresService;
		this.redisService = redisService;
	}

	@Override
	public void processMessage(String message) {

		while (true) {
			var vote = redisService.dequeueNext();
			if (vote == null)
				break;

			var voteEntity = new VoteEntity();
			voteEntity.setName(vote.getName());

			postgresService.saveVote(voteEntity);
			redisService.broadcastMessage("DB-Updated");
		}
	}

}
