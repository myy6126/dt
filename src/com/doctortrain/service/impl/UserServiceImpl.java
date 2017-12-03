package com.doctortrain.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.doctortrain.bean.Permission;
import com.doctortrain.bean.RolePermissionRelation;
import com.doctortrain.bean.User;
import com.doctortrain.bean.UserRoleRelation;
import com.doctortrain.dao.PermissionDAO;
import com.doctortrain.dao.RolePermissionRelationDAO;
import com.doctortrain.dao.UserDAO;
import com.doctortrain.dao.UserRoleRelationDAO;
import com.doctortrain.exception.ApplicationException;
import com.doctortrain.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="userDao")
	public UserDAO userDao;
	
	@Resource(name="userRoleRelationDao")
	private UserRoleRelationDAO userRoleRelationDao;
	
	@Resource(name="rolePermissionRelationDao")
	private RolePermissionRelationDAO rolePermissionRelationDao;

	@Resource(name="permissionDao")
	private PermissionDAO permissionDao;

	public void addUser(User user) throws Exception {
		userDao.addUser(user);
	}

	public User getUserByUsername(String username) throws Exception {
		return userDao.getUserByUsername(username);
	}

	public List<User> getAllUser() throws Exception {
		return userDao.getAllUser();
	}

	public void updateUser(User user) throws Exception {
		userDao.updateUser(user);
	}

	public void deleteUserByUsername(String[] usernames) throws Exception {
		userDao.deleteUserByUsername(usernames);
	}

	public User login(String username, String password,String ip) throws Exception {
		//验证用户帐号和密码是否正确
				User user = userDao.getUserByUsernameAndPassword(username,password);
				if(user==null){
					throw new ApplicationException("帐号或者密码错误");
				}
				/*//验证是否锁定
				if(User.LOCK_CODE.equals(user.getLockStatus()) ){
					throw new ApplicationException("帐号被锁定请联系管理员");			
				}
				//验证是否失效
				if(user.getExpireTime()!=null&&(DateUtil.getSystemTime().compareTo(user.getExpireTime())>0)){
					throw new ApplicationException("帐号已失效请联系管理员");						
				}
				//验证ip是否受限
				if(StringUtil.isNotEmpty(user.getAllowIps())&&!user.getAllowIps().contains(ip)){
					throw new ApplicationException("ip访问受限请联系管理员");									
				}
				*/
				return user;
	}

	public Set<String> getUrlsByUsername(String username) {
		Set<String> urls = new HashSet<String>();
		//根据用户id获取该用户对应的用户和角色的关系
		List<UserRoleRelation> userRoleRelations = userRoleRelationDao.getByUsername(username);
		for (UserRoleRelation userRoleRelation : userRoleRelations) {
			//根据角色id获取该角色对应的许可
			List<RolePermissionRelation> rolePermissionRelations = rolePermissionRelationDao.getByRoleId(userRoleRelation.getRole().getId());
			for (RolePermissionRelation rolePermissionRelation : rolePermissionRelations) {
				Permission permission = rolePermissionRelation.getPermission();
				if(permission.getOperationUrl()!=null){
					//将数组放到集合上
					Collections.addAll(urls, permission.getOperationUrl().split(","));
				}
				if(permission.getModuleUrl()!=null){
					urls.add(permission.getModuleUrl());
				}
			}
		}
		return urls;
	}

	public Map<String, Object> getModuleAndOperationByUsername(String username) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//List<String> olList = new ArrayList<String>();
		List<UserRoleRelation> userRoleRelations = userRoleRelationDao.getByUsername(username);
		List<Map<String, Object>> moList = new ArrayList<Map<String, Object>>();
		for (UserRoleRelation userRoleRelation : userRoleRelations) {
			List<RolePermissionRelation> list =  rolePermissionRelationDao.getByRoleId(userRoleRelation.getRole().getId());
			/*for (RolePermissionRelation rolePermissionRelation : list) {
				Permission permission = rolePermissionRelation.getPermission();
				if(permission.getPid().equals("0")){
					Map<String,Object> pMap = new HashMap<String,Object>();
					List<Map<String, Object>> Menulist = new ArrayList<Map<String, Object>>();
					List<Permission> perList = permissionDao.getPermissionByPId(permission.getPid());
					for (Permission permission2 : perList) {
						Map<String,Object> cMap = new HashMap<String,Object>();
						cMap.put("id", permission2.getId());
						cMap.put("text", permission2.getName());
						cMap.put("url", permission2.getModuleUrl());
						olList.add(permission2.getOperationUrl());
						Menulist.add(cMap);
					};
					pMap.put("id", permission.getId());
					pMap.put("text", permission.getName());
					pMap.put("menu", Menulist);
					moList.add(pMap);
				}
			}*/
			// 找出所有父节点
			List<Permission> li = new ArrayList<Permission>();
			for (RolePermissionRelation rolePermissionRelation : list) {
				if("0".equals(rolePermissionRelation.getPermission().getPid())){
					li.add(rolePermissionRelation.getPermission());
				}
			}
			
			String contextPath = null;
			if(ServletActionContext.getServletContext().getContextPath()==null||"".equals(ServletActionContext.getServletContext().getContextPath())){
				contextPath = "";
			}else{
				//contextPath = "/"+ServletActionContext.getServletContext().getContextPath();
				contextPath = ServletActionContext.getServletContext().getContextPath();
			}
			// 父节点加入子节点
			for (Permission parentP : li) {
				Map<String,Object> pMap = new HashMap<String,Object>();
				List<Map<String,Object>> pList = new ArrayList<Map<String,Object>>();
				pMap.put("id", parentP.getId());
				pMap.put("text", parentP.getName());
				pMap.put("icon", parentP.getIcon());
				for (RolePermissionRelation rolePermissionRelation : list) {
					Map<String,Object> cMap = new HashMap<String,Object>();
					Permission per = rolePermissionRelation.getPermission();
					if(parentP.getId().equals(per.getPid())){
						cMap.put("id", per.getId());
						cMap.put("text", per.getName());
					
						cMap.put("url", contextPath+per.getModuleUrl());
						cMap.put("icon", per.getIcon());
						// olList.add(per.getOperationUrl());
						pList.add(cMap);
					}
				}
				pMap.put("menus", pList);
				moList.add(pMap);
			}
			
		}
		resultMap.put("moduleUrls", moList);
		// resultMap.put("operationUrls", olList);
		return resultMap;
	}


}
