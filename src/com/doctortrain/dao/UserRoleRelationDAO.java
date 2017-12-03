package com.doctortrain.dao;

import java.util.List;

import com.doctortrain.bean.UserRoleRelation;


public interface UserRoleRelationDAO {
	/**
	 * 根据用户id获取用户和角色的关系数据
	 * @param userId
	 * @return
	 */
	public List<UserRoleRelation>  getByUsername(String username);
	/**
	 * 根据用户id和角色id获取用户和角色关系
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public UserRoleRelation getByUsernameAndRoleId(String username, String roleId);
	
	/**
	 * 获取通过RoleID
	 * @param roleId
	 * @return
	 */
	public List<UserRoleRelation> getByRoleId(String roleId);
	/**
	 * 保存用户角色关系
	 * @param userRoleRelation
	 */
	public void save(UserRoleRelation userRoleRelation);
	/**
	 * 删除用户角色关系
	 * @param userRoleRelation
	 */
	public void delete(UserRoleRelation userRoleRelation);
}
