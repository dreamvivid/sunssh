package com.sund.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sund.test.action.TestAnnotationTxAction;
import com.sund.test.service.ITestAnnotationProxyService;
import com.sund.test.service.impl.TestAnnotationTxCglibProxyService;
import com.sund.test.service.impl.TestPureAnnotationProxyService;

public class TestAnnotationTx {
	@Test
	public void testAnnotationTx() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("txAnnatation_applicationContext.xml");
		TestAnnotationTxAction action = (TestAnnotationTxAction) ctx.getBean("testAnnotationTxAction");
		action.testTx();
	}
	
	@Test
	public void testProxy() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("txAnnatation_applicationContext.xml");
		ITestAnnotationProxyService jdk = (ITestAnnotationProxyService) ctx.getBean("testAnnotationProxyServiceIsJdk");
		TestAnnotationTxCglibProxyService cglib = (TestAnnotationTxCglibProxyService) ctx.getBean("testAnnotationProxyServiceIsCglib");
		cglib.testTx();
		jdk.testTx();
		System.out.println("ok--"+jdk+"//"+cglib);
	}
	
	@Test
	public void testPureAnnotation() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("txAnnatation_applicationContext.xml");
		TestPureAnnotationProxyService cglib = (TestPureAnnotationProxyService) ctx.getBean("testPureAnnotationProxyService");
		cglib.testTx();
	}
}
