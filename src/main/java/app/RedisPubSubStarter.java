package app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import example.config.AppConfig;

public class RedisPubSubStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new AnnotationConfigApplicationContext(AppConfig.class);
	}
	// TODO select approach to start/stop publisher and the subscriber
}
