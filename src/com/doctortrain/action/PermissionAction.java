package com.doctortrain.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.doctortrain.bean.Permission;
import com.doctortrain.service.PermissionService;
import com.doctortrain.service.RolePermissionRelationService;
import com.doctortrain.util.DateUtil;

@Controller("PermissionAction")
@Scope("prototype")
@Namespace("/permission")
public class PermissionAction extends BaseAction {
	
	@Resource(name="permissionService")
	public PermissionService permissionService;
	
	@Resource(name="rolePermissionRelationService")
	public RolePermissionRelationService permissionRoleRelationService;
	
	private Permission permission;
	
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Action(value="index",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/permission_info.jsp")})
	public String index() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toAdd",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/permission_add.jsp")})
	public String stuAdd() throws Exception{
		return SUCCESS;
	}
	
	/**
	 * 去编辑页面
	 * @return
	 * @throws Exception
	 */
	@Action(value="toEdit",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/permission_edit.jsp")})
	public String toEdit() throws Exception{
		String id = permission.getId();
		permission = permissionService.getPermissionById(id);
		return SUCCESS;
	}
	
	/**
	 * 做修改操作
	 */
	@Action("doEdit")
	public String doEdit() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", true);
		try {
			permissionService.updatePermission(permission);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
	@Action("getAll")
	public String getAllPermission() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		List<Permission> perList = new ArrayList<Permission>();
		jsonMap.put("success", true);
		try {
			perList = permissionService.getAllPermission();
			for (Permission per : perList) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", per.getId());
				map.put("name", per.getName());
				map.put("icon", per.getIcon());
				map.put("moduleUrl", per.getModuleUrl());
				map.put("operationUrl", per.getOperationUrl());
				map.put("pid", per.getPid());
				listResult.add(map);
			}
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("add")
	public String addUser() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			if(permission.getPid()==null||permission.getPid()==""){
				permission.setId("0");
			}
			permission.setCreateTime(DateUtil.getSystemTime());
			permissionService.addPermission(permission);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("delete")
	public String deleteStudentInfos() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", true);
		try {
			
			
			String[] idsArr = ids.split("-");
			if(idsArr!=null&&idsArr.length>0){
				permissionRoleRelationService.deleteAssignByPermissionId(ids);
				permissionService.deletePermissionById(idsArr);
			}else{
				jsonMap.put("success", false);
			}
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
}
