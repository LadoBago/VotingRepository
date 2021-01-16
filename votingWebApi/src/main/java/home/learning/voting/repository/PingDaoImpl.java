package home.learning.voting.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PingDaoImpl implements PingDao {
	@Autowired
	public PingDaoImpl(JedisConnectionFactory jedisConnectionFactory) {
		this.jedisConnectionFactory = jedisConnectionFactory;
	}

	private JedisConnectionFactory jedisConnectionFactory;
	
	@Override
	public String ping() {
		return jedisConnectionFactory.getConnection().ping();
	}

}
