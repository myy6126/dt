package com.doctortrain.service;

import java.util.List;
import java.util.Map;

import com.doctortrain.bean.Permission;
import com.doctortrain.bean.User;

public interface PermissionService {
	/**
	 * 添加许可信息
	 * @param user
	 * @throws Exception
	 */
	public void addPermission(Permission per) throws Exception;
	
	/**
	 * 查询许可信息通过id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Permission getPermissionById(String id) throws Exception;

	/**
	 * 查询所有许可信息
	 * @return
	 * @throws Exception
	 */
	public List<Permission> getAllPermission() throws Exception;
	
	/**
	 * 更新许可信息
	 * @param user
	 * @throws Exception
	 */
	public void updatePermission(Permission per) throws Exception;
	
	/**
	 * 删除许可信息
	 * @param ids
	 * @throws Exception
	 */
	public void deletePermissionById(String[] ids) throws Exception;
	
	/**
	 * 获取Permission树
	 * @return 
	 * @throws Exception
	 */
	public List<Map<String, Object>> getPermissionTree() throws Exception;
	
	/**
	 * 查询许可信息通过pid
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Permission> getPermissionByPId(String pid) throws Exception;
}
