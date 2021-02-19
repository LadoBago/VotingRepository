package home.learning.voting.messagehandler;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeanFactoryMessageHandler {
	private static final String SERVICE_NAME_PREFIX = "voting.MessageHandler";

	@Autowired
	public BeanFactoryMessageHandler(BeanFactory beanfactory) {
		this.beanfactory = beanfactory;
	}

	private final BeanFactory beanfactory;

	public void processMessage(String message) {
		try {
			var service = beanfactory.getBean(getBeanName(message), BroadcastMessageHandler.class);
			service.processMessage(message);
		} catch (Exception exception) {
			System.out.println("Unhandled message: " + message.toString());
			System.out.println(exception.toString());
		}

	}

	private String getBeanName(String message) {

		return String.join(".", SERVICE_NAME_PREFIX, message);
	}

}
