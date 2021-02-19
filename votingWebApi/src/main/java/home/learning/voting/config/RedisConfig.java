package home.learning.voting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import home.learning.voting.properties.RedisProperties;

@Configuration
public class RedisConfig {
	private static final String BroadcastChannel = "BroadcastChannel"; 

	@Autowired
	private RedisProperties redisProperties;
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory()
	{
		System.out.print(redisProperties.toString());
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(redisProperties.getHost());
		redisStandaloneConfiguration.setPort(redisProperties.getPort());
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
		return jedisConnectionFactory;
	}
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory)
	{
		final RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();		
		return template;
	}
	
	@Bean
	public MessageListenerAdapter messageListener()
	{
		return new MessageListenerAdapter(new RedisMessageSubscriber());
	}
	
	@Bean
	public MessagePublisher redisPublisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic)
	{
		return new RedisMessagePublisher(redisTemplate, topic);
	}
	
	@Bean
	public ChannelTopic topic()
	{
		return new ChannelTopic(BroadcastChannel);
	}
	
	@Bean
	public RedisMessageListenerContainer redisContainer(JedisConnectionFactory jedisConnectionFactory, MessageListenerAdapter listenerAdapter, ChannelTopic topic)
	{
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		
		container.setConnectionFactory(jedisConnectionFactory);
		container.addMessageListener(listenerAdapter, topic);
		
		return container;
	}
}
