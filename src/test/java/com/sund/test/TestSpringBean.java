package com.sund.test;

import org.junit.Test;
import com.sund.common.CommonBean;
import com.sund.firstest.TestBean;

public class TestSpringBean {
	@Test
	public void testBean() {
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-bean-test.xml");
//		TestBean bean = (TestBean) ctx.getBean("testBean");
		TestBean bean = CommonBean.getBean("testBean", TestBean.class);
		if(bean!=null) 
			System.out.println(bean.saySth());
		
	}
}
