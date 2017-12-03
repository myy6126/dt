package com.doctortrain.service;

import java.util.List;

import com.doctortrain.bean.StudentInfo;

/**
 * Service层
 * @author May
 *
 */
public interface StudentInfoService {
	
	/**
	 * 获取所有学生信息
	 * @return List
	 * 		学生list
	 * @throws Exception
	 */
	public List<StudentInfo> queryAllStudentInfo() throws Exception;
	
	/**
	 * 添加学生信息
	 * @param studentInfo
	 * 		学生信息
	 * @throws Exception
	 */
	public void addStudentInfo(StudentInfo studentInfo) throws Exception;

	/**
	 * 修改学生信息
	 * @param studentInfo
	 * @throws Exception
	 */
	public void updateStudentInfo(StudentInfo studentInfo) throws Exception;
	
	/**
	 * 通过id获取学生信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StudentInfo getStudentInfo(String id) throws Exception;
	
	/**
	 * 删除操作（批量删除、单个删除）
	 * @param idList id集合
	 * @throws Exception
	 */
	public void deleteStudentInfos(String[] ids) throws Exception;
	
	/**
	 * 通过住院医级别查询
	 * @param id 
	 * @return
	 * @throws Exception
	 */
	public List<StudentInfo> getStudentInfoByRcId(String id) throws Exception;

	/**
	 * 住院医等级自增长
	 * @param id
	 * @throws Exception
	 */
	public void growRC(String id) throws Exception;
	
	/**
	 * 住院医等级自增长
	 * @param studentInfo
	 * @throws Exception
	 */
	public void growRC(StudentInfo studentInfo) throws Exception;
	
	/**
	 * 住院医等级自增长所有学生信息
	 * @throws Exception
	 */
	public void growRCAll() throws Exception;
	
	public void updateStuInfo(StudentInfo studentInfo) throws Exception;
}
