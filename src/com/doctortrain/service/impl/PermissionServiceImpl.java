package com.doctortrain.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doctortrain.bean.Permission;
import com.doctortrain.bean.User;
import com.doctortrain.dao.PermissionDAO;
import com.doctortrain.dao.UserDAO;
import com.doctortrain.service.PermissionService;
import com.doctortrain.service.UserService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	
	@Resource(name="permissionDao")
	public PermissionDAO permissionDao;

	public void addPermission(Permission per) throws Exception {
		permissionDao.addPermission(per);
	}

	public Permission getPermissionById(String id) throws Exception {
		return permissionDao.getPermissionById(id);
	}

	public List<Permission> getAllPermission() throws Exception {
		return permissionDao.getAllPermission();
	}

	public void updatePermission(Permission per) throws Exception {
		permissionDao.updatePermission(per);
	}

	public void deletePermissionById(String[] ids) throws Exception {
		permissionDao.deletePermissionById(ids);
	}

	public List<Map<String,Object>> getPermissionTree() throws Exception {
		List<Map<String,Object>> ResultList = new ArrayList<Map<String,Object>>();
		List<Permission> perList = permissionDao.getAllPermission();
		for (Permission permission : perList) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id", permission.getId());
			map.put("pId", permission.getPid());
			map.put("name", permission.getName());
			if("0".equals(permission.getPid())){
				map.put("open",true);
			}
			ResultList.add(map);
			/*Map<String,Object> map2 = new HashMap<String,Object>();
			if(!"0".equals(permission.getPid())){
				map2.put("id", permission.getId()+"1");
				map2.put("pId", permission.getId());
				map2.put("name", "查看");
				ResultList.add(map2);
			}
			Map<String,Object> map3 = new HashMap<String,Object>();
			if(!"0".equals(permission.getPid())){
				map3.put("id", permission.getId()+"2");
				map3.put("pId", permission.getId());
				map3.put("name", "管理");
				ResultList.add(map3);
			}*/
		}
		return ResultList;
	}

	public List<Permission> getPermissionByPId(String pid) throws Exception {
		return permissionDao.getPermissionByPId(pid);
	}

}
