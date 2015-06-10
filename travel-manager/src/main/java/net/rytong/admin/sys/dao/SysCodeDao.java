package net.rytong.admin.sys.dao;

import net.rytong.admin.sys.entity.SysCode;

public interface SysCodeDao {
	public void save(SysCode entity);
	
	public SysCode load(Long id);
}
