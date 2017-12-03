package com.doctortrain.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.doctortrain.service.RolePermissionRelationService;

@Controller
@Namespace("/rolePermissionRelation")
@Scope("prototype")
public class RolePermissionRelationAction extends BaseAction{
	private String roleId;
	private String[] permissionIds;
	
	@Resource(name="rolePermissionRelationService")
	private RolePermissionRelationService rolePermissionRelationService; 
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public void setPermissionIds(String[] permissionIds) {
		this.permissionIds = permissionIds;
	}
	@Action("assign")
	public String assign()throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try {
			rolePermissionRelationService.assign(roleId,permissionIds);
			jsonMap.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
}
