package com.doctortrain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doctortrain.bean.Role;
import com.doctortrain.dao.PermissionDAO;
import com.doctortrain.dao.RoleDAO;
import com.doctortrain.service.RoleService;

@Service("roleService")
public class RoleServiceimpl implements RoleService {
	
	@Resource(name="roleDao")
	public RoleDAO roleDao;

	public void addRole(Role role) throws Exception {
		roleDao.addRole(role);
	}

	public Role getRoleById(String id) throws Exception {
		return roleDao.getRoleById(id);
	}

	public List<Role> getAllRole() throws Exception {
		return roleDao.getAllRoles();
	}

	public void updateRole(Role role) throws Exception {
		roleDao.updateRole(role);
	}

	public void deleteRoleById(String[] ids) throws Exception {
		roleDao.deleteRoleById(ids);
	}

	public List<Role> getRolesByType(String type) throws Exception {
		return roleDao.getRolesByType(type);
	}

}
