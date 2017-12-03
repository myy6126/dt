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

import com.doctortrain.bean.OperationLog;
import com.doctortrain.bean.Permission;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.bean.User;
import com.doctortrain.service.OperationLogService;
import com.doctortrain.service.StudentInfoService;
import com.opensymphony.xwork2.ActionContext;

@Controller("OperationLogAction")
@Scope("prototype")
@Namespace("/operationlog")
public class OperationLogAction extends BaseAction {
	
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Resource(name="operationLogService")
	public OperationLogService operationLogService;
	
	@Action(value="index",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/operationlog_info.jsp")})
	public String index() throws Exception{
		return SUCCESS;
	}
	
	@Action("getAll")
	public String getAll() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		List<OperationLog> perList = new ArrayList<OperationLog>();
		jsonMap.put("success", true);
		try {
			perList = operationLogService.getAll();
			for (OperationLog ol : perList) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", ol.getId());
				map.put("ip", ol.getIp());
				if(ol.getOperator()!=null){
					map.put("user", ol.getOperator());
				}
				map.put("time", ol.getTime());
				map.put("module", ol.getModule());
				map.put("node", ol.getNode());
				map.put("type", ol.getType());
				listResult.add(map);
			}
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	@Action("delete")
	public String delete() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			String[] idsArr = ids.split("-");
			operationLogService.deleteOperationLog(idsArr);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	@Action("deleteAll")
	public String deleteAll() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			operationLogService.deleteAll();
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	
	
}
