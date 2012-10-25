package example.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.scheduling.annotation.Scheduled;

import example.listener.IRedisPublisher;

public class RedisPublisherImpl implements IRedisPublisher {
	private final RedisTemplate<String, String> template;
	private final ChannelTopic topic;
	private final AtomicLong counter = new AtomicLong(0);

	public RedisPublisherImpl(final RedisTemplate<String, String> template, final ChannelTopic topic) {
		this.template = template;
		this.topic = topic;
	}

	@Scheduled(fixedDelay = 100)
	public void publish() {
		template.convertAndSend(topic.getTopic(), "Message " + counter.incrementAndGet() + ", "
				+ Thread.currentThread().getName());
	}
}