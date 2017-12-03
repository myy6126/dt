package com.doctortrain.dao;

import java.util.List;

import org.hibernate.Session;

import com.doctortrain.bean.StudentInfo;

public interface StudentInfoDAO {
	
	/**
	 * 添加学生信息
	 * @param stuInfo
	 * @throws Exception
	 */
	public void addStudentInfo(StudentInfo stuInfo) throws Exception;
	
	/**
	 * 删除学生信息
	 * @param id
	 * @throws Exception
	 */
	public void deleteStudentInfo(String[] ids) throws Exception;
	
	/**
	 * 修改学生信息
	 * @param stuInfo
	 * @throws Exception
	 */
	public void updateStudentInfo(StudentInfo stuInfo) throws Exception;
	
	/**
	 * 根据id
	 * 查询学生信息
	 * @param id
	 * @return StudentInfo
	 * @throws Exception
	 */
	public StudentInfo getStudentInfoById(String id) throws Exception;
	
	/**
	 * 获取全部学生信息
	 * @return
	 * @throws Exception
	 */
	public List<StudentInfo> getAllStudentInfo() throws Exception;
	
	/**
	 * 通过住院医界别查询
	 * @param id 
	 * @return
	 * @throws Exception
	 */
	public List<StudentInfo> getStudentInfoByRcId(String id) throws Exception;
	
	public void flush();
	
	
	
}
