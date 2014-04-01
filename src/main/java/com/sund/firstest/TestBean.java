package com.sund.firstest;

import com.sund.common.CommonBean;

public class TestBean {
	private String property;

	public String saySth() {
		CommonBean instance = CommonBean.getInstance();
		return "Hello World!";
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
