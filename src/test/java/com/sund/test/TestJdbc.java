package com.sund.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestJdbc {
	@Test
	public void testJdbcTemplate() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		JdbcTemplate template = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		List<Map<String, Object>> list = template.queryForList("select * from TEST_JDBC");
		for(Map<String, Object> item : list) {
			System.out.println("id="+item.get("id")+","+"value="+item.get("name"));
		}
	}
}
