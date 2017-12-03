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
import com.doctortrain.bean.Role;
import com.doctortrain.bean.User;
import com.doctortrain.service.PermissionService;
import com.doctortrain.service.RolePermissionRelationService;
import com.doctortrain.service.RoleService;
import com.doctortrain.service.UserRoleRelationService;
import com.doctortrain.util.DateUtil;

@Controller("RoleAction")
@Scope("prototype")
@Namespace("/role")
public class RoleAction extends BaseAction {
	
	private Role role;
	
	private String ids;
	
	@Resource(name="roleService")
	public RoleService roleService;
	
	@Resource(name="permissionService")
	public PermissionService permissionService;
	
	private String roleId;
	private String[] permissionIds;
	
	@Resource(name="rolePermissionRelationService")
	private RolePermissionRelationService rolePermissionRelationService; 
	@Resource(name="userRoleRelationService")
	private UserRoleRelationService userRoleRelationService; 
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public void setPermissionIds(String[] permissionIds) {
		this.permissionIds = permissionIds;
	}
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Action(value="index",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/role_info.jsp")})
	public String index() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toAdd",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/role_add.jsp")})
	public String stuAdd() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toEdit",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/role_edit.jsp")})
	public String stuEdit() throws Exception{
		role = roleService.getRoleById(role.getId());
		return SUCCESS;
	}
	
	@Action("doEdit")
	public String doEdit() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			// 添加Role信息
			role.setUpdateTime(DateUtil.getSystemTime());
			roleService.updateRole(role);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("getCheckedId")
	public String getAllCheckedPermissionId() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		jsonMap.put("success", true);
		try {
			idList = rolePermissionRelationService.getPermissionIdList(roleId);
			jsonMap.put("idList", idList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("getAuth")
	public String getAuthority() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", true);
		try {
			List<Map<String, Object>> rList = permissionService.getPermissionTree();
			jsonMap.put("resultList", rList);
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
		List<Role> roleList = new ArrayList<Role>();
		jsonMap.put("success", true);
		try {
			roleList = roleService.getAllRole();
			for (Role role : roleList) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", role.getId());
				map.put("name", role.getName());
				if("0".equals(role.getType())){
					map.put("type", "普通用户");
				}
				if("1".equals(role.getType())){
					map.put("type", "管理员");
				}
				map.put("remark", role.getRemark());
				listResult.add(map);
			}
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("add")
	public String addRole() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			// 添加Role信息
			role.setCreateTime(DateUtil.getSystemTime());
			roleService.addRole(role);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
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
	
	@Action("delete")
	public String deleteStudentInfos() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", true);
		try {
			String[] idsArr = ids.split("-");
			if(idsArr!=null&&idsArr.length>0){
				for (String roleid : idsArr) {
					rolePermissionRelationService.deleteAssignByRoleId(roleid);
					userRoleRelationService.deleteAssignByRoleId(roleid);
				}
				roleService.deleteRoleById(idsArr);
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
