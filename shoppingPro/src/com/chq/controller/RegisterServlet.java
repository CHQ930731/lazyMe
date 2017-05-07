package com.chq.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chq.entity.UserInfo;
import com.chq.service.RegisterService;

/**
 * @author Chiara 类体描述： 注册Servlet层
 * 
 *         从注册页面获取用户名和密码参数，把它交给service层进行处理，根据获取的处理结果进行相应的页面跳转
 */
public class RegisterServlet extends HttpServlet {
	/**
	 * 方法描述： 以get方式提交用户注册表单时执行该方法
	 * 
	 * @param request        
	 * @param response     
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 调用doPost方法
		doPost(request, response);

	}

	/**
	 * 方法描述： 以post方式提交用户注册表单时执行该方法
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
		// 将请求参数封装成对象
		UserInfo ui = new UserInfo();
		ui.setUsername(username);
		ui.setPassword(password);
		// 将对象交给service层处理，得到处理结果
		RegisterService rs = new RegisterService();
		boolean result = rs.isRegisterSuc(ui);
		// 根据处理结果进行相应的页面跳转和页面转发
		if (result) {
			response.sendRedirect("home/login.jsp");
		} else {
			request.setAttribute("registerFail", "fail");
			request.getRequestDispatcher("/home/register.jsp").forward(request,
					response);
		}
	}

}
