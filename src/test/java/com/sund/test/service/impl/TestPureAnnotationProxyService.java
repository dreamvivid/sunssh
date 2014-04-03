package com.sund.test.service.impl;

import org.springframework.stereotype.Service;

@Service
public class TestPureAnnotationProxyService {
	public void testTx() {
		System.out.println("Test tx...");
	}
}
