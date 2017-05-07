package com.chq.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chq.entity.GoodsDTO;
import com.chq.entity.PageBean;
import com.chq.service.PagingService;
import com.chq.util.FinalUtil;

/** 
 * 类体描述： 商品分页Servlet层
 * 
 * @author Chiara
 * 
 */
public class PagingServlet extends HttpServlet {
	/**
	 * 方法描述：以get方式提交分页查询表单时执行该方法
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
	 * 方法描述： 以post方式提交分页查询表单时执行该方法
	 * 
	 * @param request        
	 * @param response     
	 * @throws ServletException
	 * @throws IOException
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*获取页面信息*/
		request.setCharacterEncoding("UTF-8");
		String curPage = request.getParameter("curPage");//获取当前页
		System.out.println(curPage);
		String pageSize = request.getParameter("pageSize");//获取页面数据
		String goodsName = request.getParameter("searchInput");//搜索条件
//		System.out.println(goodsName);
		
		int curPageNum = 1;
		if(curPage != null) {
			try{
				curPageNum = Integer.parseInt(curPage);
			}catch(Exception e) {
				//当前页面还是等于1
			}
		}
		
		int curPageSize = FinalUtil.PAGE_SIZE;
		if(pageSize != null) {
			try{
				curPageNum = Integer.parseInt(pageSize);
			}catch (Exception e) {
				//默认为每页5条
			}
		}
		
		
		/*封装*/
		PageBean pb = new PageBean();
		pb.setCurPage(curPageNum);
		pb.setPageSize(curPageSize);
		
		//设置DTO对象
		GoodsDTO gd = new GoodsDTO();
		if(goodsName != null) {
			gd.setGoodsName(goodsName);
		}
		//设置查询条件对象
		pb.setObj(gd);
		
		/*交给service层处理，返回完整的pageBean*/
		PagingService gs = new PagingService();
		pb = gs.getPageBean(pb);
//		System.out.println(pb.getPageCount());
		/*把pb作为属性*/
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("home/shopcart.jsp").forward(request, response);
		
	}

}
