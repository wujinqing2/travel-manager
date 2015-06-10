package net.rytong.admin.sys.controller;

import javax.servlet.http.HttpServletRequest;

import net.rytong.admin.common.controller.BaseController;
import net.rytong.admin.sys.entity.SysCode;
import net.rytong.admin.sys.service.SysCodeService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SysCodeController extends BaseController{
	private final Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	private SysCodeService sysCodeService;
	
	@RequestMapping("/add")
	public String add(SysCode entity, Model model)
	{
		sysCodeService.save(entity);
		
		model.addAttribute("entity", entity);
		
		return "sysCodeList";
	}
	
	@RequestMapping("/load")
	public String load(Long id, Model model)
	{
		HttpServletRequest req = getReq();
		String i = (String)req.getParameter("id");
		getRes();
		getSession();
		SysCode entity = sysCodeService.load(id);
		
		model.addAttribute("entity", entity);
		
		return "sysCodeList";
	}
}
