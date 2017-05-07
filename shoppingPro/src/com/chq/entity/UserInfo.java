package com.chq.entity;

/**
 * 类体描述： 用户实体类
 * 
 * @author Chiara
 * 
 */
public class UserInfo {

	private String username;// 用户名
	private String password;// 密码

	/**
	 * @return 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            设置用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            设置密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
