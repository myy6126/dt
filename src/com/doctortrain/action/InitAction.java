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
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.doctortrain.bean.Role;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.bean.User;
import com.doctortrain.service.DtConfigService;
import com.doctortrain.service.InitDataService;
import com.doctortrain.service.RoleService;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.service.UserRoleRelationService;
import com.doctortrain.service.UserService;
import com.doctortrain.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller("InitAction")
@Scope("prototype")
@Namespace("/init")
public class InitAction extends BaseAction {
	
	@Resource(name="initDataService")
	public InitDataService initDataService;
	
	@Resource(name="dtConfigService")
	public DtConfigService dtConfigService;
	
	@Action(value="index",results={@Result(location="/WEB-INF/jsp/init.jsp")})
	public String index() throws Exception{
		return SUCCESS;
	}
	
	@Action("doInit")
	public String doInit() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			if(dtConfigService.getAll().size()>0){
				jsonMap.put("success", false);
			}else{
				initDataService.importXmlFile("init-data.xml");
			}
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
}
