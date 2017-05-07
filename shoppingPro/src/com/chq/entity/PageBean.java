package com.chq.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chq.util.FinalUtil;

/**
 *@author Chiara 类体描述： 分页实体类
 * 
 */
public class PageBean {
	private int curPage = 1;// 当前页面
	private int pageSize = FinalUtil.PAGE_SIZE;// 每页容量
	private int goodsCount;// 商品种类总量
	private List<Map<String, Object>> curGoodsContext = new ArrayList<Map<String, Object>>();// 商品内容

	private int pageCount;//页面数量
	
	private Object obj = new Object();//查询条件数据

	/**
	 * @return 当前页面值
	 */
	public int getCurPage() {
		return curPage;
	}

	/**
	 * @param 设置当前页面值
	 */
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	/**
	 * @return 
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return 商品品种数目
	 */
	public int getGoodsCount() {
		return goodsCount;
	}

	/**
	 * @param goodsCount
	 */
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}

	/**
	 * @return 当前页面商品内容
	 */
	public List<Map<String, Object>> getCurGoodsContext() {
		return curGoodsContext;
	}

	/**
	 * @param goodsContext
	 */
	public void setCurGoodsContext(List<Map<String, Object>> curGoodsContext) {
		this.curGoodsContext = curGoodsContext;
	}

	/**
	 * @return 页面数量
	 */
	public int getPageCount() {
		int temp = goodsCount / pageSize;
		pageCount = goodsCount % pageSize == 0 ? temp : (temp+1);
		return pageCount;
	}

	/**
	 * @return
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * @param obj
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	

}
