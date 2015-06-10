package net.rytong.admin.sys.controller;

import net.rytong.admin.common.controller.BaseController;
import net.rytong.admin.sys.entity.User;
import net.rytong.admin.sys.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class UserController extends BaseController {
	private final Logger logger = LogManager.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/add")
	public String add(User user, Model model)
	{
		user.setCreateUserId(getUserId());
		user.setUpdateUserId(getUserId());
		
		userService.save(user);
		
		model.addAttribute("entity", user);
		
		return "sysCodeList";
	}
	

}
