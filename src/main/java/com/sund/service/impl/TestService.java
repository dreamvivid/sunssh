package com.sund.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.sund.dao.ITestDao;
import com.sund.service.ITestService;
@Service("testService")
public class TestService implements ITestService {
	@Resource(name="testDao")
	private ITestDao testDao;
	public String testServiceMethod() {
			//call dao
			//get result
			return "Call dao with annotation successfully";
	}
	
	public String callServiceMethod() {
			return testDao.callDaoMethod();
	}

	public ITestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(ITestDao testDao) {
		this.testDao = testDao;
	}
	
}
