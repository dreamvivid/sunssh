package com.sund.firstest;

import org.apache.log4j.Logger;

import com.sund.common.CommonBean;

public class TestBean {
	private static final Logger log = Logger.getLogger(TestBean.class);
	private String property;

	public String saySth() {
		CommonBean instance = CommonBean.getInstance();
		log.debug("###########################first log..................");
		return "Hello World!";
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
