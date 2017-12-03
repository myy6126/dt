package com.doctortrain.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.doctortrain.bean.User;

public interface UserService {
	/**
	 * 添加用户信息
	 * @param user
	 * @throws Exception
	 */
	public void addUser(User user) throws Exception;
	
	/**
	 * 查询用户信息通过id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User getUserByUsername(String username) throws Exception;

	/**
	 * 查询所有用户信息
	 * @return
	 * @throws Exception
	 */
	public List<User> getAllUser() throws Exception;
	
	/**
	 * 更新用户信息
	 * @param user
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception;
	
	/**
	 * 删除用户信息
	 * @param ids
	 * @throws Exception
	 */
	public void deleteUserByUsername(String[] ids) throws Exception;
	
	/**
	 * 登录验证
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	public User login(String username,String password,String ip) throws Exception;
	
	/**
	 * 获取该用户拥有的所有url
	 * @param id
	 * @return
	 */
	public Set<String> getUrlsByUsername(String username);
	
	/**
	 * 获取模块Url
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getModuleAndOperationByUsername(String username) throws Exception;
	
	
}
