package com.doctortrain.service;

import java.util.List;

import com.doctortrain.bean.Permission;

public interface RolePermissionRelationService {
	
	/**
	 * 分配许可
	 * @param roleId
	 * @param permissionIds
	 * @throws Exception 
	 */
	public void assign(String roleId, String[] permissionIds) throws Exception;

	public void deleteAssignByRoleId(String roleId) throws Exception;
	public void deleteAssignByPermissionId(String roleId) throws Exception;
	
	public List<Permission> getPermissionList(String roleId) throws Exception;
	
	public List<String> getPermissionIdList(String roleId) throws Exception; 
	
	
}
