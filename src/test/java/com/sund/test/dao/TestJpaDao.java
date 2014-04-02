package com.sund.test.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestJpaDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private JdbcTemplate jdbcTemplate2;

	public void testInsertData() {
		jdbcTemplate.execute("insert into TEST_JDBC(id, name) values(88,88)");
		jdbcTemplate2.execute("insert into TEST_JDBC(id, name) values(88,88)");
	}

}
