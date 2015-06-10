package net.rytong.admin.sys.dao.impl;

import java.util.List;

import net.rytong.admin.common.dao.BaseDao;
import net.rytong.admin.common.util.Assert;
import net.rytong.admin.sys.dao.UserDao;
import net.rytong.admin.sys.entity.User;

import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public void save(User user) {
		if(user != null)
			hibernateTemplate.save(user);
	}

	@Override
	public User load(String userName, String password) {
		List<User> list = (List<User>)hibernateTemplate.find("from User model where model.name = ? and model.password = ?", userName, password);
		
		if(Assert.isNotEmpty(list))
			return list.get(0);
		
		return null;
	}

}
