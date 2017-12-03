package com.doctortrain.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.doctortrain.bean.ClinicalRotation;
import com.doctortrain.bean.Permission;
import com.doctortrain.bean.Role;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.bean.User;
import com.doctortrain.service.ClinicalRotationService;
import com.doctortrain.service.PermissionService;
import com.doctortrain.service.RolePermissionRelationService;
import com.doctortrain.service.RoleService;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.service.UserRoleRelationService;
import com.doctortrain.util.DateUtil;

@Controller("ExamAction")
@Scope("prototype")
@Namespace("/exam")
public class ExamListAction extends BaseAction {
	
	@Resource(name="studentInfoService")
	public StudentInfoService studentInfoService;
	
	@Resource(name="clinicalRotationService")
	public ClinicalRotationService clinicalRotationService;
	
	@Action(value="index",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/exam_info.jsp")})
	public String index() throws Exception{
		return SUCCESS;
	}
	

	/**
	 * 获取考试科目
	 * @return
	 * @throws Exception
	 */
	@Action("getColumns")
	public String getColumns() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			String year = DateUtil.getYmd().split("-")[0];
			List<ClinicalRotation> crList = clinicalRotationService.getYearList(year);
			Map<String,Object> map = clinicalRotationService.getNowMonthSub(crList);
			List<String> subList = (List<String>)map.get("subList");
			jsonMap.put("subcolumns", subList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	/**
	 * 1.本月所有考试的科目
	 * 2.本月所有科目考试人员
	 * 3.横坐标科目，纵坐标人员名称
	 */
	
	@Action("getList")
	public String getList() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		jsonMap.put("success", true);
		try {
			
			String year = DateUtil.getYmd().split("-")[0];
			// 当年crlist
			List<ClinicalRotation> crList = clinicalRotationService.getYearList(year);
			Map<String,Object> theMap = clinicalRotationService.getNowMonthSub(crList);
			// 本月科目列表
			List<String> subList = (List<String>) theMap.get("subList");
			// 考试名单
			List<ClinicalRotation> theCrList = (List<ClinicalRotation>) theMap.get("crList");
			Map<String, List<String>> examMap = clinicalRotationService.getExamList(theCrList, subList);
			
			
			int theSize = 0;
			int size = 0;
			List<String> sList = new ArrayList<String>();
			List<List<String>> theList = new ArrayList<List<String>>();
			for (String key : examMap.keySet()) {
				String[] keyArr = key.split("-");
				theSize = Integer.parseInt(keyArr[1]);
				sList.add(keyArr[0]);
				if(theSize>size){
					size  = theSize;
				}
				theList.add(examMap.get(key));
			}
			
			for (int i = 0; i < size; i++) {
				Map<String,Object> map = new HashMap<String,Object>();
				for (int j = 0; j < subList.size(); j++) {
					List<String> list = theList.get(j);
					if(i<list.size()){
						String value = list.get(i);
						map.put(sList.get(j), value);
					}
				}
				listResult.add(map);
			}
			
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			Map<String,Object> map = new HashMap<String,Object>();
			listResult.add(map);
			jsonMap.put("rows", listResult);
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("getById")
	public String getByUsername() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		String theUsername = (String) session.getAttribute("username");
		StudentInfo stuInfo = studentInfoService.getStudentInfo(theUsername);
		List<ClinicalRotation> list = clinicalRotationService.getCrByStuId(stuInfo.getId());
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		List<ClinicalRotation> theList = new ArrayList<ClinicalRotation>();
		String year = DateUtil.getYmd().split("-")[0];
		for (ClinicalRotation clinicalRotation : list) {
			if(year.equals(clinicalRotation.getYear())){
				theList.add(clinicalRotation);
			}
		}
		Map<String,Object> theMap = clinicalRotationService.getNowMonthSub(theList);
		List<String> subList = (List<String>) theMap.get("subList");
		for (String string : subList) {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("exam", string);
			listResult.add(map);
		}
		jsonMap.put("rows", listResult);
		return jsonOut(jsonMap);
	}
	
	
}
