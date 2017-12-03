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

import com.doctortrain.bean.ClinicalRotation;
import com.doctortrain.bean.ClinicalRotationConfig;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.service.ClinicalRotationConfigService;
import com.doctortrain.service.ClinicalRotationService;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.util.DateUtil;


@Controller("ClinicalRotationConfigAction")
@Scope("prototype")
@Namespace("/clinicalRotationConfig")
public class ClinicalRotationConfigAction extends BaseAction {

	private ClinicalRotation clinicalRotation;
	
	private ClinicalRotationConfig clinicalRotationConfig;
	
	private String ids;

	@Resource(name="clinicalRotationService")
	public ClinicalRotationService clinicalRotationService;
	@Resource(name="clinicalRotationConfigService")
	public ClinicalRotationConfigService clinicalRotationConfigService;
	
	@Resource(name="studentInfoService")
	public StudentInfoService studentInfoService;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public ClinicalRotation getClinicalRotation() {
		return clinicalRotation;
	}

	public void setClinicalRotation(ClinicalRotation clinicalRotation) {
		this.clinicalRotation = clinicalRotation;
	}

	public ClinicalRotationConfig getClinicalRotationConfig() {
		return clinicalRotationConfig;
	}

	public void setClinicalRotationConfig(
			ClinicalRotationConfig clinicalRotationConfig) {
		this.clinicalRotationConfig = clinicalRotationConfig;
	}

	@Action(value="index",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/crc_info.jsp")})
	public String index() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toCrcAdd",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/crc_add.jsp")})
	public String toCrcAdd() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toCrcEdit",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/crc_edit.jsp")})
	public String toCrcEdit() throws Exception{
		clinicalRotationConfig =clinicalRotationConfigService.getCRCById(clinicalRotationConfig.getId()); 
		return SUCCESS;
	}
	
	@Action("getConfigAll")
	public String getConfigAll() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		List<ClinicalRotationConfig> crList = new ArrayList<ClinicalRotationConfig>();
		jsonMap.put("success", true);
		try {
			crList = clinicalRotationConfigService.getAllCRC();
			for (ClinicalRotationConfig cr : crList) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", cr.getId());
				map.put("subject", cr.getSubject());
				map.put("startTime", cr.getStartTime());
				map.put("endTime", cr.getEndTime());
				map.put("remark", cr.getRemark());
				listResult.add(map);
			}
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	@Action("doConfigAdd")
	public String doAdd() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			clinicalRotationConfig.setCreateTime(DateUtil.getSystemTime());
			clinicalRotationConfigService.addCRC(clinicalRotationConfig);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("doConfigEdit")
	public String doEdit() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			clinicalRotationConfig.setUpdateTime(DateUtil.getSystemTime());
			clinicalRotationConfigService.updateCRC(clinicalRotationConfig);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("doConfigDel")
	public String doDel() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			String [] idArr = ids.split("-");
			for (int i = 0; i < idArr.length; i++) {
				clinicalRotationConfigService.deleteCRCById(Integer.parseInt(idArr[i]));
			}
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	
	
}
