package com.sund.test.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sund.test.dao.TestJpaDao;

@Service
public class TestJpaService {
	@Resource
	private TestJpaDao testJpaDao;
	public void testInsertData() {
		testJpaDao.testInsertData();
	}
}
