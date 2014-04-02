package com.sund.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sund.service.ITestService;

public class TestTx {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ITestService testService = (ITestService) ctx.getBean("testTxService");
		testService.insertTestTx();
	}

}
