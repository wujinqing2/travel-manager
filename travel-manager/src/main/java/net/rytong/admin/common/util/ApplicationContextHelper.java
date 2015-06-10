package net.rytong.admin.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * 获取ApplicationContext对象
 * 
 * @author wu.jinqing
 * @date 2015年4月21日
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {
	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext paramApplicationContext)
			throws BeansException {
		context = paramApplicationContext;
	}
	
	public static <T> T getBean(Class<T> classType)
	{
		return context.getBean(classType);
	}
	
	public static ApplicationContext getApplicationContext()
	{
		return context;
	}
}
