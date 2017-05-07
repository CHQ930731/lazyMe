package com.chq.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chq.entity.UserInfo;
import com.chq.service.LoginService;
import com.chq.util.FinalUtil;

/**
 * 类体描述： 登录Servlet层
 * 
 * @author Chiara
 * 
 */
public class LoginServlet extends HttpServlet {
	/**
	 * 方法描述： 以get方式提交登录表单时执行该方法
	 * 
	 * @param request        
	 * @param response     
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * 方法描述： 以post方式提交登录表单时执行该方法
	 * 
	 * @param request        
	 * @param response     
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 封装对象
		UserInfo ui = new UserInfo();
		ui.setUsername(username);
		ui.setPassword(password);
		// 把对象交给service层处理，返回处理结果
		LoginService ls = new LoginService();
		boolean result = ls.isLoginSuc(ui);
		System.out.println(result);
		if (result) {
			String remember = request.getParameter("remember");//查看是否选中记住密码
			if("remember1".equals(remember)) {
				// 新建cookie
				Cookie cookie = new Cookie(FinalUtil.COOKIE_NAME, username
						+ FinalUtil.SPLIT_WORD + password + FinalUtil.OTHERWORD);
				// 设置生命周期
				cookie.setMaxAge(7 * 24 * 3600);
				// 回复添加cookie
				response.addCookie(cookie);
			}
			// 页面跳转
			request.getSession().setAttribute("loginName", username);
			response.sendRedirect("home/home.jsp");
		} else {
			// 请求转发
			request.setAttribute("loginFail", "fail");
			request.getRequestDispatcher("/home/login.jsp").forward(request,
					response);
		}
	}

}
