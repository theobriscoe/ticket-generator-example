package example.impl;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * An example of a simple message listner
 * 
 * @author theo.briscoe@gmail.com
 * 
 */
public class RedisMessageListener implements MessageListener {

	public void onMessage(final Message message, final byte[] pattern) {
		System.out.println("Message received: " + message.toString());
	}
}