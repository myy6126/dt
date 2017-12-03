package com.doctortrain.dao;

import java.util.List;

import com.doctortrain.bean.RolePermissionRelation;

public interface RolePermissionRelationDAO {
	
	/**
	 * 根据角色id和许可id获得角色和许可的关系对象
	 * @param roleId
	 * @param id
	 * @return
	 */
	RolePermissionRelation getRoleIdAndPermissionId(String roleId, String id);
	/**
	 * 根据角色id获取角色和许可的关系对象
	 * @param roleId
	 * @return
	 */
	List<RolePermissionRelation> getByRoleId(String roleId);
	
	/**
	 * 获取 通过PermissionId
	 * @param permissionId
	 * @return
	 */
	List<RolePermissionRelation> getByPermissionId(String permissionId);
	/**
	 * 删除角色和许可的关系对象
	 * @param rolePermissionRelation
	 */
	void delete(RolePermissionRelation rolePermissionRelation);
	/**
	 * 保存角色和许可的关系
	 * @param rolePermissionRelation
	 */
	void save(RolePermissionRelation rolePermissionRelation);
	/**
	 * 生成sql语句
	 */
	void flush();

}
