package net.rytong.admin.sys.service;

import net.rytong.admin.sys.entity.SysCode;

public interface SysCodeService {
	public void save(SysCode entity);
	
	public SysCode load(Long id);
}
