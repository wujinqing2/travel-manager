package net.rytong.admin.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.rytong.admin.common.service.BaseSessionKeyConstant;
import net.rytong.admin.common.util.Assert;
import net.rytong.admin.sys.util.CacheUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private final Logger logger = LogManager.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		
		if(session != null)
		{
			Long userId = (Long)session.getAttribute(BaseSessionKeyConstant.USER_ID);
			String token = (String)session.getAttribute(BaseSessionKeyConstant.SECURITY_TOKEN);
			
			if(Assert.isNotNull(userId) && Assert.isNotEmpty(token) && token.equals(CacheUtil.userLoginInfoMap.get(userId)))
				return true;
		}
		
		//如果没有登录, 则重定向到登录页面
		response.sendRedirect(request.getContextPath() + "/login.html");
		
		return false;
	}
}
