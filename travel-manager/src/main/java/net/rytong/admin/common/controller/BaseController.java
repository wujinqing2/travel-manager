package net.rytong.admin.common.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.rytong.admin.common.support.HttpContextSupport;


public class BaseController extends HttpContextSupport {
	private final Logger logger = LogManager.getLogger(getClass());
}
