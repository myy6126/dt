package com.doctortrain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doctortrain.bean.OperationLog;
import com.doctortrain.dao.OperationLogDAO;
import com.doctortrain.dao.PermissionDAO;
import com.doctortrain.service.OperationLogService;

@Service("operationLogService")
public class OperationLogServiceImpl implements OperationLogService {

	@Resource(name="operationLogDao")
	public OperationLogDAO operationLogDao;
	
	public List<OperationLog> getByPage(Integer pageNo, Integer pageSize,
			String operatorName, String operationType, String startTime,
			String endTime) {
		return operationLogDao.getByPage(pageNo, pageSize, operatorName, operationType, startTime, endTime);
	}

	public void save(OperationLog ol) {
		operationLogDao.save(ol);
	}

	public List<OperationLog> getAll() {
		return operationLogDao.getAll();
	}

	public int deleteOperationLog(String[] ids) {
		
		if(ids.length>0){
			for (int i = 0; i < ids.length; i++) {
				operationLogDao.deleteOperationLog(ids[i]);
			}
		}
		
		return 1;
	}

	public void deleteAll() {
		operationLogDao.deleteAll();
	}

}
