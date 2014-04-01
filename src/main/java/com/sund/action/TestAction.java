package com.sund.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * ����Struts2
 * 
 * @author hh
 * 
 */
public class TestAction extends ActionSupport {

	private static final long serialVersionUID = -7002107930444998001L;
	private static final Logger logger = Logger.getLogger(TestAction.class);

	@Override
	/**
	 *		����Ĭ�Ϸ���
	 */
	public String execute() throws Exception {
		logger.info("Struts2 default mapping is ok!");
		return SUCCESS;
	}

	/**
	 * 		���Գɹ�����
	 * @return
	 * @throws Exception
	 */
	public String success() throws Exception {
		logger.info("Struts2 success mapping is ok!");
		return SUCCESS;
	}

	/**
	 * 		����ʧ�ܷ���
	 * @return
	 * @throws Exception
	 */
	public String error() throws Exception {
		logger.error("Struts error mapping is ok!");
		return ERROR;
	}
}
