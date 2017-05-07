<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
	<head lang="en">
		<base href="<%=basePath%>">
		<%--important --%>
		<meta charset="UTF-8">
		<title>注册</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-siteapp" />

		<link rel="stylesheet" href="AmazeUI-2.4.2/assets/css/amazeui.min.css" />
		<link href="css/dlstyle.css" rel="stylesheet" type="text/css">
		<script src="AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
		<script src="AmazeUI-2.4.2/assets/js/amazeui.min.js"></script>
		<%--导入jQuery包 --%>
		<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript">
			var base = '<%=basePath%>';
		</script>
		<%-- jQuery校验 --%>
		<script type="text/javascript">
			$(function() {
				$(".pwdMsg").html("");
				$(".userMsg").html("");
				//当鼠标点到输入用户名框时，隐藏提示消息，当鼠标移开时，进行校验，提示消息
				$("#user").focus(function() {$(".userMsg").html("");}).blur(checkUser);
				
				//当鼠标点到输入密码框时，隐藏提示消息
				$("#password").focus(function() {$(".pwdMsg").html("");}).blur(checkPwd);
				//当鼠标点到确认密码框时，隐藏提示消息，当鼠标移开确认密码框时，提示消息
				$("#passwordRepeat").focus(function() {$(".pwdMsg").html("");}).blur(checkPwd);
				
				
			})
			//确认密码验证 点击注册按钮时
			function checkInfo() {
				if (checkPwd() && checkUser() && checkAgree() ){
					return true;
				}
				return false;
			}
			/*校验密码*/
			function checkPwd() {
				var passwordVal = $("#password").val();
				var passwordRepeatVal = $("#passwordRepeat").val();
				var pwdRg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$/;
				if(passwordVal.trim() == "") {
					$(".pwdMsg").html("<span style='color:red; font-size:9px;'>输入密码不能为空！</span>");
					return false;
				}
				if(!passwordVal.match(pwdRg)) {
					$(".pwdMsg").html("<span style='color:red; font-size:9px;'>密码必须由6-20字母和数字组成！</span>");
					return false;
				}
				if(passwordRepeatVal.trim() == "") {
					$(".pwdMsg").html("<span style='color:red; font-size:9px;'>确认密码不能为空！</span>");
					return false;
				}
				if(passwordVal == passwordRepeatVal) {
					$(".pwdMsg").html("<span style='color:green;font-size:9px;'>密码一致！</span>");
					return true;
				} else {
					$(".pwdMsg").html("<span style='color:red;font-size:9px;'>密码不一致！</span>");
					return false;
				}
			}
			/*校验用户名*/
			function checkUser() {
				var userVal = $("#user").val();
				var emailRg = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
				if(userVal.trim() == "") {
					$(".userMsg").html("<span style='color:red; font-size:9px;'>用户名不能为空！</span>");
					return false;
				}
				if(!userVal.match(emailRg)) {
					$(".userMsg").html("<span style='color:red; font-size:9px;'>您输入的邮箱格式有误！</span>");
					return false;
				}
				if(userVal.length > 20) {
					$(".userMsg").html("<span style='color:red; font-size:9px;'>您输入的邮箱长度不能超过20！</span>");
					return false;
				}
				//以上两者都通过，发送ajax请求
				//ajax默认是异步，我们这边需要同步，得到结果再去判断是否邮箱已被注册
				var isExit = null;
				$.ajax({
					url:base + 'checkReq',//绝对路径
					data:{uname:userVal.trim()},
					async:false,
					type:'post',
					success:function(result) {
						isExit = result;
					}
				});
				if(isExit == 'true') {
					$(".userMsg").html("<span style='color:red; font-size:9px;'>该邮箱已被注册！</span>");
					return false;
				}
				return true;
			}
			/*校验复选框*/
			function checkAgree() {
				if($('#reader-me').is(':checked')) {
 					return true
				}
				return false;
			}
			
		</script>

	</head>

	<body>

		<div class="login-boxtitle">
			<a href="home/home.jsp"><img alt="logo" src="images/logobig.png" />
			</a>
		</div>

		<div class="res-banner">
			<div class="res-main">
				<div class="login-banner-bg">
					<img src="images/big.jpg" />
				</div>
				<div class="login-box">
					<div class="am-tabs" id="doc-my-tabs">
						<h3 class="title" >
							注册
						</h3>
						<div class="login-links">
							<a href="home/login.jsp" class="zcnext am-fr am-btn-default">登录</a>
						</div>
						<%-- **********************邮箱注册**************************** --%>
						<div class="am-tabs-bd">
							<form action="registerReq" method="post" onsubmit="return checkInfo()">
								<div class="am-tab-panel am-active">
									<div class="test">
										<%-- 用户名 div --%>
										<div class="user-email">
											<%-- 用户名 标签 --%>
											<label for="email">
												<i class="am-icon-envelope-o"></i>
											</label>
											<%-- 用户名 输入框 --%>
											<input type="text" name="username" id="user" placeholder="请输入邮箱账号">
										</div>
										<div class="userMsg">
										</div>
										<%-- 密码 div --%>
										<div class="user-pass">
											<%-- 密码 标签 --%>
											<label for="password">
												<i class="am-icon-lock"></i>
											</label>
											<%-- 密码 输入框 --%>
											<input type="password" name="password" id="password"
												placeholder="设置密码">
										</div>
										<%-- 确认密码 div --%>
										<div class="user-pass">
											<%-- 确认密码 标签 --%>
											<label for="passwordRepeat">
												<i class="am-icon-lock"></i>
											</label>
											<%-- 确认密码 输入框--%>
											<input type="password" name="" id="passwordRepeat"
												placeholder="确认密码">
										</div>
										<%--消息提示 --%>
										<div class="pwdMsg">

										</div>
										<%--接收注册失败的消息并显示在页面上 --%>
										<div class="registerInfo">
											<%
												String registerInfo = (String) request.getAttribute("registerFail");
												if (registerInfo != null) {
											%>
											注册失败！
											<%
												}
											%>
										</div>
									</div>
									<%-- 已阅复选框  --%>
									<div class="login-links">
										<label for="reader-me">
											<input id="reader-me" type="checkbox">
											点击表示您同意商城《服务协议》
										</label>
									</div>
									<%-- 提交form表单 确认注册 --%>
									<div class="am-cf">
										<input type="submit" name="" value="注册"
											class="am-btn am-btn-primary am-btn-sm am-fl"/>
									</div>
										
								</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="footer ">
			<div class="footer-hd ">
				<p>
					<a href="# ">恒望科技</a>
					<b>|</b>
					<a href="# ">商城首页</a>
					<b>|</b>
					<a href="# ">支付宝</a>
					<b>|</b>
					<a href="# ">物流</a>
				</p>
			</div>
			<div class="footer-bd ">
				<p>
					<a href="# ">关于恒望</a>
					<a href="# ">合作伙伴</a>
					<a href="# ">联系我们</a>
					<a href="# ">网站地图</a>
					<em>© 2015-2025 Hengwang.com 版权所有. 更多模板 <a
						href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a>
						- Collect from <a href="http://www.cssmoban.com/" title="网页模板"
						target="_blank">网页模板</a> </em>
				</p>
			</div>
		</div>
	</body>

</html>
