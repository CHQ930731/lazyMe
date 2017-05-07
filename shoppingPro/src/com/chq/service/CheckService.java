package com.chq.service;

import com.chq.dao.CheckDao;

/**
 * 类描述：校验用户名是否存在
 * @author Chiara
 *
 */
public class CheckService {
	
	private CheckDao cd = new CheckDao();
	//只存在一个用户名，无需封装
	public boolean isExitUser(String name) {
		
		return cd.JudgeCheckUser(name);
	}
}
