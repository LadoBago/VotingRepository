package home.learning.voting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.learning.voting.config.RedisMessagePublisher;
import home.learning.voting.model.Vote;
import home.learning.voting.repository.VoteDao;

@Service
public class VoteServiceImpl implements VoteService  {
	private VoteDao voteDao;
	private RedisMessagePublisher messagePublisher;

	@Autowired
	public VoteServiceImpl(VoteDao voteDao, RedisMessagePublisher messagePublisher) {
		this.voteDao = voteDao;
		this.messagePublisher = messagePublisher;
	}

	@Override
	public void enqueueVote(Vote vote) {
		voteDao.leftPush(vote);
		this.messagePublisher.publish("NewVote");
	}

	@Override
	public List<Object> getUnhandledVotes() {
		return voteDao.range(0, -1);
	}

}
