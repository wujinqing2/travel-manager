package net.rytong.admin.sys.service.impl;

import net.rytong.admin.common.security.SecurityHelper;
import net.rytong.admin.common.util.Assert;
import net.rytong.admin.common.util.TimeHelper;
import net.rytong.admin.sys.dao.UserDao;
import net.rytong.admin.sys.entity.User;
import net.rytong.admin.sys.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public void save(User user) {
		user.setCreateDate(TimeHelper.getCurrentTime());
		user.setUpdateDate(TimeHelper.getCurrentTime());
		user.setPassword(SecurityHelper.encrypt(Assert.trim(user.getPassword())));// 对明文密码进行加密
		
		userDao.save(user);
	}
	
	public User load(String userName, String password)
	{
		return userDao.load(userName, password);
	}

	
}
