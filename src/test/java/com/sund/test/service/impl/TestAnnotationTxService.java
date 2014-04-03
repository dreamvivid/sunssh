package com.sund.test.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.sund.test.service.ITestAnnotationTxService;
@Transactional(readOnly=true,isolation=Isolation.DEFAULT)
@Service("testAnnotationTxService")
public class TestAnnotationTxService implements ITestAnnotationTxService{

	public void testTx() {
		System.out.println("Test tx...");
	}

}
