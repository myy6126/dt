package com.doctortrain.dao;

import java.util.List;

import com.doctortrain.bean.User;

public interface UserDAO {
	
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
	public void deleteUserByUsername(String[] usernames) throws Exception;
	
	/**
	 * 获取用户信息 By username password
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	public User getUserByUsernameAndPassword(String username,String password) throws Exception;

}