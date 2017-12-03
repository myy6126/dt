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

import com.doctortrain.bean.ClinicalRotationConfig;
import com.doctortrain.bean.Permission;
import com.doctortrain.bean.Role;
import com.doctortrain.bean.TeacherInfo;
import com.doctortrain.bean.User;
import com.doctortrain.service.ClinicalRotationConfigService;
import com.doctortrain.service.PermissionService;
import com.doctortrain.service.RolePermissionRelationService;
import com.doctortrain.service.RoleService;
import com.doctortrain.service.TeacherInfoService;
import com.doctortrain.service.UserRoleRelationService;
import com.doctortrain.util.DateUtil;

@Controller("TeacherInfoAction")
@Scope("prototype")
@Namespace("/teacher")
public class TeacherAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TeacherInfo teacherInfo;
	
	private String ids;
	
	private String id;
	
	List<ClinicalRotationConfig> crcList;
	
	@Resource(name="teacherInfoService")
	private TeacherInfoService teacherInfoService; 
	
	@Resource(name="clinicalRotationConfigService")
	public ClinicalRotationConfigService clinicalRotationConfigService;
	
	public List<ClinicalRotationConfig> getCrcList() {
		return crcList;
	}

	public void setCrcList(List<ClinicalRotationConfig> crcList) {
		this.crcList = crcList;
	}

	public TeacherInfo getTeacherInfo() {
		return teacherInfo;
	}

	public void setTeacherInfo(TeacherInfo teacherInfo) {
		this.teacherInfo = teacherInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Action(value="index",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/teacher_info.jsp")})
	public String index() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="stuin",results={@Result(location="/WEB-INF/jsp/stuin.jsp")})
	public String stuin() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toAdd",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/teacher_add.jsp")})
	public String stuAdd() throws Exception{
		crcList = clinicalRotationConfigService.getAllCRC();
		return SUCCESS;
	}
	
	@Action(value="toEdit",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/teacher_edit.jsp")})
	public String stuEdit() throws Exception{
		crcList = clinicalRotationConfigService.getAllCRC();
		teacherInfo = teacherInfoService.getTeacherInfoById(teacherInfo.getId());
		return SUCCESS;
	}
	
	@Action("doEdit")
	public String doEdit() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			teacherInfo.setUpdateTime(DateUtil.getSystemTime());
			teacherInfoService.updateTeacherInfo(teacherInfo);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("getAll")
	public String getAllPermission() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		List<TeacherInfo> teacherInfoList = new ArrayList<TeacherInfo>();
		jsonMap.put("success", true);
		try {
			teacherInfoList = teacherInfoService.getAllTeacherInfo();
			for (TeacherInfo teacherInfo : teacherInfoList) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", teacherInfo.getId());
				map.put("name", teacherInfo.getName());
				map.put("birth", teacherInfo.getBirth());
				map.put("department", teacherInfo.getDepartment());
				map.put("remark", teacherInfo.getRemark());
				listResult.add(map);
			}
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("doAdd")
	public String addRole() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			teacherInfo.setCreateTime(DateUtil.getSystemTime());
			teacherInfoService.addTeacherInfo(teacherInfo);
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
			teacherInfoService.deleteTeacherInfoById(idsArr);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
}
