package example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import service.TwapRedisService;
import example.impl.RedisMessageListener;
import example.impl.RedisPublisherImpl;
import example.listener.IRedisPublisher;
import example.serializer.TwapJsonSerializer;

/**
 * This class sets up the Spring context using Spring JavaConfig. This is an
 * alternative to Spring XML configuration.
 * 
 * 1. 2. The mesager publisher is created after message consumer
 * 
 * @author theo.briscoe@gmail.com
 * 
 */
@Configuration
@EnableScheduling
public class AppConfig {
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	RedisTemplate<String, String> redisTemplate() {
		final RedisTemplate<String, String> template = new RedisTemplate<String, String>();
		template.setConnectionFactory(jedisConnectionFactory());

		template.setKeySerializer(new StringRedisSerializer());

		template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		// template.setValueSerializer(new
		// GenericToStringSerializer<Object>(Object.class));

		template.setValueSerializer(new TwapJsonSerializer());

		// TODO: set JSON Serializer

		// template.setStringSerializer(new
		// JacksonJsonRedisSerializer<String>(String.class));
		return template;
	}

	@Bean
	MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(new RedisMessageListener());
	}

	@Bean
	RedisMessageListenerContainer redisContainer() {
		final RedisMessageListenerContainer container = new RedisMessageListenerContainer();

		container.setConnectionFactory(jedisConnectionFactory());
		container.addMessageListener(messageListener(), topic());

		container.addMessageListener(messageListener(), topic());

		return container;
	}

	@Bean
	IRedisPublisher redisPublisher() {
		return new RedisPublisherImpl(redisTemplate(), topic());
	}

	@Bean
	TwapRedisService twapRedisService() {
		return new TwapRedisService(redisTemplate(), twapKeyTopic());
	}

	@Bean
	ChannelTopic topic() {
		return new ChannelTopic("pubsub:queue"); // TODO allow topic name as a
		// property
	}

	@Bean
	ChannelTopic twapKeyTopic() {
		return new ChannelTopic("twap:key:topic"); // TODO allow topic name as a
		// property
	}

}