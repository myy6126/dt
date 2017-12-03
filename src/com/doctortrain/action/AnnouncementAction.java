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

import com.doctortrain.bean.Announcement;
import com.doctortrain.bean.Role;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.bean.User;
import com.doctortrain.service.AnnouncementService;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.util.DateUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller("AnnouncementAction")
@Scope("prototype")
@Namespace("/announcement")
public class AnnouncementAction extends BaseAction {
	
	@Resource(name="announcementService")
	public AnnouncementService announcementService;
	
	private Announcement announcement;
	
	private int id;
	
	private String ids;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	@Action(value="index",results={@Result(location="/WEB-INF/jsp/announcement_info.jsp")})
	public String index() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toAdd",results={@Result(location="/WEB-INF/jsp/announcement_add.jsp")})
	public String stuAdd() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toEdit",results={@Result(location="/WEB-INF/jsp/announcement_edit.jsp")})
	public String stuEdit() throws Exception{
		announcement = announcementService.getAnnouncementById(announcement.getId());
		return SUCCESS;
	}
	
	@Action(value="toAnn",results={@Result(location="/WEB-INF/jsp/ann.jsp")})
	public String toAnn() throws Exception{
		announcement = announcementService.getAnnouncementById(announcement.getId());
		return SUCCESS;
	}
	
	@Action("getAll")
	public String getAll() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		List<Announcement> annList = new ArrayList<Announcement>();
		jsonMap.put("success", true);
		try {
			annList = announcementService.getAllAnn();
			for (Announcement ann : annList) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", ann.getId());
				map.put("title", ann.getTitle());
				map.put("username", ann.getUsername());
				map.put("createTime", ann.getCreateTime());
				map.put("publishTime", ann.getPublishTime());
				if(ann.getStatus().equals("1")){
					map.put("status", "已发布");
				}
				if(ann.getStatus().equals("0")){
					map.put("status", "未发布");
				}
				listResult.add(map);
			}
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("add")
	public String doAddAnnouncement() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			announcement.setCreateTime(DateUtil.getSystemTime());
			announcement.setUpdateTime("");
			if("1".equals(announcement.getStatus())){
				announcement.setPublishTime(DateUtil.getSystemTime());
			}
			announcementService.addAnnouncement(announcement);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("doEdit")
	public String doEdit() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			announcement.setUpdateTime(DateUtil.getSystemTime());
			announcementService.updateAnnouncement(announcement);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("publish")
	public String publish() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			Announcement ann = announcementService.getAnnouncementById(id);
			ann.setStatus("1");
			ann.setPublishTime(DateUtil.getSystemTime());
			announcementService.updateAnnouncement(ann);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("delete")
	public String delete() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", true);
		try {
			String[] idsArr = ids.split("-");
			int[] idsArrInt = new int[idsArr.length];
			if(idsArr!=null&&idsArr.length>0){
				for (int i = 0; i < idsArr.length; i++) {
					idsArrInt[i] = Integer.parseInt(idsArr[i]);
				}
				announcementService.deleteByAnnouncementId(idsArrInt);
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
