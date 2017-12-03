package com.doctortrain.service.impl;

import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.doctortrain.bean.ClinicalRotation;
import com.doctortrain.bean.ClinicalRotationConfig;
import com.doctortrain.bean.Score;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.dao.ClinicalRotationDAO;
import com.doctortrain.dao.StudentInfoDAO;
import com.doctortrain.service.ClinicalRotationConfigService;
import com.doctortrain.service.ClinicalRotationService;
import com.doctortrain.service.ScoreService;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.util.DateUtil;

@Service("studentInfoService")
public class StudentInfoServiceImpl implements StudentInfoService {

	@Resource(name="studentInfoDAO")
	public StudentInfoDAO studentInfoDAO;
	@Resource(name="clinicalRotationService")
	ClinicalRotationService clinicalRotationService;
	@Resource(name="clinicalRotationConfigService")
	ClinicalRotationConfigService clinicalRotationConfigService;
	@Resource(name="scoreService")
	public ScoreService scoreService;
	/**
	 * 获取所有学生信息
	 */
	public List<StudentInfo> queryAllStudentInfo() throws Exception {
		return studentInfoDAO.getAllStudentInfo();
	}

	/**
	 * 添加一个学生信息
	 */
	public void addStudentInfo(StudentInfo studentInfo) throws Exception {
		studentInfoDAO.addStudentInfo(studentInfo);
		List<ClinicalRotation> crList = clinicalRotationService.getCrByStuId(studentInfo.getId());
		if("2".equals(studentInfo.getStudentStatus())){
			if(crList==null||crList.size()==0){
				clinicalRotationService.addCRPriorityNew(studentInfo,true);
			}
		}
	}
	
	public void updateStuInfo(StudentInfo studentInfo) throws Exception {
		studentInfoDAO.updateStudentInfo(studentInfo);
	}
	public void updateStudentInfo(StudentInfo studentInfo) throws Exception {
		studentInfoDAO.updateStudentInfo(studentInfo);
		List<ClinicalRotation> crList = clinicalRotationService.getCrByStuId(studentInfo.getId());
		if("2".equals(studentInfo.getStudentStatus())){
			if(crList==null||crList.size()==0){
				clinicalRotationService.addCRPriorityNew(studentInfo,false);
			}
		}
	}

	public StudentInfo getStudentInfo(String id) throws Exception {
		return studentInfoDAO.getStudentInfoById(id);
	}

	public void deleteStudentInfos(String[] ids) throws Exception {
		studentInfoDAO.deleteStudentInfo(ids);
		for (String str : ids) {
			clinicalRotationService.deleteCRByStuId(str);
			scoreService.deleteScoreById(str);
		}
	}

	public List<StudentInfo> getStudentInfoByRcId(String id) throws Exception {
		return studentInfoDAO.getStudentInfoByRcId(id);
	}
	
	/**
	 * 住院医级别自增长 +1
	 * @param id 学生id
	 * @throws Exception
	 */
	public void growRC(String id) throws Exception {
		// 获取学生信息
		StudentInfo studentInfo = studentInfoDAO.getStudentInfoById(id);
		// String 住院医级别
		String rc = studentInfo.getResidentClass();
		// int 住院医级别
		int rcInt = Integer.parseInt(rc);
		// 入学时间 年
		String inYear = studentInfo.getInTraningDate().split("-")[0];
		// 当前时间 年
		String nowYear = DateUtil.getYmd().split("-")[0];
		// 入学年 Int
		int inYearInt = Integer.parseInt(inYear);
		// 当前年 Int
		int nowYearInt = Integer.parseInt(nowYear);
		
		int tempInt = nowYearInt - inYearInt;
		
		if(!((tempInt+1)+"").equals(rc)){
			rcInt = tempInt + 1;
			String rcStr = rcInt+"";
			studentInfo.setResidentClass(rcStr);
			studentInfoDAO.updateStudentInfo(studentInfo);
		}
	}
	
	/**
	 * 住院医级别自增长 +1 
	 * @param studentInfo 学生对象
	 * @throws Exception
	 */
	public void growRC(StudentInfo studentInfo) throws Exception {
		// String 住院医级别
		String rc = studentInfo.getResidentClass();
		// int 住院医级别
		int rcInt = Integer.parseInt(rc);
		// 入学时间 年
		String inYear = studentInfo.getInTraningDate().split("-")[0];
		// 当前时间 年
		String nowYear = DateUtil.getYmd().split("-")[0];
		// 入学年 Int
		int inYearInt = Integer.parseInt(inYear);
		// 当前年 Int
		int nowYearInt = Integer.parseInt(nowYear);
		
		int tempInt = nowYearInt - inYearInt;
		
		if(!((tempInt+1)+"").equals(rc)){
			rcInt = tempInt + 1;
			String rcStr = rcInt+"";
			studentInfo.setResidentClass(rcStr);
			studentInfoDAO.updateStudentInfo(studentInfo);
		}
		
	}
	
	public void growRCAll() throws Exception{
		/*String[] noDate = DateUtil.getYmd().split("-");
		if(noDate[1].equals("01")&&noDate[2].equals("01")){
		}*/
		List<StudentInfo> stuList = studentInfoDAO.getAllStudentInfo();
		for (StudentInfo studentInfo : stuList) {
			growRC(studentInfo);
		}
	}
	
}
