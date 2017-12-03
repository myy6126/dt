package com.doctortrain.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doctortrain.bean.Role;
import com.doctortrain.bean.UserRoleRelation;
import com.doctortrain.dao.RoleDAO;
import com.doctortrain.dao.UserDAO;
import com.doctortrain.dao.UserRoleRelationDAO;
import com.doctortrain.service.UserRoleRelationService;

@Service("userRoleRelationService")
public class UserRoleRelationServiceImpl implements UserRoleRelationService {
	
	@Resource(name="userRoleRelationDao")
	private UserRoleRelationDAO userRoleRelationDao; 
	
	@Resource(name="userDao")
	private UserDAO userDao;
	
	@Resource(name="roleDao")
	private RoleDAO roleDao; 
	
	public List<Role> getAssignedByUsername(String username) {
		List<UserRoleRelation> userRoleRelations = userRoleRelationDao.getByUsername(username);
		List<Role> roles = new ArrayList<Role>();
		for (UserRoleRelation userRoleRelation : userRoleRelations) {
			roles.add(userRoleRelation.getRole());
		}
		return roles; 
	}

	public void assign(String username, String roleId) throws Exception {
		//for (String roleId : roleIds) {
			//根据用户和角色id获取关系对象
			UserRoleRelation userRoleRelation = userRoleRelationDao.getByUsernameAndRoleId(username,roleId);
			if(userRoleRelation==null){
				//正向授权  insert
				userRoleRelation = new UserRoleRelation();
				userRoleRelation.setUser(userDao.getUserByUsername(username));
				userRoleRelation.setRole(roleDao.getRoleById(roleId));
				userRoleRelationDao.save(userRoleRelation);
			}
				//else{
				//负向授权  delete
				//userRoleRelationDao.delete(userRoleRelation);
			//}
		//}
	}
	
	public void deleteAssignByUsername(String username) throws Exception {
		List<UserRoleRelation> list = userRoleRelationDao.getByUsername(username);
		for (UserRoleRelation userRoleRelation2 : list) {
			userRoleRelationDao.delete(userRoleRelation2);
		}
	}
	
	public void deleteAssignByRoleId(String roleId) throws Exception{
		List<UserRoleRelation> list = userRoleRelationDao.getByRoleId(roleId);
		for (UserRoleRelation userRoleRelation2 : list) {
			userRoleRelationDao.delete(userRoleRelation2);
		}
	}

}