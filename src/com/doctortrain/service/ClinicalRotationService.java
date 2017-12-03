package com.doctortrain.service;

import java.util.List;
import java.util.Map;

import com.doctortrain.bean.ClinicalRotation;
import com.doctortrain.bean.ClinicalRotationConfig;
import com.doctortrain.bean.StudentInfo;

public interface ClinicalRotationService {
	
	/**
	 * 添加轮转
	 * @param cr
	 * @throws Exception
	 */
	public void addCR(ClinicalRotation cr)throws Exception;
	
	/**
	 * 添加轮转
	 * @param studentInfo 学生信息
	 * @param addOrUpdate add true/ update false
	 * @throws Exception
	 */
	public void addCR(StudentInfo studentInfo,boolean addOrUpdate)throws Exception;
	
	/**
	 * 
	 * @param studentInfo
	 * @param add
	 * @throws Exception
	 */
	public void addCRPriority(StudentInfo studentInfo,boolean add) throws Exception;
	public void addCRPriorityNew(StudentInfo studentInfo,boolean add) throws Exception;
	/**
	 * 修改轮转
	 * @param cr
	 * @throws Exception
	 */
	public void updateCR(ClinicalRotation cr)throws Exception;
	
	/**
	 * 删除轮转
	 * @param id
	 * @throws Exception
	 */
	public void deleteCRById(int id)throws Exception;
	
	/**
	 * 通过学生ID删除轮转信息
	 * @param id
	 * @throws Exception
	 */
	public void deleteCRByStuId(String id) throws Exception;

	/**
	 * 获取轮转ById
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ClinicalRotation getCRById(int id)throws Exception;
	
	/**
	 * 获取所有轮转
	 * @return
	 * @throws Exception
	 */
	public List<ClinicalRotation> getAllCR()throws Exception;
	

	/**
	 * 获取轮转信息 by StuID
	 * @param stuId
	 * @return
	 * @throws Exception
	 */
	public List<ClinicalRotation> getCrByStuId(String stuId)throws Exception;
	
	/**
	 * 获取正在轮转科目
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getCrNow(String id) throws Exception;
	
	/**
	 * 开始时间 < --- > 结束时间   ：一共几个月
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public int countMonth(String startTime,String endTime) throws Exception;
	
	
	public void addCRByStatus(StudentInfo studentInfo,boolean add) throws Exception;
	public void addCRPriorityNew(StudentInfo studentInfo,ClinicalRotationConfig crc) throws Exception;
	
	/**
	 * 本月科目列表
	 * @param crList
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getNowMonthSub(List<ClinicalRotation> crcList) throws Exception;
	
	/**
	 * 获取当前年crcList
	 * @param year
	 * @return
	 * @throws Exception
	 */
	public List<ClinicalRotation> getYearList(String year)throws Exception;
	/**
	 * 考试名单
	 * @param crList 当年crcList
	 * @param subList
	 * @return "科目":"学号-姓名"
	 * @throws Exception
	 */
	public Map<String, List<String>> getExamList(List<ClinicalRotation> crList,
			List<String> subList) throws Exception;
	
}
