package net.rytong.admin.sys.service.impl;


import net.rytong.admin.sys.dao.SysCodeDao;
import net.rytong.admin.sys.entity.SysCode;
import net.rytong.admin.sys.service.SysCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SysCodeServiceImpl implements SysCodeService {
	@Autowired
	private SysCodeDao sysCodeDao;
	@Override
	public void save(SysCode entity) {
		sysCodeDao.save(entity);
		
	}

	@Override
	public SysCode load(Long id) {
		return sysCodeDao.load(id);
	}

}
