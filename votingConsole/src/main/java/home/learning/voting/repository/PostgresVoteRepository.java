package home.learning.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import home.learning.voting.model.VoteEntity;

@Repository
public interface PostgresVoteRepository extends JpaRepository<VoteEntity, Integer> {

}
