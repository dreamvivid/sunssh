package com.sund.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

public class TestLog4j {
	@Test
	public void testLog4j() {
		PropertyConfigurator.configure("G:\\DevData\\eclipse-workspace\\sundssh\\src\\main\\webapp\\WEB-INF\\log4j.properties");
		Logger logger = Logger.getLogger(TestLog4j.class);
		logger.info("Log successful!");
	}
}
