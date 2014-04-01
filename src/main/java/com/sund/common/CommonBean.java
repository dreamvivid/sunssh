package com.sund.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CommonBean implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	private static CommonBean instance;

	private static Object getBean(String beanName) {
		return CommonBean.applicationContext.getBean(beanName);
	}
	
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return clazz.cast(getBean(beanName));
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		CommonBean.applicationContext = applicationContext;
	}

	public static CommonBean getInstance() {
		return instance;
	}
	
	private void commonBeanInit() {
		instance = this;
	}
}
