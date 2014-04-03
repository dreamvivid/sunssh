package com.sund.test.aop;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component("testAspect")
public class TestAspect {
	public void doBefore(JoinPoint joinPoint) {
		System.out.println("Do before...");
	}
	public void doAfter(JoinPoint joinPoint) {
		System.out.println("Do after...");
	}
}
