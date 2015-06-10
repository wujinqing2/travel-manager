package net.rytong.admin.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.rytong.admin.common.service.BaseSessionKeyConstant;
import net.rytong.admin.common.thread.ThreadHelper;
import net.rytong.admin.common.util.TimeHelper;
import net.rytong.admin.sys.entity.ApiVisitRecord;
import net.rytong.admin.sys.service.ApiVisitRecordService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 接口访问记录
 * 
 * @author wu.jinqing
 * @date 2015年6月9日
 */
public class ApiVisitRecoredInterceptor extends HandlerInterceptorAdapter {
	private final Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	private ApiVisitRecordService apiVisitRecordService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		try
		{
			HttpSession session = request.getSession();
			String contextPath = request.getContextPath();
			
			Long userId = (Long)session.getAttribute(BaseSessionKeyConstant.USER_ID);
			String requestURI = request.getRequestURI().replaceFirst(contextPath, "");
			Long visitTime = TimeHelper.getCurrentTime1();
			String queryString = request.getQueryString();
			String remoteAddr = request.getRemoteAddr();
			
			ApiVisitRecord avr = new ApiVisitRecord();
			
			avr.setUserId(userId);
			avr.setRequestURI(requestURI);
			avr.setVisitTime(visitTime);
			avr.setQueryString(queryString);
			avr.setRemoteAddr(remoteAddr);
			
			ThreadHelper.fixedThreadPool.submit(new ApiVisitRecordThread(avr));
		}catch(Throwable t)
		{
			logger.info("保存接口访问记录失败, 错误信息: {}", t.getMessage());
		}
		
		return true;
	}
	
	public class ApiVisitRecordThread implements Runnable
	{
		private ApiVisitRecord apiVisitRecord;

		public ApiVisitRecordThread(ApiVisitRecord apiVisitRecord) {
			this.apiVisitRecord = apiVisitRecord;
		}

		@Override
		public void run() {
			apiVisitRecordService.save(apiVisitRecord);
		}
	}
}
