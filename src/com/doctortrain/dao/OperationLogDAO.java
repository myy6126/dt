package com.doctortrain.dao;

import java.util.List;

import com.doctortrain.bean.OperationLog;


public interface OperationLogDAO {
	/**
	 * 分页查询操作日志
	 * @param pageNo
	 * @param pageSize
	 * @param operatorName
	 * @param operationType
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<OperationLog> getByPage(Integer pageNo, Integer pageSize,
			String operatorName, String operationType, String startTime,
			String endTime);
	/**
	 * 保存操作日志
	 * @param ol
	 */
	public void save(OperationLog ol);
	/**
	 * 获取所有操作日志
	 * @return
	 */
	public List<OperationLog> getAll();
	
	public int deleteOperationLog(String id);
	
	public void deleteAll();

}
