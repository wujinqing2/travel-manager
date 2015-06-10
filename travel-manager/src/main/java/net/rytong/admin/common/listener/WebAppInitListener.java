package net.rytong.admin.common.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebAppInitListener implements ServletContextListener {
	private static String REAL_PATH = null;

	private static ServletContext ctx = null;

	@Override
	public void contextDestroyed(ServletContextEvent event) {

		setCtx(event.getServletContext());

		System.out.println(">>>>> Context Destroyed");

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		setCtx(event.getServletContext());

		String realPath = getCtx().getRealPath(File.separator);

		setREAL_PATH(realPath);

		System.out.println(">>>>> web path is:" + realPath);

	}

	public static void setREAL_PATH(String rEAL_PATH) {
		REAL_PATH = rEAL_PATH;
	}

	public static String getREAL_PATH() {
		return REAL_PATH;
	}

	public static void setCtx(ServletContext ctx) {
		WebAppInitListener.ctx = ctx;
	}

	public static ServletContext getCtx() {
		return ctx;
	}
}
