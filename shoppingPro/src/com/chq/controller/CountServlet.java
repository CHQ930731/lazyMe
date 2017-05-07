package com.chq.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext context = getServletContext();
		Integer count = null;
		// 为了避免线程安全的问题，使用synchronized关键字对context对象进行同步
		synchronized (context) {
			count = (Integer) context.getAttribute("counter");
			// 第一次被访问的时候，在上下文对象中还没有保存counter属性，所以获取该属性的值将返回null
			if (null == count) {
				count = new Integer(1);
			} else {
				count = new Integer(count.intValue() + 1);
			}
			context.setAttribute("counter", count);
		}
		//字符编码
		response.setContentType("text/html;charset=gb2312");  
		
		PrintWriter out = response.getWriter();
		out.println("<html><head>");
		out.println("<title>页面访问统计</title>");
		out.println("</head><body>");
		out.println("该页面已被访问了" + "<b>" + count + "</b>" + "次");
		out.println("</body></html>");
		out.close();
	}

}
