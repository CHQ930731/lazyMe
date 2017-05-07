package com.chq.dao;

import com.chq.entity.UserInfo;
import com.chq.util.JDBCUtil;

public class RegisterDao {

	public boolean judgeRegister(UserInfo ui) {
		boolean result = false;
		String username = ui.getUsername();
		String password = ui.getPassword();

		String sql = "insert into user_info values (user_seq.nextval,'"
				+ username + "', '" + password + "')";
		result = JDBCUtil.doUpdate(sql);
//		System.out.println(result);
		return result;
	}
}
