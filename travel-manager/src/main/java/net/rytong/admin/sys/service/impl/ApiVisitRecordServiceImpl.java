package net.rytong.admin.sys.service.impl;

import net.rytong.admin.sys.dao.ApiVisitRecordDao;
import net.rytong.admin.sys.entity.ApiVisitRecord;
import net.rytong.admin.sys.service.ApiVisitRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ApiVisitRecordServiceImpl implements ApiVisitRecordService {
	@Autowired
	private ApiVisitRecordDao apiVisitRecordDao;
	
	@Override
	public void save(ApiVisitRecord entity) {
		apiVisitRecordDao.save(entity);
	}

}
