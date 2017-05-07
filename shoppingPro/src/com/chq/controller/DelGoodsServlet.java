package com.chq.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chq.service.PagingService;

public class DelGoodsServlet extends HttpServlet {

	/**
	 * 方法描述： 以get方式删除商品数据时执行该方法
	 * 
	 * @param request        
	 * @param response     
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取参数
		String gId = request.getParameter("gId");
		System.out.println(gId);
		//交给service层处理
		PagingService ps = new PagingService();
		boolean result = ps.isDelGoods(gId);
		System.out.println(result);
		//根据service处理结果进行页面跳转
		if(result) {
			request.getRequestDispatcher("pagingReq").forward(request, response);
		}
		
	}

	/**
	 * 方法描述： 以post方式删除商品数据时执行该方法
	 * 
	 * @param request        
	 * @param response     
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
