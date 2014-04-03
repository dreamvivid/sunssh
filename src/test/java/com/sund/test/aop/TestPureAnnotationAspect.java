package com.sund.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestPureAnnotationAspect {

	@Before("execution(* com.sund.test.service.impl.TestPureAnnotationProxyService.*(..))")
	public void doBefore(JoinPoint joinPoint) {
		System.out.println("Do before...");
	}

	@After("execution(* com.sund.test.service.impl.TestPureAnnotationProxyService.*(..))")
	public void doAfter(JoinPoint joinPoint) {
		System.out.println("Do after...");
	}
}
