package com.sund.test.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.sund.test.service.ITestAnnotationTxService;
import com.sund.test.service.impl.TestAnnotationTxCglibProxyService;

@Controller("testAnnotationTxAction")
public class TestAnnotationTxAction {
	@Resource(name="testAnnotationTxService")
	private ITestAnnotationTxService service;
	@Resource(name="testAnnotationTxCglibProxyService")
	private TestAnnotationTxCglibProxyService cglibProxyService;
	
	public void testTx() {
		service.testTx();
	}
}
