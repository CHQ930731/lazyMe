package com.chq.service;

import com.chq.dao.PagingDao;
import com.chq.entity.PageBean;

/**
 * @author Chiara
 *
 */
public class PagingService {
	private PagingDao gd = new PagingDao();
	
	public PageBean getPageBean(PageBean pb) {
		
		return gd.getPageBean(pb);
	}
	
	public boolean isDelGoods (String gId) {
		return gd.doDelGoods(gId);
	}
}
