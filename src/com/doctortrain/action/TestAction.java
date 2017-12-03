package com.doctortrain.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Namespace("/test")
@Scope("prototype")
public class TestAction extends BaseAction{
	
	@Action("table")
	public String tTable() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		Map<String,Object> stuMap = new HashMap<String,Object>();
		stuMap.put("id", "001");
		stuMap.put("name", "张三");
		stuMap.put("class", "三年一班");
		stuMap.put("teacher", "网红");
		stuMap.put("time", "2016年11月21日");
		jsonMap.put("result", stuMap);
		return jsonOut(jsonMap);
	}
	
}
