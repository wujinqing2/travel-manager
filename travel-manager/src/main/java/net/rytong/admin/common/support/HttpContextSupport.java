package net.rytong.admin.common.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.rytong.admin.common.service.BaseSessionKeyConstant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 获取HttpServletRequest, HttpServletResponse, HttpSession对象
 * 
 * @author wu.jinqing
 * @date 2015年6月8日
 */

@Component
public class HttpContextSupport{
	protected final Logger logger = LogManager.getLogger(getClass());
	
	/**
	 * If you use a Servlet 2.5 web container, with requests processed 
	 * outside of Spring’s DispatcherServlet (for example, when using JSF or Struts), 
	 * you need to register the org.springframework.web.context.request.RequestContextListener 
	 * ServletRequestListener. For Servlet 3.0+, this can done programmatically via the 
	 * WebApplicationInitializer interface. 
	 * 
	 * @author wu.jinqing
	 * @date 2015年6月8日
	 */
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	private HttpSession session;
	
	/**
	 * <pre>
	 * 获取HttpServletRequest对象
	 * <pre>
	 * 
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getReq()
	{
		return request;
	}
	
	/**
	 * <pre>
	 * 获取HttpServletResponse对象
	 * <pre>
	 * 
	 * @return HttpServletResponse
	 */
	public HttpServletResponse getRes()
	{
		return response;
	}
	
	/**
	 * <pre>
	 * 获取HttpSession对象
	 * <pre>
	 * 
	 * @return HttpSession
	 */
	public HttpSession getSession()
	{
		return session;
	}
	
	/**
	 * <pre>
	 * 获取USER_ID对象
	 * <pre>
	 * 
	 * @return HttpSession
	 */
	public Long getUserId()
	{
		return (Long)session.getAttribute(BaseSessionKeyConstant.USER_ID);
	}
}
