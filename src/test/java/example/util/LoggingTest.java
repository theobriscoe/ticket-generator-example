package example.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class LoggingTest {

	private static final Log logger = LogFactory.getLog(LoggingTest.class);

	@Test
	public void test() {
		logger.debug("Debug: Testig the log4jConfigueation");
	}
}
