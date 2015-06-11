package net.rytong.admin.sys.dao;

import net.rytong.admin.sys.entity.SysUser;

public interface UserDao {
	/**
	 * <pre>
	 * 保存用户信息
	 * <pre>
	 * 
	 * @param user
	 * @return
	 */
	public void save(SysUser user);
	
	/**
	 * <pre>
	 * 根据用户名及密码查询当前登录用户信息
	 * <pre>
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public SysUser load(String userName, String password);
}
