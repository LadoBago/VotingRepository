package home.learning.voting.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.redis")
@ConstructorBinding
@ToString
public class RedisProperties {
	private String host;
	private int port;
}
