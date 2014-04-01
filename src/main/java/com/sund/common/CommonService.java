package com.sund.common;

import org.springframework.beans.factory.InitializingBean;

import com.sund.service.ITestServiceWithoutAnnotation;

public class CommonService implements ICommonService, InitializingBean {
	private static CommonService instance;
	private ITestServiceWithoutAnnotation testService;

	public static CommonService getInstance() {
		return instance;
	}
	public ITestServiceWithoutAnnotation getTestService() {
		return testService;
	}

	public void setTestService(ITestServiceWithoutAnnotation testService) {
		this.testService = testService;
	}
	
	public void afterPropertiesSet() throws Exception {
		instance = this;
	}

}
