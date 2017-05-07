package com.chq.dao;

import java.util.List;
import java.util.Map;

import com.chq.util.JDBCUtil;

public class CheckDao {
	
	public boolean JudgeCheckUser(String name) {
		boolean result = false;
		String sql = "select * from user_info where user_name='"+name+"'";
		List<Map<String,Object>> userList = JDBCUtil.doQuery(sql);
		System.out.println(userList);
		if(userList != null && userList.size() == 1) {
			result = true;
		}
		System.out.println("checkDao" + result);
		return result;
	}
}
