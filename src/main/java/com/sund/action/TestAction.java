package com.sund.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sund.common.CommonService;
import com.sund.service.ITestService;
import com.sund.service.ITestServiceWithoutAnnotation;

/**
 * 测试Struts2
 * 
 * @author hh
 * 
 */
@Controller
public class TestAction extends ActionSupport {

	private static final long serialVersionUID = -7002107930444998001L;
	protected static final Logger logger = Logger.getLogger(TestAction.class);
	@Resource(name="testService")
	private ITestService testService;
	/**
	 *		测试默认方法
	 */
	@Override
	public String execute() throws Exception {
		logger.info("Struts2 default mapping is ok!");
		return SUCCESS;
	}

	/**
	 * 		测试成功方法
	 * @return
	 * @throws Exception
	 */
	public String success() throws Exception {
		logger.info("Struts2 success mapping is ok!");
		return SUCCESS;
	}

	/**
	 * 		测试失败方法
	 * @return
	 * @throws Exception
	 */
	public String error() throws Exception {
		logger.error("Struts error mapping is ok!");
		return ERROR;
	}
	
	/**
	 * 		测试action/service/dao分层调用(无注解方式)
	 * @return
	 * @throws Exception
	 */
	public String testMvcWithoutAnnoation() throws Exception {
		ITestServiceWithoutAnnotation testService = CommonService.getInstance().getTestService();
		String result = testService.testServiceMethod();
		System.out.println("################ The result is :  "+result);
		return SUCCESS;
	}
	
	/**
	 * 		测试action/service/dao分层调用(注解方式)
	 * @return
	 * @throws Exception
	 */
	public String testMvc() throws Exception {
			System.out.println("################ The result is :  "+testService.testServiceMethod());
			return SUCCESS;
	}

	public ITestService getTestService() {
		return testService;
	}

	public void setTestService(ITestService testService) {
		this.testService = testService;
	}

}
