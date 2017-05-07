package com.chq.dao;

import java.util.List;
import java.util.Map;

import com.chq.entity.UserInfo;
import com.chq.util.JDBCUtil;

public class LoginDao {
	
	public boolean judgeLogin(UserInfo ui) {
		boolean result = false;
		String username = ui.getUsername();
		String password = ui.getPassword();
		String sql = "select * from user_info where user_name='" + username + "' and user_pwd='" + password + "'";
		List<Map<String,Object>> userList = JDBCUtil.doQuery(sql);
//		System.out.println(userList.toString());
		if(userList != null && userList.size() == 1) {
			result = true;
		}
		return result;
	}
}
