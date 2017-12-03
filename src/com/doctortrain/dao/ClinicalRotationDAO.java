package com.doctortrain.dao;

import java.util.List;

import com.doctortrain.bean.ClinicalRotation;
import com.doctortrain.bean.ClinicalRotationConfig;

public interface ClinicalRotationDAO {
	
	/**
	 * 添加轮转
	 * @param cr
	 * @throws Exception
	 */
	public void addCR(ClinicalRotation cr) throws Exception;
	
	/**
	 * 获取Cr ByStuId
	 * @param id
	 * @throws Exception
	 */
	public List<ClinicalRotation> getCrByStuId(String id) throws Exception;
	/**
	 * 删除轮转
	 * @param cr
	 * @throws Exception
	 */
	public void deleteCR(int id) throws Exception;
	
	/**
	 * 删除轮转
	 * @param cr
	 * @throws Exception
	 */
	public void deleteCRByStuId(String stuId) throws Exception;
	/**
	 * 修改轮转
	 * @param cr
	 * @throws Exception
	 */
	public void updateCR(ClinicalRotation cr) throws Exception;
	/**
	 * 获取轮转ById
	 * @param id
	 * @throws Exception
	 */
	public ClinicalRotation getCRById(int id) throws Exception;
	
	/**
	 * 获取所有轮转
	 * @param id
	 * @throws Exception
	 */
	public List<ClinicalRotation> getAllCR() throws Exception;
	
	public void closeSession() throws Exception;
	
	/**
	 * 当月轮转列表
	 * @param year 年
	 * @param month 月
	 * @return
	 * @throws Exception
	 */
	public List<ClinicalRotation> getByYear(String year) throws Exception;
}
