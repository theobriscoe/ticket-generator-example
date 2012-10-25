package service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

import example.model.Twap;

/**
 * Provides Create, Read, Update, Delete methods for the TWAP data structure
 * 
 * @author agile-development-group
 * 
 */
public class TwapRedisService {

	private final RedisTemplate<String, String> redisTemplate;
	private final ChannelTopic topic;

	public TwapRedisService(final RedisTemplate<String, String> redisTemplate, final ChannelTopic topic) {
		this.redisTemplate = redisTemplate;
		this.topic = topic;
	}

	// TODO: Use JSON serialization
	public String create(Twap twap) {
		String key = generateKey(twap);

		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("symbol", twap.symbol);
		tempMap.put("twap1", twap.twap1.toString());
		tempMap.put("twap5", twap.twap5.toString());
		tempMap.put("twap30", twap.twap30.toString());
		tempMap.put("twap60", twap.twap60.toString());
		tempMap.put("timestamp", twap.timestamp.toString());

		redisTemplate.opsForHash().putAll(key, tempMap);

		return key;
	}

	// TODO: Use JSON serialization
	public Twap read(String key) {

		if (redisTemplate.keys(key).isEmpty()) {
			return new Twap();
		}

		String symbol = (String) redisTemplate.opsForHash().get(key, "symbol");
		Double twap1 = Double.valueOf((String) redisTemplate.opsForHash().get(key, "twap1"));
		Double twap5 = Double.valueOf((String) redisTemplate.opsForHash().get(key, "twap5"));
		Double twap30 = Double.valueOf((String) redisTemplate.opsForHash().get(key, "twap30"));
		Double twap60 = Double.valueOf((String) redisTemplate.opsForHash().get(key, "twap60"));
		Long timestamp = Long.valueOf((String) redisTemplate.opsForHash().get(key, "timestamp"));

		Twap twap = new Twap(symbol, twap1, twap5, twap30, twap60, timestamp);

		return twap;
	}

	public void delete(Twap twap) {
		String key = generateKey(twap);
		redisTemplate.delete(key);

	}

	public void publishKey(Twap twap) {
		redisTemplate.convertAndSend(topic.getTopic(), generateKey(twap));
	}

	/**
	 * @param twap
	 * @return key used to store Twap values in Redis (example
	 *         TWAP:INTC:1350929446050)
	 */
	public static String generateKey(Twap twap) {
		String key = "TWAP:" + twap.symbol + ":" + twap.timestamp.toString();
		return key;
	}

	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

}
