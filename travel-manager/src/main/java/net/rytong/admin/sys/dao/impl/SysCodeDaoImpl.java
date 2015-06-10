package net.rytong.admin.sys.dao.impl;

import net.rytong.admin.common.dao.BaseDao;
import net.rytong.admin.common.util.Assert;
import net.rytong.admin.sys.dao.SysCodeDao;
import net.rytong.admin.sys.entity.SysCode;

import org.springframework.stereotype.Component;

@Component
public class SysCodeDaoImpl extends BaseDao implements SysCodeDao {

	@Override
	public void save(SysCode entity) {
		if(Assert.isNotNull(entity))
			hibernateTemplate.save(entity);
	}

	@Override
	public SysCode load(Long id) {
		return hibernateTemplate.get(SysCode.class, id);
	}

}
