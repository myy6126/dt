package com.doctortrain.service;

import java.util.List;
import java.util.Map;

import com.doctortrain.bean.Permission;
import com.doctortrain.bean.Role;

public interface RoleService {
	/**
	 * 添加角色信息
	 * @param user
	 * @throws Exception
	 */
	public void addRole(Role role) throws Exception;
	
	/**
	 * 查询角色信息通过id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Role getRoleById(String id) throws Exception;

	/**
	 * 查询所有角色信息
	 * @return
	 * @throws Exception
	 */
	public List<Role> getAllRole() throws Exception;
	
	/**
	 * 更新角色信息
	 * @param user
	 * @throws Exception
	 */
	public void updateRole(Role role) throws Exception;
	
	/**
	 * 删除角色信息
	 * @param ids
	 * @throws Exception
	 */
	public void deleteRoleById(String[] ids) throws Exception;
	
	/**
	 * 获取角色通过类型
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRolesByType(String type) throws Exception;
	

}
