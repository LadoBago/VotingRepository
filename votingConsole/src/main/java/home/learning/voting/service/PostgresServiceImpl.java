package home.learning.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import home.learning.voting.model.VoteEntity;
import home.learning.voting.repository.PostgresVoteRepository;

@Service
public class PostgresServiceImpl implements PostgresService {
	private PostgresVoteRepository repository;

	@Autowired
	public PostgresServiceImpl(PostgresVoteRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void saveVote(VoteEntity vote) {
	
		repository.save(vote);

	}

}
