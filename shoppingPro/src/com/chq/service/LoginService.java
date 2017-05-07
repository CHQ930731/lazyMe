package com.chq.service;

import com.chq.dao.LoginDao;
import com.chq.entity.UserInfo;

public class LoginService {
	
	private LoginDao ld = new LoginDao();
	
	public boolean isLoginSuc(UserInfo ui) {
		return ld.judgeLogin(ui);
	}
}
