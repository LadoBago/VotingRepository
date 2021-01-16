package home.learning.voting.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import home.learning.voting.model.Vote;

@Repository
public class VoteDaoImpl implements VoteDao{
	private static final String ListName = "Votes";
	
	private RedisTemplate<String, Object> redisTemplate;
	private ListOperations<String, Object> listOperations;

	@Autowired
	public VoteDaoImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	public void initializeListOpeartions()
	{
		listOperations = redisTemplate.opsForList();
	}

	@Override
	public void leftPush(Vote vote) {
		listOperations.leftPush(ListName, vote);
	}

	@Override
	public List<Object> range(long start, long end) {
		//List<Object> list = listOperations.range(ListName, start, end);
		return listOperations.range(ListName, start, end);

	}
}
