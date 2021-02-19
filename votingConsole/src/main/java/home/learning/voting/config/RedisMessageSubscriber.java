package home.learning.voting.config;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import home.learning.voting.messagehandler.BeanFactoryMessageHandler;

@Service
public class RedisMessageSubscriber implements MessageListener {

	@Autowired
	public RedisMessageSubscriber(BeanFactoryMessageHandler beanFactoryMessageHandler) {
		this.beanFactoryMessageHandler = beanFactoryMessageHandler;
	}

	private final BeanFactoryMessageHandler beanFactoryMessageHandler;
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		//var msg = message.toString();
		var msg = new String(message.getBody(), StandardCharsets.UTF_8);
		msg = msg.replace("\"", "");
		System.out.println("Message received: " + msg);
		beanFactoryMessageHandler.processMessage(msg);
	}

}
