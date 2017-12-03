package com.doctortrain.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doctortrain.bean.Permission;
import com.doctortrain.bean.Role;
import com.doctortrain.bean.RolePermissionRelation;
import com.doctortrain.dao.PermissionDAO;
import com.doctortrain.dao.RoleDAO;
import com.doctortrain.dao.RolePermissionRelationDAO;
import com.doctortrain.service.RolePermissionRelationService;
@Service("rolePermissionRelationService")
public class RolePermissionRelationServiceImpl implements RolePermissionRelationService{

	@Resource(name="rolePermissionRelationDao")
	private RolePermissionRelationDAO  rolePermissionRelationDao;
	
	@Resource(name="roleDao")
	private RoleDAO roleDao;
	
	@Resource(name="permissionDao")
	private PermissionDAO permissionDao;
	
	public void assign(String roleId, String[] permissionIds) throws Exception {
		//先删除该角色拥有的许可
		//根据角色id获得该角色拥有的角色和许可的关系对象
		List<RolePermissionRelation> permissionRelations =  rolePermissionRelationDao.getByRoleId(roleId);
		for (RolePermissionRelation rolePermissionRelation : permissionRelations) {
			rolePermissionRelationDao.delete(rolePermissionRelation);
		}
		
		//生成sql语句
		rolePermissionRelationDao.flush();
		
		//再插入关系
		Role role = roleDao.getRoleById(roleId);
		for (String permissionId : permissionIds) {
			if(!"".equals(permissionId)){
				Permission permission = permissionDao.getPermissionById(permissionId);
				RolePermissionRelation rolePermissionRelation = new RolePermissionRelation();
				rolePermissionRelation.setRole(role);
				rolePermissionRelation.setPermission(permission);
				rolePermissionRelationDao.save(rolePermissionRelation);				
			}	
		}
		
	}

	public void deleteAssignByRoleId(String roleId) throws Exception {
		//先删除该角色拥有的许可
				//根据角色id获得该角色拥有的角色和许可的关系对象
				List<RolePermissionRelation> permissionRelations =  rolePermissionRelationDao.getByRoleId(roleId);
				if(permissionRelations.size()>0){
					for (RolePermissionRelation rolePermissionRelation : permissionRelations) {
						rolePermissionRelationDao.delete(rolePermissionRelation);
					}
					//生成sql语句
					rolePermissionRelationDao.flush();
				}
	}
	
	public void deleteAssignByPermissionId(String permissionIds) throws Exception {
		
		if(permissionIds!=null&&permissionIds.contains("-")){
			String [] ids = permissionIds.split("-");
			for (int i = 0; i < ids.length; i++) {
				deleteByPermissionId(ids[i]);
			}
		}else{
				deleteByPermissionId(permissionIds);
		}
		
	}
	
	public void deleteByPermissionId(String id) throws Exception{
		//先删除该角色拥有的许可
		//根据角色id获得该角色拥有的角色和许可的关系对象
		List<RolePermissionRelation> permissionRelations =  rolePermissionRelationDao.getByPermissionId(id);
		if(permissionRelations.size()>0){
			for (RolePermissionRelation rolePermissionRelation : permissionRelations) {
				rolePermissionRelationDao.delete(rolePermissionRelation);
			}
			//生成sql语句
			rolePermissionRelationDao.flush();
		}
	}

	public List<Permission> getPermissionList(String roleId) throws Exception {
		List<Permission> perList = new ArrayList<Permission>();
		List<RolePermissionRelation> permissionRelations =  rolePermissionRelationDao.getByRoleId(roleId);
		for (RolePermissionRelation rolePermissionRelation : permissionRelations) {
			perList.add(rolePermissionRelation.getPermission());
		}
		return perList;
	}

	public List<String> getPermissionIdList(String roleId) throws Exception {
		List<String> perIdList = new ArrayList<String>();
		List<RolePermissionRelation> permissionRelations =  rolePermissionRelationDao.getByRoleId(roleId);
		for (RolePermissionRelation rolePermissionRelation : permissionRelations) {
			perIdList.add(rolePermissionRelation.getPermission().getId());
		}
		return perIdList;
	}
	
	
	
	
}
