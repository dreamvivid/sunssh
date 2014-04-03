package com.sund.test.service.impl;

import org.springframework.stereotype.Service;
@Service("testAnnotationTxCglibProxyService")
public class TestAnnotationTxCglibProxyService {

	public void testTx() {
		System.out.println("Test tx...");
	}

}
