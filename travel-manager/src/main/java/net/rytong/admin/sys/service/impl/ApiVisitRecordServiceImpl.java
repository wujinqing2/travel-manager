package net.rytong.admin.sys.service.impl;

import org.springframework.stereotype.Component;

import net.rytong.admin.sys.entity.ApiVisitRecord;
import net.rytong.admin.sys.service.ApiVisitRecordService;

@Component
public class ApiVisitRecordServiceImpl implements ApiVisitRecordService {

	@Override
	public void save(ApiVisitRecord entity) {
		System.out.println("save");

	}

}
