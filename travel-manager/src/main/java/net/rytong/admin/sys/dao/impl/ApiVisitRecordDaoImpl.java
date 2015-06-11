package net.rytong.admin.sys.dao.impl;

import org.springframework.stereotype.Component;

import net.rytong.admin.common.dao.BaseDao;
import net.rytong.admin.sys.dao.ApiVisitRecordDao;
import net.rytong.admin.sys.entity.ApiVisitRecord;

@Component
public class ApiVisitRecordDaoImpl extends BaseDao implements ApiVisitRecordDao {

	@Override
	public void save(ApiVisitRecord entity) {
		if(entity != null)
			hibernateTemplate.save(entity);
	}

}
