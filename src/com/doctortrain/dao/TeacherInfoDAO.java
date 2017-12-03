package com.doctortrain.dao;

import java.util.List;

import com.doctortrain.bean.Role;
import com.doctortrain.bean.TeacherInfo;

public interface TeacherInfoDAO {
	
	/**
	 * 添加教师
	 * @param role
	 * @throws Exception
	 */
	public void addTeacherInfo(TeacherInfo role) throws Exception;

	/**
	 * 修改教师
	 * @param role
	 * @throws Exception
	 */
	public void updateTeacherInfo(TeacherInfo role) throws Exception;
	
	/**
	 * 查询教师
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TeacherInfo getTeacherInfoById(int id) throws Exception;
	
	/**
	 * 删除教师ById
	 * @param id
	 * @throws Exception
	 */
	public void deleteTeacherInfoById(String[] ids) throws Exception;
	
	/**
	 * 查询所有教师
	 * @return
	 * @throws Exception
	 */
	public List<TeacherInfo> getAllTeacherInfoes() throws Exception;
	
}
