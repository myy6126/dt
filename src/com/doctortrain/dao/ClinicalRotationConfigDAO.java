package com.doctortrain.dao;

import java.util.List;

import com.doctortrain.bean.ClinicalRotation;
import com.doctortrain.bean.ClinicalRotationConfig;

public interface ClinicalRotationConfigDAO {
	
	/**
	 * 添加轮转
	 * @param cr
	 * @throws Exception
	 */
	public void addCRC(ClinicalRotationConfig cr) throws Exception;
	/**
	 * 删除轮转
	 * @param cr
	 * @throws Exception
	 */
	public void deleteCRC(int id) throws Exception;
	/**
	 * 修改轮转
	 * @param cr
	 * @throws Exception
	 */
	public void updateCRC(ClinicalRotationConfig cr) throws Exception;
	/**
	 * 获取轮转ById
	 * @param id
	 * @throws Exception
	 */
	public ClinicalRotationConfig getCRCById(int id) throws Exception;
	
	/**
	 * 获取所有轮转
	 * @param id
	 * @throws Exception
	 */
	public List<ClinicalRotationConfig> getAllCRC() throws Exception;
	
	/**
	 * 获取所有优先级
	 * @return
	 * @throws Exception
	 */
	List<Integer> getAllPriority() throws Exception;
	
	/**
	 * 根据状态获取配置
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<ClinicalRotationConfig> getAllByStutas(String status) throws Exception;
	
	/**
	 * 通过优先级获取轮转配置
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<ClinicalRotationConfig> getCRCByPriority(int priority) throws Exception;
	
	
}
