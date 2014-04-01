package com.sund.dao.impl;

import org.springframework.stereotype.Repository;

import com.sund.dao.ITestDao;
@Repository("testDao")
public class TestDao implements ITestDao {
		public String callDaoMethod() {
			return "Call dao method successful!";
		}
}
