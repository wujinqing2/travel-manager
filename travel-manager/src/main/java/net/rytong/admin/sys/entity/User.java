package net.rytong.admin.sys.entity;

import net.rytong.admin.common.entity.BaseEntity;

public class User extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String name;// 用户名
	private String password;// 密码
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
