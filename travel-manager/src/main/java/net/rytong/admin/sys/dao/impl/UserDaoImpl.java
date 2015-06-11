package net.rytong.admin.sys.dao.impl;

import java.util.List;

import net.rytong.admin.common.dao.BaseDao;
import net.rytong.admin.common.util.Assert;
import net.rytong.admin.sys.dao.UserDao;
import net.rytong.admin.sys.entity.SysUser;

import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public void save(SysUser user) {
		if(user != null)
			hibernateTemplate.save(user);
	}

	@Override
	public SysUser load(String userName, String password) {
		List<SysUser> list = (List<SysUser>)hibernateTemplate.find("from SysUser model where model.name = ? and model.password = ?", userName, password);
		
		if(Assert.isNotEmpty(list))
			return list.get(0);
		
		return null;
	}

}
