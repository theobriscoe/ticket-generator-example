package example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.TwapRedisService;
import example.config.AppConfig;
import example.model.Twap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class TwapRedisServiceTest {

	@Autowired
	private TwapRedisService service;

	@Test
	public void testConfiguration() {
		assertNotNull(service);
		assertNotNull(service.getRedisTemplate());
		assertEquals("PONG", service.getRedisTemplate().getConnectionFactory().getConnection().ping());
	}

	@Test
	public void testBuildKeyCurrentDate() {
		Twap twap = new Twap();
		String key = TwapRedisService.generateKey(twap);
		assertNotNull(key);

		twap = new Twap();
		twap.setSymbol("INTC");
		twap.setTwap1(0d);
		twap.setTwap5(0d);
		twap.setTwap30(0d);
		twap.setTwap60(0d);
		twap.setTimestamp(new Date().getTime());
		key = TwapRedisService.generateKey(twap);

		assertNotNull(key);
	}

	@Test
	public void testBuildKey() {
		Twap twap = new Twap();
		String key = TwapRedisService.generateKey(twap);
		assertNotNull(key);

		twap = new Twap();
		twap.setSymbol("INTC");
		twap.setTwap1(0d);
		twap.setTwap5(0d);
		twap.setTwap30(0d);
		twap.setTwap60(0d);

		Calendar calendar = Calendar.getInstance();
		calendar.set(2012, 10, 1, 3, 30, 0);

		twap.setTimestamp(calendar.getTime().getTime());
		key = TwapRedisService.generateKey(twap);

		long time = calendar.getTimeInMillis();
		assertEquals("TWAP:INTC:" + time, key);
	}

	@Test
	public void testCreate1() {
		Twap twap = new Twap("INTC", 0d, 0d, 0d, 0d, new Date().getTime());
		String key = service.create(twap);

		Twap twap2 = service.read(key);
		assertEquals(twap, twap2);
	}

	@Test
	public void testCreate2() {
		Twap twap = new Twap("INTC", 21.45d, 0d, 0d, 0d, new Date().getTime());
		String key = service.create(twap);

		Twap twap2 = service.read(key);
		assertEquals(twap, twap2);
	}

	@Test
	public void testDelete0() {
		Twap twap = new Twap("INTC", 21.45d, 0d, 0d, 0d, new Date().getTime());

		String key = service.create(twap);
		service.delete(twap);

		Twap twap2 = service.read(key);
		assertEquals("", twap2.symbol);
	}

	@Test
	public void testPublish() {
		Twap twap = new Twap("INTC", 21.45d, 0d, 0d, 0d, new Date().getTime());

		service.publishKey(twap);
		// TODO Assert message received
	}

}
