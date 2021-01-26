package home.learning.voting.service;

import home.learning.voting.model.VoteEntity;

public interface PostgresService {
	void saveVote(VoteEntity vote);
}
