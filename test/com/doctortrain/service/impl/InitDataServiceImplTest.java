package com.doctortrain.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.doctortrain.bean.StudentInfo;
import com.doctortrain.service.InitDataService;
import com.doctortrain.service.ScoreService;
import com.doctortrain.service.StudentInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class InitDataServiceImplTest {
	
	@Resource(name="initDataService")
	InitDataService initDataService;
	
	@Resource(name="studentInfoService")
	public StudentInfoService studentInfoService;
	
	@Test
	public void testInit() throws Exception{
		studentInfoService.growRC("1099");
	}
	
	@Test
	public void testUpdate() throws Exception{
		StudentInfo stu  = studentInfoService.getStudentInfo("1099");
		stu.setResidentClass("6");
		studentInfoService.updateStudentInfo(stu);
	}
	
	@Test
	public void testAdd() throws Exception{
		StudentInfo stu  = new StudentInfo();
		stu.setId("1234");
		stu.setBirth("123");
		stu.setHospitalID("123");
		stu.setInTraningDate("123");
		stu.setName("123");
		stu.setPersonPic("123");
		stu.setSex("123");
		stu.setStudentStatus("123");
		stu.setTeacherID("123");
		stu.setTrainingType("123");
		stu.setResidentClass("6");
		studentInfoService.addStudentInfo(stu);
	}
	
}
