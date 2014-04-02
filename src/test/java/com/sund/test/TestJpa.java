package com.sund.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sund.test.service.TestJpaService;

public class TestJpa {
	@Test
	public void testJpa() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("jta_applicationContext.xml");
		TestJpaService service = (TestJpaService) ctx.getBean("testJpaService");
		service.testInsertData();
	}
}
