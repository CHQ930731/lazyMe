package com.chq.dao;

import java.util.List;
import java.util.Map;

import com.chq.entity.GoodsDTO;
import com.chq.entity.PageBean;
import com.chq.util.JDBCUtil;

public class PagingDao {
	public PageBean getPageBean(PageBean pb) {
		int curPageNum = pb.getCurPage();
		int curPageSize = pb.getPageSize();
		
		int min = curPageSize*(curPageNum - 1);
		int max = curPageSize*curPageNum;
		
		StringBuffer whereSql = new StringBuffer(" where 1 = 1 ");
		GoodsDTO goodsDTO = (GoodsDTO) pb.getObj();
		if(goodsDTO.getGoodsName() != null) {
			whereSql.append(" and goods_name like '%" + goodsDTO.getGoodsName() + "%'");
		}
		
		//sql语句查询当前页面的商品数据
		String sql = "select t.* from (select rownum row_index, g.* from goods_info g "+ whereSql + ") t where row_index <= "+ max +" and row_index > "+ min;
		
		//交给JDBC
		List<Map<String,Object>> goodsLists = JDBCUtil.doQuery(sql); 
		pb.setCurGoodsContext(goodsLists);
		
		//sql语句查询总商品数目
		String sql2 = "select count(*) counts from goods_info" + whereSql;
		List<Map<String,Object>> totalCount = JDBCUtil.doQuery(sql2); 
		int goodsCount = Integer.parseInt(totalCount.get(0).get("COUNTS").toString());
		
		pb.setGoodsCount(goodsCount);
		
		return pb;
	}
	
	public boolean doDelGoods(String gId) {
		boolean result = false;
		String sql = "delete from goods_info where goods_id in ( " + gId +" )";
		result = JDBCUtil.doUpdate(sql);
		return result;
	}

}
