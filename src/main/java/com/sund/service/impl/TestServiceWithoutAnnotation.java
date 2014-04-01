package com.sund.service.impl;

import com.sund.service.ITestServiceWithoutAnnotation;

public class TestServiceWithoutAnnotation implements ITestServiceWithoutAnnotation {
	public String testServiceMethod() {
		//call dao
		//get result
		return "Call dao successfully";
	}
}
