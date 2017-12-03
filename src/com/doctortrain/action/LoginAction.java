package com.doctortrain.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.doctortrain.bean.Announcement;
import com.doctortrain.bean.Role;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.bean.User;
import com.doctortrain.exception.ApplicationException;
import com.doctortrain.service.AnnouncementService;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.service.UserRoleRelationService;
import com.doctortrain.service.UserService;
import com.doctortrain.vo.Page;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction {
	
	private String username;
	private String password;
	
	private Page page = new Page();
	
	private int annId;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="studentInfoService")
	public StudentInfoService studentInfoService;
	
	@Resource(name="userRoleRelationService")
	private UserRoleRelationService userRoleRelationService;
	
	@Resource(name="announcementService")
	public AnnouncementService announcementService;
	
	public int getAnnId() {
		return annId;
	}

	public void setAnnId(int annId) {
		this.annId = annId;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Action(value="login",interceptorRefs={@InterceptorRef("basicStack")})
	public String login()throws Exception{
		//{"success":true} {"success":false,"errorMsg":""}
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		try {
			//身份验证
			String ip = request.getRemoteAddr();
			//System.out.println(ip);
			User user = userService.login(username,password,ip);
			List<Role> roleList = userRoleRelationService.getAssignedByUsername(username);
			StudentInfo stu = studentInfoService.getStudentInfo(username);
			if(roleList.size()==1){
				for (Role role : roleList) {
					String type = role.getType();
					session.setAttribute("operation_type", type);
					
				}
			}
			if(stu!=null){
				session.setAttribute("stu_rc", stu.getResidentClass());
			}
			//程序能够执行到这个位置，肯定没有发生异常，验证通过，将用户放到session中
			//ServletActionContext.getRequest().getSession().setAttribute("session_user", user);
			session.setAttribute("username", user.getUsername());
			session.setAttribute("session_user", user);
			//userService.getModuleByUserId(user.getUsernum());
			//获取该用户能够操作的所有url
			//Set<String> urls = userService.getUrlsByUserId(user.getId());
			//将用户拥有的url放到session中
			//session.setAttribute("urls", urls);
			
			jsonMap.put("success", true);
		} catch (ApplicationException e) {
			jsonMap.put("success", false);
			jsonMap.put("errorMsg", e.getMessage());
		}
		return jsonOut(jsonMap);
	}
	
	@Action(value="logout",results={@Result(location="/signin.jsp")})
	public String logout()throws Exception{
		//销毁session
		session.invalidate();
		return SUCCESS;
	}
	
	@Action(value="index",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/index.jsp")})
	public String stuAdd() throws Exception{
		return SUCCESS;
	}
	
	@Action("getMenu")
	public String getMenu() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		List<Map<String,Object>> mList = new ArrayList<Map<String,Object>>();
		jsonMap.put("success", true);
		try {
			User theUser = (User)session.getAttribute("session_user");
			Map<String,Object> olmoMap = userService.getModuleAndOperationByUsername(theUser.getUsername());
			//ServletActionContext.getRequest().getSession().setAttribute("operationType", );
			jsonMap.put("menu", olmoMap.get("moduleUrls"));
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
	@Action("getAnn")
	public String getPublishAnn() throws Exception{
		//System.out.println("contextPaht:"+ServletActionContext.getServletContext().getContextPath()+"<<");
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		List<Map<String,Object>> theList = new ArrayList<Map<String,Object>>();
		jsonMap.put("success", true);
		try {
			page.setTotalSize(announcementService.getCount());
			List<Announcement> dList = announcementService.getAllPublishAnn(page.getStartIndex(), page.getPageSize());
			page.setDataList(dList);
			for (Announcement announcement : dList) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", announcement.getId());
				map.put("title", announcement.getTitle());
				if(announcement.getCreateTime()==null){
					map.put("publishTime", "");
				}else{
					map.put("publishTime", announcement.getCreateTime().split(" ")[0]);
				}
				theList.add(map);
			}
			//annList = announcementService.getAllPublishAnn();
			jsonMap.put("pageNo", page.getPageNo());
			jsonMap.put("pageSize", page.getTotalPage());
			jsonMap.put("dataAnn", theList);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
	@Action("inAnn")
	public String getAnn() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", true);
		try {
			Announcement ann = announcementService.getAnnouncementById(annId);
			jsonMap.put("title", ann.getTitle());
			jsonMap.put("time", ann.getPublishTime());
			jsonMap.put("text", ann.getText());
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
	
	
}
