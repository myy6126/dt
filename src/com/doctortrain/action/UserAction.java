package com.doctortrain.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.doctortrain.bean.Role;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.bean.User;
import com.doctortrain.service.RoleService;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.service.UserRoleRelationService;
import com.doctortrain.service.UserService;
import com.doctortrain.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller("UserAction")
@Scope("prototype")
@Namespace("/user")
public class UserAction extends BaseAction {
	
	@Resource(name="userService")
	public UserService userService;
	
	@Resource(name="roleService")
	public RoleService roleService;
	
	@Resource(name="userRoleRelationService")
	public UserRoleRelationService userRoleRelationService;
	
	private User user;
	
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Action(value="index",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/user_info.jsp")})
	public String index() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toAdd",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/user_add.jsp")})
	public String stuAdd() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toEdit",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/user_edit.jsp")})
	public String toEdit() throws Exception{
		user = userService.getUserByUsername(user.getUsername());
		return SUCCESS;
	}
	
	@Action("doEdit")
	public String doEdit() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			user.setUpdateTime(DateUtil.getSystemTime());
			userService.updateUser(user);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	@Action("getAll")
	public String getAllUser() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		List<User> userList = new ArrayList<User>();
		jsonMap.put("success", true);
		try {
			userList = userService.getAllUser();
			for (User user : userList) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("username", user.getUsername());
				map.put("password", user.getPassword());
				map.put("email", user.getEmail());
				map.put("tel", user.getTel());
				map.put("status", user.getStatus());
				String privilege = user.getPrivilege();
				String name = null;
				if(roleService.getRoleById(privilege)==null){
					name = "";
				}else{
					name = roleService.getRoleById(privilege).getName();
				}
				map.put("privilege",  name);
				map.put("createTime", user.getCreateTime());
				map.put("updateTime", user.getUpdateTime());
				listResult.add(map);
			}
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	
	@Action("getByUsername")
	public String getUserByUsername() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		User user = null;
		jsonMap.put("success", true);
		String id = (String)session.getAttribute("username");
		try {
				user = userService.getUserByUsername(id);
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("username", user.getUsername());
				map.put("password", user.getPassword());
				map.put("email", user.getEmail());
				map.put("tel", user.getTel());
				map.put("status", user.getStatus());
				String privilege = user.getPrivilege();
				String name = null;
				if(roleService.getRoleById(privilege)==null){
					name = "";
				}else{
					name = roleService.getRoleById(privilege).getName();
				}
				map.put("privilege",  name);
				map.put("createTime", user.getCreateTime());
				map.put("updateTime", user.getUpdateTime());
				listResult.add(map);
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
			user.setCreateTime(DateUtil.getSystemTime());
			user.setUpdateTime("");
			userService.addUser(user);	
			userRoleRelationService.assign(user.getUsername(), user.getPrivilege());
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
			for (int i = 0; i < idsArr.length; i++) {
				userRoleRelationService.deleteAssignByUsername(idsArr[i]);
			}
			if(idsArr!=null&&idsArr.length>0){
				userService.deleteUserByUsername(idsArr);
			}else{
				jsonMap.put("success", false);
			}
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
	@Action("getAllRole")
	public String getAllRole() throws Exception{
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
				listResult.add(map);
			}
			jsonMap.put("listResult", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
}
