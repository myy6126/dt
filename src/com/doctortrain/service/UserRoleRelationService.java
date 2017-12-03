package com.doctortrain.service;

import java.util.List;

import com.doctortrain.bean.Role;


public interface UserRoleRelationService {
	/**
	 * 根据用户id获得该用户已经分配的角色列表
	 * @param userId
	 * @return
	 */
	public List<Role> getAssignedByUsername(String username);
	
	/**
	 * 给用户分配角色
	 * @param userId
	 * @param roleIds
	 * @throws Exception 
	 */
	public void assign(String userId, String roleIds) throws Exception;
	
	/**
	 * 去除权限
	 * @param username
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteAssignByUsername(String username) throws Exception;
	public void deleteAssignByRoleId(String roleId) throws Exception;

}
