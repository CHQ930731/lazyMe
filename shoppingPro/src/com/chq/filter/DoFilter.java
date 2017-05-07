package com.chq.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chq.entity.UserInfo;
import com.chq.service.LoginService;
import com.chq.util.FinalUtil;

/**
 * @author Administrator
 * 
 */
public class DoFilter implements Filter {

	public void destroy() {

	}

	/**
	 * 方法描述： 获取session，如果是同一次会话，直接执行其它过滤链操作；否则遍历cookies
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @exception IOException
	 * @exception ServletException
	 */
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		// 获取session，如果是同一次会话，直接执行其它过滤链操作
		String test = (String) request.getSession().getAttribute("loginName");
		if (test != null) {
			arg2.doFilter(arg0, arg1);
			return;
		}
		
		
		// 否则遍历cookies，查看是否有之前登录保存的cookie
		Cookie[] cookies = request.getCookies();
		// 遍历cookies
		if (cookies != null) {
			for (Cookie ck : cookies) {
				if (FinalUtil.COOKIE_NAME.equals(ck.getName())) {
					// 拆分cookie值
					String cookieVal = ck.getValue();
					String[] cookieVals = cookieVal.split(FinalUtil.SPLIT_WORD);
					if (cookieVals != null && cookieVals.length == 2) {
						String username = cookieVals[0];
						String password = cookieVals[1].substring(0,
								cookieVals[1].length()
										- FinalUtil.OTHERWORD_LENGTH);
						// 封装对象
						UserInfo ui = new UserInfo();
						ui.setUsername(username);
						ui.setPassword(password);
						// 把对象交给service层处理，返回处理结果
						LoginService ls = new LoginService();
						boolean result = ls.isLoginSuc(ui);
						// 根据返回结果进行相应的页面跳转和请求转发
						if (result) {
							request.getSession().setAttribute("loginName", username);
							
						}
					}
				}
			}
		}
		arg2.doFilter(arg0, arg1);

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
