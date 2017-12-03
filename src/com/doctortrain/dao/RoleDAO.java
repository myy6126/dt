package com.doctortrain.dao;

import java.util.List;

import com.doctortrain.bean.Role;

public interface RoleDAO {
	
	/**
	 * 添加角色
	 * @param role
	 * @throws Exception
	 */
	public void addRole(Role role) throws Exception;

	/**
	 * 修改角色
	 * @param role
	 * @throws Exception
	 */
	public void updateRole(Role role) throws Exception;
	
	/**
	 * 查询角色ById
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Role getRoleById(String id) throws Exception;
	
	/**
	 * 删除角色ById
	 * @param id
	 * @throws Exception
	 */
	public void deleteRoleById(String[] ids) throws Exception;
	
	/**
	 * 查询所有角色
	 * @return
	 * @throws Exception
	 */
	public List<Role> getAllRoles() throws Exception;
	
	/**
	 * 根据角色类型查询角色
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRolesByType(String type) throws Exception;
	
}
