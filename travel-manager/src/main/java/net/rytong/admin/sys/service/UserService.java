package net.rytong.admin.sys.service;

import net.rytong.admin.sys.entity.User;

public interface UserService {
	/**
	 * <pre>
	 * 保存用户信息
	 * <pre>
	 * 
	 * @param user
	 * @return
	 */
	public void save(User user);
	
	/**
	 * <pre>
	 * 根据用户名及密码查询当前登录用户信息
	 * <pre>
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public User load(String userName, String password);
}
