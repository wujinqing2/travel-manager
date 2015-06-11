package net.rytong.admin.sys.ajax;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.rytong.admin.common.ajax.BaseAjaxService;
import net.rytong.admin.common.security.SecurityHelper;
import net.rytong.admin.common.service.BaseSessionKeyConstant;
import net.rytong.admin.common.util.Assert;
import net.rytong.admin.sys.entity.SysUser;
import net.rytong.admin.sys.service.UserService;
import net.rytong.admin.sys.util.CacheUtil;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;

@RemoteProxy
public class LoginAjaxService extends BaseAjaxService{
	@Autowired
	private UserService userService;

	/**
	 * 登陆
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	@RemoteMethod
	public Map<String, String> login(String userName, String password) {
		Map<String, String> map = new HashMap<String, String>();
		
		if(!Assert.isNotEmpty(userName))
		{
			map.put("result", "false");
			map.put("msg", "用户名不能为空！");
			
			return map;
		}
		
		if(!Assert.isNotEmpty(password))
		{
			map.put("result", "false");
			map.put("msg", "密码不能为空！");
			
			return map;
		}
		
		password = SecurityHelper.encrypt(Assert.trim(password));// 对明文密码进行加密
		
		SysUser user = userService.load(Assert.trim(userName), password);
		
		if(!Assert.isNotNull(user))
		{
			map.put("result", "false");
			map.put("msg", "用户名或密码不正确！");
			
			return map;
		}
		
		HttpSession session = getSession();
		
		String token = SecurityHelper.generateToken(userName + password);
		Long userId = user.getId();
		
		session.setAttribute(BaseSessionKeyConstant.USER_ID, userId);
		session.setAttribute(BaseSessionKeyConstant.SECURITY_TOKEN, token);
		
		CacheUtil.userLoginInfoMap.put(userId, token);
		
		map.put("result", "true");
		
		return map;
	}

	/**
	 * 退出
	 */
	@RemoteMethod
	public void loginOut() {
		try {
			HttpSession session = getSession();
			Long userId = (Long)session.getAttribute(BaseSessionKeyConstant.USER_ID);
			
			if( null != userId)
				CacheUtil.userLoginInfoMap.remove(userId);
			
			session.setAttribute(BaseSessionKeyConstant.USER_ID, null);
			session.setAttribute(BaseSessionKeyConstant.SECURITY_TOKEN, null);
			
			this.getReq().getSession().invalidate();
			this.getSession().invalidate();
		} catch (Exception e) {
			logger.error("loginOut", e);
		}
	}

}
