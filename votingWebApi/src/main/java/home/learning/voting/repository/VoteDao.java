package home.learning.voting.repository;

import java.util.List;

import home.learning.voting.model.Vote;

public interface VoteDao {
	void leftPush(Vote vote);
	List<Object> range(long start, long end);
}
