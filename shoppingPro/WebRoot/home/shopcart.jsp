<%@ page language="java" import="java.util.*,com.chq.entity.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>购物车页面</title>
		<link href="AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet"
			type="text/css" />
		<link href="basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link href="css/cartstyle.css" rel="stylesheet" type="text/css" />
		<link href="css/optstyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js">
		</script>
		<script type="text/javascript">
			var base='<%=basePath%>';
		</script>
		
		<!-- 获取每页多少条并将其放到form表单的hidden里面 -->
		<script type="text/javascript">
			//输入页面进行跳转
			function getCurPage(curPage,pageCount){
				var cp = $("#curPage").val();
				if(cp <= pageCount  && cp > 0) {
					location.href=base +"pagingReq?curPage="+cp;
				}
				else {
					alert("您输入的页码不存在！");
				}
			}; 
			//单条数据删除
			function isDel(gId,gName) {
				if(confirm("你确定删除“"+gName+"”该条数据吗？")) {
					location.href = base + "delGoodsReq?gId="+gId;
				}
			}
			//全选和反选
			function selectAll(obj) {
				if($(obj).is(':checked')) {
					$("input[name='selectItem']").attr("checked","true");//全选
				}else {
					$("input[name='selectItem']").removeAttr("checked");//取消全选
				}
			}
			
			//单选判断是否此时达到了全选或者未全选
			//每次勾选循环所有selectItem标签
			function selItem(obj) {
				//获取全选复选框
				var selectAll = $("input[name='selectAll']"); 
				if(!$(obj).is(':checked')) {
					selectAll.removeAttr("checked");//若该对象未选中，则全选复选框一定未选中
					return;
				}
				//遍历所有单选框
				var totalItems = $("input[name='selectItem']").size();//所有单选框的数量
				var checkedItems = $("input[name='selectItem']:checked").size();//所有被选中的单选框的个数(:checked紧连，中间不能有空格 两者相差意义巨大)
				if(totalItems == checkedItems) {
					selectAll.attr("checked","true");
				};
			}
			
			//全选数据删除
			function delAll() {
				var checkedItems = $("input[name='selectItem']").filter(":checked");
				if(!checkedItems.size() > 0) {
					alert("请选择需要删除的数据！");
					return;
				}
				if(confirm("你确定删除勾选的数据吗？")) {
					
					var result = "";
					checkedItems.each(function(){
						result += "," + $(this).val();
					}); 
					result = result.substring(1);
					
					location.href = base + "delGoodsReq?gId="+result;
				};
			}
			
		</script> 
	</head>

	<body>
		<!--顶部导航条 -->
		<div class="am-container header">
			<!--顶部导航之“登录”“注册”-->
			<ul class="message-l">
				<div class="topMessage">
					<div class="menu-hd">
						<%
							String name = (String) session.getAttribute("loginName");
							if (name != null) {
						%>
						用户 &nbsp;
						<%=name%>
						<%
							} else {
						%>
						<a href="home/login.jsp" target="_top" class="h">亲，请登录</a>
						<a href="home/register.jsp" target="_top">免费注册</a>
						<%
							}
						%>
					</div>
				</div>
			</ul>
			<!--顶部导航之“商城首页”“个人中心”“购物车”“收藏夹”-->
			<ul class="message-r">
				<div class="topMessage home">
					<div class="menu-hd">
						<a href="home/home.jsp" target="_top" class="h">商城首页</a>
					</div>
				</div>
				<div class="topMessage my-shangcheng">
					<div class="menu-hd MyShangcheng">
						<a href="person/personalCenter.jsp" target="_top"><i
							class="am-icon-user am-icon-fw"></i>个人中心</a>
					</div>
				</div>
				<div class="topMessage mini-cart">
					<div class="menu-hd">
						<a id="mc-menu-hd" href="pagingReq" target="_top"><i
							class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong
							id="J_MiniCartNum" class="h">0</strong> </a>
					</div>
				</div>
				<div class="topMessage favorite">
					<div class="menu-hd">
						<a href="person/collection.jsp" target="_top"><i
							class="am-icon-heart am-icon-fw"></i><span>收藏夹</span> </a>
					</div>
				</div>
			</ul>
		</div>

		<!--悬浮搜索框-->

		<div class="nav white">
			<div class="logo">
				<img src="images/logo.png" />
			</div>
			<div class="logoBig">
				<li>
					<img src="images/logobig.png" />
				</li>
			</div>

			<div class="search-bar pr">
				<a name="index_none_header_sysc" href="#"></a>
				
				<form action="pagingReq" method="post" onsubmit="getPageSize()">
					<input type="text" id="searchInput" name="searchInput" placeholder="搜索" autocomplete="off" value="${pb.obj.goodsName}"/>
					<input type="submit" id="ai-topsearch" class="submit am-btn" value="搜索" index="1"/>
				</form>
			</div>
		</div>

		<div class="clear"></div>
		<!---------------------------------------------以下是购物车-------------------------------------------------->
		<div class="concent">
			<div id="cartTable">
				<tr class="item-list">
					<div class="bundle  bundle-last ">
					
						<!-- *****************页面操作栏****************** -->
						<div class="bundle-hd">
							<div class="bd-promos">
								<div class="act-promo">
									<table>
										<tr>
											<td>
												<a href="pagingReq?curPage=1">&lt;&lt;首页&nbsp;&nbsp;</a>
											</td>
											<td>
												<a href="pagingReq?curPage=${pb.curPage - 1 > 0? pb.curPage -1 : 1}">&lt;上一页&nbsp;&nbsp;</a>
											</td>
											<td>
												<a href="pagingReq?curPage=${pb.curPage + 1 > pb.pageCount ? pb.pageCount : pb.curPage + 1}">下一页&gt;&nbsp;&nbsp;</a>
											</td>
											<td>
												<a href="pagingReq?curPage=${pb.pageCount}">末页&gt;&gt;&nbsp;&nbsp;</a>
											</td>
											<%-- <td>
												每页
												<select name="pageSize" id="pageSize">
													<option value="5" <c:if test="${pb.pageSize == 5}"> select="selected"</c:if> >5条</option>
													<option value="10" <c:if test="${pb.pageSize == 10}"> select="selected"</c:if> >10条</option>
													<option value="15" <c:if test="${pb.pageSize == 15}"> select="selected"</c:if> >15条</option>
												</select>
											</td>  --%>
											<td>
												&nbsp;&nbsp;当前第 ${pb.curPage}页/共 ${pb.pageCount == 0 ? 1 : pb.pageCount } 页
											</td>
											<td>
												&nbsp;&nbsp;到
												<input type="text" size="1" name="curPage" id="curPage"/>页
												&nbsp;
												<%
												PageBean pbb=(PageBean)(request.getAttribute("pb"));
												int curPage=pbb.getCurPage();
												int pageCount=pbb.getPageCount();
												%>
												<button><a onclick="getCurPage(<%=curPage%>,<%=pageCount%>)">确&nbsp;定</a></button>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
						<!-- *****************页面操作栏****************** -->
						
						<div class="clear"></div>
						
						<!-- *****************标题栏****************** -->
						<div class="cart-table-th">
							<div class="wp">
								<div class="th th-chk">
									<div id="J_SelectAll1" class="select-all J_SelectAll">
									</div>
								</div>
								<div class="th th-item">
									<div class="td-inner">
										商品信息
									</div>
								</div>

								<div class="th th-price">
									<div class="td-inner">
										单价
									</div>
								</div>
								<div class="th th-amount">
									<div class="td-inner">
										数量
									</div>
								</div>
								<div class="th th-sum">
									<div class="td-inner">
										金额
									</div>
								</div>
								<div class="th th-op">
									<div class="td-inner">
										操作
									</div>
								</div>
							</div>
						</div>
						<!-- *****************标题栏****************** -->
						
						<div class="clear"></div>
						
						<!-- *****************循环部分****************** -->
						<c:forEach  items="${pb.curGoodsContext}" var="curObj">
							<div class="bundle-main">
								<ul class="item-content clearfix">
									<%-- 复选框 --%>
									<li class="td td-chk">
										<div class="cart-checkbox ">
											<input class="check" id="J_CheckBox_170037950254"
											type="checkbox" name="selectItem" value="${curObj.GOODS_ID}" onclick="selItem(this)"/>
											<label for="J_CheckBox_170037950254"></label>
										</div>
									</li>
									<%-- 名称 --%>
									<li class="td td-item">
										<div class="item-pic">
											<img src="${curObj.GOODS_PIC}" class="itempic J_ItemImg" />
										</div>
										<div class="item-info">
											<div class="item-basic-info">
												<span title = "${curObj.GOODS_NAME}">
													${curObj.GOODS_NAME}
												</span>
											</div>
										</div>
									</li>
									<%-- 类型 --%>
									<li class="td td-info">
										<div class="item-props item-props-can">
											<span class="sku-line">${curObj.GOODS_TYPE}</span>
										</div>
									</li>
									<%-- 价格 --%>
									<li class="td td-price">
										<div class="item-price price-promo-promo">
											<div class="price-content">
												<em class="J_Price price-now" tabindex="0">
												${curObj.GOODS_PRICE}
												</em>
											</div>
										</div>
									</li>
									<%-- 数量 --%>
									<li class="td td-amount">
										<div class="amount-wrapper ">
											<span>${curObj.GOODS_STOCK}</span>
										</div>
									</li>
									<li class="td td-sum">
										<div class="td-inner">
											<em tabindex="0" class="J_ItemSum number">${curObj.GOODS_PRICE * curObj.GOODS_STOCK}</em>
										</div>
									</li>
									<%-- 删除 --%>
									<li class="td td-op">
										<div class="td-inner">
											<a href="javascript:void(0);" data-point-url="#" class="delete" onclick="isDel('${curObj.GOODS_ID}','${curObj.GOODS_NAME}')">
												删除</a>
										</div>
									</li>
								</ul>
							</div>
						</c:forEach>
						<!-- *****************循环部分****************** -->
						
					</div>
				</tr>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>

			<div class="float-bar-wrapper">
				<div id="J_SelectAll2" class="select-all J_SelectAll">
					<div class="cart-checkbox">
						<input class="check-all check" id="J_SelectAllCbx2"
							name="selectAll" value="true" type="checkbox" onclick="selectAll(this)"/>
						<label for="J_SelectAllCbx2"></label>
					</div>
					<span>全选</span>
				</div>
				<div class="operations">
					<a href="javascript:void(0);" hidefocus="true" class="deleteAll" name="selectAll" onclick="delAll()" >删除</a>
					
				</div>
				<div class="float-bar-right">
					<div class="amount-sum">
						<span class="txt">已选商品</span>
						<em id="J_SelectedItemsCount">0</em><span class="txt">件</span>
						<div class="arrow-box">
							<span class="selected-items-arrow"></span>
							<span class="arrow"></span>
						</div>
					</div>
					<div class="price-sum">
						<span class="txt">合计:</span>
						<strong class="price">¥<em id="J_Total">0.00</em> </strong>
					</div>
					<div class="btn-area">
						<a href="javascript:void(0);" id="J_Go"
							class="submit-btn submit-btn-disabled"
							aria-label="请注意如果没有选择宝贝，将无法结算"> <span>结&nbsp;算</span> </a>
					</div>
				</div>

			</div>

			<div class="footer">
				<div class="footer-hd">
					<p>
						<a href="#">恒望科技</a>
						<b>|</b>
						<a href="#">商城首页</a>
						<b>|</b>
						<a href="#">支付宝</a>
						<b>|</b>
						<a href="#">物流</a>
					</p>
				</div>
				<div class="footer-bd">
					<p>
						<a href="#">关于恒望</a>
						<a href="#">合作伙伴</a>
						<a href="#">联系我们</a>
						<a href="#">网站地图</a>
						<em>© 2015-2025 Hengwang.com 版权所有. 更多模板 <a
							href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a>
							- Collect from <a href="http://www.cssmoban.com/" title="网页模板"
							target="_blank">网页模板</a> </em>
					</p>
				</div>
			</div>

		</div>
		<!---------------------------------------------以上是购物车-------------------------------------------------->
	</body>

</html>