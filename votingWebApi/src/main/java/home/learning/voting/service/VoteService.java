package home.learning.voting.service;

import java.util.List;

import home.learning.voting.model.Vote;

public interface VoteService {
	void enqueueVote(Vote vote);
	List<Object> getUnhandledVotes();

}
