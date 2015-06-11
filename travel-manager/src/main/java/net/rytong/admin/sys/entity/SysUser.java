package net.rytong.admin.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import net.rytong.admin.common.entity.BaseEntity;

@Entity
@Table(name="sys_user")
public class SysUser extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "name")
	private String name;// 用户名
	@Column(name = "password")
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
