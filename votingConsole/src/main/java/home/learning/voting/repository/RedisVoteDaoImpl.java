package home.learning.voting.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import home.learning.voting.model.Vote;

@Repository
public class RedisVoteDaoImpl implements RedisVoteDao {
	private static final String ListName = "Votes";
	
	private RedisTemplate<String, Object> redisTemplate;
	private ListOperations<String, Object> listOperations;

	@Autowired
	public RedisVoteDaoImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	public void initializeListOpeartions()
	{
		listOperations = redisTemplate.opsForList();
	}
	
	@Override
	public Vote rightPop() {
		var item = listOperations.rightPop(ListName);
		if (item != null)
		{
			return Vote.class.cast(item);
		}
		return null;
	}

	
}
