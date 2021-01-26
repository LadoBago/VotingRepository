package home.learning.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.learning.voting.config.RedisMessagePublisher;
import home.learning.voting.model.Vote;
import home.learning.voting.repository.RedisVoteDao;

@Service
public class RedisServiceImpl implements RedisService {

	private RedisMessagePublisher messagePublisher;
	private RedisVoteDao redisRepository;
	
	@Autowired
	public RedisServiceImpl(RedisVoteDao redisRepository, RedisMessagePublisher messagePublisher) {
		this.redisRepository = redisRepository;
		this.messagePublisher = messagePublisher;
	}
	
	@Override
	public Vote dequeueNext() {
		return this.redisRepository.rightPop();
	}

	@Override
	public void broadcastMessage(String message) {
		this.messagePublisher.publish(message);
	}

}
