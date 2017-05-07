package com.chq.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chq.service.CheckService;

/**
 * 类体描述： 用户名校验Servlet层 从注册页面获取用户名参数，把它交给service层进行处理，根据获取的处理结果并
 * 
 * @author Chiara
 * 
 */
public class CheckServlet extends HttpServlet {

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("uname");
		CheckService cs = new CheckService();
		boolean result = cs.isExitUser(name);
		System.out.println("check" + result);
		// ajax不需要进行页面，只需要数据
		PrintWriter out = response.getWriter();
		out.print(result);

		out.flush();
		out.close();
	}

}
