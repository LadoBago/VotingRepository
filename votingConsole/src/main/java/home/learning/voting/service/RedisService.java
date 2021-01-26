package home.learning.voting.service;

import home.learning.voting.model.Vote;

public interface RedisService {
	Vote dequeueNext();
	void broadcastMessage(String message);

}
