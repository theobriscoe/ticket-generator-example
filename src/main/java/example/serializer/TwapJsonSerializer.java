package example.serializer;

import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;

import example.model.Twap;

public class TwapJsonSerializer extends JacksonJsonRedisSerializer<Twap> {

	public TwapJsonSerializer() {
		super(Twap.class);
	}

}
