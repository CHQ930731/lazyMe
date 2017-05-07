package com.chq.service;

import com.chq.dao.RegisterDao;
import com.chq.entity.UserInfo;

public class RegisterService {
	private RegisterDao rs = new RegisterDao();

	public boolean isRegisterSuc(UserInfo ui) {

		return rs.judgeRegister(ui);

	}

}
