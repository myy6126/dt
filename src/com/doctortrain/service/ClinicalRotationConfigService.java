package com.doctortrain.service;

import java.util.List;
import java.util.Map;

import com.doctortrain.bean.ClinicalRotation;
import com.doctortrain.bean.ClinicalRotationConfig;

public interface ClinicalRotationConfigService {
	
	/**
	 * 添加轮转
	 * @param cr
	 * @throws Exception
	 */
	public void addCRC(ClinicalRotationConfig cr)throws Exception;
	
	/**
	 * 修改轮转
	 * @param cr
	 * @throws Exception
	 */
	public void updateCRC(ClinicalRotationConfig cr)throws Exception;
	
	/**
	 * 删除轮转
	 * @param id
	 * @throws Exception
	 */
	public void deleteCRCById(int id)throws Exception;
	
	/**
	 * 获取轮转ById
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ClinicalRotationConfig getCRCById(int id)throws Exception;
	
	/**
	 * 获取所有轮转
	 * @return
	 * @throws Exception
	 */
	public List<ClinicalRotationConfig> getAllCRC()throws Exception;
	
	/**
	 * 优先化 规则化 后的Config
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getAllCrcByPriority() throws Exception;
	
	/**
	 * @return
	 * 返回Map中会有三个结果   1.手动写List writeStraight
	 * 				   2.可移动List writeMove
	 * 				   3.允许移动的条件 writeMovePermit
	 * @throws Exception
	 */
	public Map<String,Object> getAllCrcByStatus() throws Exception;
	
}
