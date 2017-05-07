package com.chq.entity;

/**
 * @author Chiara 商品信息实体类
 * 
 */
public class GoodsInfo {
	private int goodsId;
	private String goodsPic;
	private String goodsName;
	private String goodsType;
	private double goodsPrice;
	private String goodsStock;

	/**
	 * @return
	 */
	public int getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId 商品id
	 */
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * @return
	 */
	public String getGoodsPic() {
		return goodsPic;
	}

	/**
	 * @param goodsPic 商品图片地址
	 */
	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
	}

	/**
	 * @return
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/**
	 * @param goodsName 商品名字
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/**
	 * @return
	 */
	public String getGoodsType() {
		return goodsType;
	}

	/**
	 * @param goodsType 商品类别
	 */
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	/**
	 * @return
	 */
	public double getGoodsPrice() {
		return goodsPrice;
	}

	/**
	 * @param goodsPrice 商品价格
	 */
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	/**
	 * @return
	 */
	public String getGoodsStock() {
		return goodsStock;
	}

	/**
	 * @param goodsStock 商品库存
	 */
	public void setGoodsStock(String goodsStock) {
		this.goodsStock = goodsStock;
	}

}
