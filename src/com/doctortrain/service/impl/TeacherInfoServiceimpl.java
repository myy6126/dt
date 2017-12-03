package com.doctortrain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doctortrain.bean.Role;
import com.doctortrain.bean.TeacherInfo;
import com.doctortrain.dao.PermissionDAO;
import com.doctortrain.dao.RoleDAO;
import com.doctortrain.dao.TeacherInfoDAO;
import com.doctortrain.service.RoleService;
import com.doctortrain.service.TeacherInfoService;

@Service("teacherInfoService")
public class TeacherInfoServiceimpl implements TeacherInfoService {
	
	@Resource(name="teacherInfoDao")
	public TeacherInfoDAO teacherInfoDao;

	public void addTeacherInfo(TeacherInfo teacherInfo) throws Exception {
		teacherInfoDao.addTeacherInfo(teacherInfo);
	}

	public TeacherInfo getTeacherInfoById(int id) throws Exception {
		return teacherInfoDao.getTeacherInfoById(id);
	}

	public List<TeacherInfo> getAllTeacherInfo() throws Exception {
		return teacherInfoDao.getAllTeacherInfoes();
	}

	public void updateTeacherInfo(TeacherInfo teacherInfo) throws Exception {
		teacherInfoDao.updateTeacherInfo(teacherInfo);
	}

	public void deleteTeacherInfoById(String[] ids) throws Exception {
		teacherInfoDao.deleteTeacherInfoById(ids);
	}

}
