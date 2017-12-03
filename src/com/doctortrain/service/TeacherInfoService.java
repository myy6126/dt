package com.doctortrain.service;

import java.util.List;
import java.util.Map;

import com.doctortrain.bean.Permission;
import com.doctortrain.bean.Role;
import com.doctortrain.bean.TeacherInfo;

public interface TeacherInfoService {
	
	/**
	 * 添加教师信息
	 * @param user
	 * @throws Exception
	 */
	public void addTeacherInfo(TeacherInfo teacherInfo) throws Exception;
	
	/**
	 * 查询教师信息通过id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public TeacherInfo getTeacherInfoById(int id) throws Exception;

	/**
	 * 查询所有教师信息
	 * @return
	 * @throws Exception
	 */
	public List<TeacherInfo> getAllTeacherInfo() throws Exception;
	
	/**
	 * 更新教师信息
	 * @param user
	 * @throws Exception
	 */
	public void updateTeacherInfo(TeacherInfo teacherInfo) throws Exception;
	
	/**
	 * 删除教师信息
	 * @param ids
	 * @throws Exception
	 */
	public void deleteTeacherInfoById(String[] ids) throws Exception;
	

}
