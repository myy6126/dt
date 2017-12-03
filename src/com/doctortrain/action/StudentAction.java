package com.doctortrain.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.directwebremoting.json.types.JsonObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.doctortrain.bean.ClinicalRotation;
import com.doctortrain.bean.ClinicalRotationConfig;
import com.doctortrain.bean.DtConfig;
import com.doctortrain.bean.Role;
import com.doctortrain.bean.Score;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.bean.TeacherInfo;
import com.doctortrain.bean.User;
import com.doctortrain.service.ClinicalRotationConfigService;
import com.doctortrain.service.ClinicalRotationService;
import com.doctortrain.service.DtConfigService;
import com.doctortrain.service.RoleService;
import com.doctortrain.service.ScoreService;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.service.TeacherInfoService;
import com.doctortrain.service.UserRoleRelationService;
import com.doctortrain.service.UserService;
import com.doctortrain.util.DateUtil;

@Controller("StudentAction")
@Scope("prototype")
@Namespace("/stu")
public class StudentAction extends BaseAction {
	
	@Resource(name="studentInfoService")
	public StudentInfoService studentInfoService;
	
	@Resource(name="scoreService")
	public ScoreService scoreService;
	
	@Resource(name="clinicalRotationService")
	ClinicalRotationService clinicalRotationService;
	
	@Resource(name="teacherInfoService")
	private TeacherInfoService teacherInfoService; 
	
	@Resource(name="clinicalRotationConfigService")
	public ClinicalRotationConfigService clinicalRotationConfigService;
	
	@Resource(name="dtConfigService")
	public DtConfigService dtConfigService;
	
	@Resource(name="roleService")
	public RoleService roleService;
	
	@Resource(name="userRoleRelationService")
	public UserRoleRelationService userRoleRelationService;
	
	@Resource(name="userService")
	public UserService userService;
	
	private ClinicalRotationConfig clinicalRotationConfig;
	
	private StudentInfo studentInfo;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	
	private String ids;
	
	private String theUsername;
	
	private DtConfig dtConfig;
	
	public DtConfig getDtConfig() {
		return dtConfig;
	}

	public void setDtConfig(DtConfig dtConfig) {
		this.dtConfig = dtConfig;
	}

	public ClinicalRotationConfig getClinicalRotationConfig() {
		return clinicalRotationConfig;
	}

	public void setClinicalRotationConfig(
			ClinicalRotationConfig clinicalRotationConfig) {
		this.clinicalRotationConfig = clinicalRotationConfig;
	}

	public String getTheUsername() {
		return theUsername;
	}

	public void setTheUsername(String theUsername) {
		this.theUsername = theUsername;
	}

	// 传输大小
    private static final int BUFFER_SIZE = 16 * 1024;
    
    // 封装上传文件域的属性  
    private File uploadImageFile;  

    // 学生照片名称
    private String studentImageName;
    
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getStudentImageName() {
		return studentImageName;
	}

	public void setStudentImageName(String studentImageName) {
		this.studentImageName = studentImageName;
	}

	public File getUploadImageFile() {
		return uploadImageFile;
	}

	public void setUploadImageFile(File uploadImageFile) {
		this.uploadImageFile = uploadImageFile;
	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}
	
	@Action(value="idGrow",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/stu_grow.jsp")})
	public String idGrow() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toEditGrow",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/stu_edit_grow.jsp")})
	public String toEditGrow() throws Exception{
		String name = dtConfig.getName();
		dtConfig = dtConfigService.getByName(name);
		String value = dtConfig.getValue();
		dtConfig.setValue((Integer.parseInt(value)+1)+"");
		return SUCCESS;
	}
	
	@Action(value="index",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/stu_info.jsp")})
	public String index() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toAdd",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/stu_add.jsp")})
	public String stuAdd() throws Exception{
		return SUCCESS;
	}
	@Action(value="toCrh",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/stu_crh.jsp")})
	public String stuToCrh() throws Exception{
		return SUCCESS;
	}
	
	@Action("getAll")
	public String getAllAction() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		List<StudentInfo> listStu = new ArrayList<StudentInfo>();
		jsonMap.put("success", true);
		try {
			log.info("studentInfoService.queryAllStudentInfo()");
			listStu = studentInfoService.queryAllStudentInfo();
			log.info("studentInfoService.queryAllStudentInfo() - "+listStu.size());
			for (StudentInfo studentInfo : listStu) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id",studentInfo.getId());
				map.put("name",studentInfo.getName());
				map.put("birth",studentInfo.getBirth());
				map.put("sex",studentInfo.getSex());
				map.put("trainingDate", studentInfo.getInTraningDate());
				map.put("trainingType", studentInfo.getTrainingType());
				map.put("pic", studentInfo.getPersonPic());
				map.put("hospital", studentInfo.getHospitalID());
				try {
					TeacherInfo teacher = teacherInfoService.getTeacherInfoById(Integer.parseInt(studentInfo.getTeacherID()));
					String tname = teacher.getName();
					map.put("teacher", tname);
				} catch (Exception e) {
				}
				// 正在轮转科室
				String inClass = clinicalRotationService.getCrNow(studentInfo.getId());
				if(inClass!=null){
					map.put("inClass", inClass);
				}else{
					map.put("inClass", "");
				}
				String inDate = studentInfo.getInTraningDate();
				
				int resdentClass = getRClass(inDate);
				
				map.put("residentClass", resdentClass);
				if("1".equals(studentInfo.getStudentStatus())){
					map.put("stauts", "未完成");
				}
				if("2".equals(studentInfo.getStudentStatus())){
					map.put("stauts", "正在轮转");
				}
				if("3".equals(studentInfo.getStudentStatus())){
					map.put("stauts", "已完成");
				}
				/*if("3".equals(clinicalRotationService.getCRById(studentInfo.getId())){
					map.put("nowStatus", "已完成");
				}*/
				listResult.add(map);
			}
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("getByUsername")
	public String getByUsernameAction() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		jsonMap.put("success", true);
		try {
			
			theUsername = (String) session.getAttribute("username");
			StudentInfo stuInfo = studentInfoService.getStudentInfo(theUsername);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id",stuInfo.getId());
			map.put("name",stuInfo.getName());
			map.put("birth",stuInfo.getBirth());
			map.put("sex",stuInfo.getSex());
			map.put("trainingDate", stuInfo.getInTraningDate());
			map.put("trainingType", stuInfo.getTrainingType());
			map.put("pic", stuInfo.getPersonPic());
			map.put("hospital", stuInfo.getHospitalID());
			try {
				TeacherInfo teacher = teacherInfoService.getTeacherInfoById(Integer.parseInt(stuInfo.getTeacherID()));
				String tname = teacher.getName();
				map.put("teacher", tname);
			} catch (Exception e) {
			}
			// 正在轮转科室
			String inClass = clinicalRotationService.getCrNow(stuInfo.getId());
			if(inClass!=null){
				map.put("inClass", inClass);
			}else{
				map.put("inClass", "");
			}
			String inDate = DateUtil.getSystemTime();
			int resdentClass = getRClass(inDate);
			map.put("residentClass", resdentClass);
			if("1".equals(stuInfo.getStudentStatus())){
				map.put("stauts", "未完成");
			}
			if("2".equals(stuInfo.getStudentStatus())){
				map.put("stauts", "正在轮转");
			}
			if("3".equals(stuInfo.getStudentStatus())){
				map.put("stauts", "已完成");
			}
			/*if("3".equals(clinicalRotationService.getCRById(studentInfo.getId())){
				map.put("nowStatus", "已完成");
			}*/
			listResult.add(map);
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("add")
	public String upload() throws Exception{
		boolean update = true;
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		log.info("add");
		jsonMap.put("success", true);
		try {
			
			// 学生成绩添加
			/* 存储img标签
			 * String src_path ="stu_img/"+studentInfo.getPersonPic()+".jpg";
			String alt = studentInfo.getName();
			String path = "<img src='"+src_path+"'  alt='"+alt+"' />";
			studentInfo.setPersonPic(path);*/
			int theId = 0;
			String strId = "";
			Score score = new Score();
			String year = studentInfo.getInTraningDate().split("-")[0];
			String idHead = year.substring(2,4);
			DtConfig dt = dtConfigService.getByName(year);
			if(dt!=null){
				theId = Integer.parseInt(dt.getValue());
				++theId;
				strId = theId+"";
				dt.setValue(theId+"");
			}else{
				update = false;
				dt = new DtConfig();
				dt.setName(year);
				dt.setValue(idHead+"001");
				strId = idHead+"001";
			}
			StudentInfo stu = studentInfoService.getStudentInfo(strId);
			if(stu==null){
				studentInfo.setId(strId);
				studentInfoService.addStudentInfo(studentInfo);
			}else{
				jsonMap.put("success", false);
				jsonMap.put("msg", "记录编号已存在！请检查新增长编号!");
			}
			// 学生成绩生成
			score.setID(strId);
			score.setRadScore("成绩暂无");
			score.setMedImaScore("成绩暂无");
			score.setMedOncScore("成绩暂无");
			score.setSurOncScore("成绩暂无");
			score.setPhthonScore("成绩暂无");
			score.setMiddleExam("成绩暂无");
			score.setStageExamFirst("成绩暂无");
			score.setStageExamSecond("成绩暂无");
			score.setFinalExam("成绩暂无");
			score.setRemark("");
			scoreService.addScore(score);
			if(update){
				dtConfigService.updateDt(dt);
			}else{
				dtConfigService.addDt(dt);
			}
			
			// 用户角色分配
			User user = new User();
			List<Role> roleList = roleService.getRolesByType("0");
			user.setCreateTime(DateUtil.getSystemTime());
			user.setPassword(strId);
			user.setPrivilege(roleList.get(0).getId());
			user.setStatus("1");
			user.setUsername(strId);
			userService.addUser(user);
			userRoleRelationService.assign(strId, user.getPrivilege());
			jsonMap.put("username", strId);
			jsonMap.put("passowrd", strId);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}  
		return jsonOut(jsonMap);
	}
	
	@Action("uploadImage")
	public String uploadImageAction() throws Exception{
		// 获取图片后缀名
		//String imageType = uploadImageFile.getName().split(".")[1];
		//统一格式jpg
		File storageFile = new File(ServletActionContext.getServletContext()  
		        .getRealPath("/stu_img") + "/"+studentImageName+"."+"jpg");  
		log.info("storageFile:"+storageFile.getAbsolutePath());
		log.info("uploadImageFile:"+uploadImageFile.getAbsolutePath());
		// 图片存储服务器
		copy(uploadImageFile, storageFile);
		return null;
	}
	
	/**
	 * 去编辑页面
	 * @return
	 * @throws Exception
	 */
	@Action(value="toEdit",results={@Result(location="/WEB-INF/jsp/stu_edit.jsp")})
	public String toEdit() throws Exception{
		String id = studentInfo.getId();
		studentInfo = studentInfoService.getStudentInfo(id);
		return SUCCESS;
	}
	
	
	
	/**
	 * 做更新操作
	 */
	@Action("doEdit")
	public String doEdit() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", true);
		try {
			studentInfoService.updateStudentInfo(studentInfo);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
	@Action("delete")
	public String deleteStudentInfos() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", true);
		try {
			String[] idsArr = ids.split("-");
			if(idsArr!=null&&idsArr.length>0){
				studentInfoService.deleteStudentInfos(idsArr);
				for (int i = 0; i < idsArr.length; i++) {
					userRoleRelationService.deleteAssignByUsername(idsArr[i]);
				}
				userService.deleteUserByUsername(idsArr);
			}else{
				jsonMap.put("success", false);
			}
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
	/**
	 * 将上传的文件存入服务器
	 * @param uploadFile
	 * 			目标文件
	 * @param saveFile
	 * 			存入文件
	 */
	public void copy(File uploadFile, File saveFile) {  
        try {  
            InputStream in = null;  
            OutputStream out = null;  
            try {  
                in = new BufferedInputStream(new FileInputStream(uploadFile),  
                        BUFFER_SIZE);  
                out = new BufferedOutputStream(new FileOutputStream(saveFile),  
                        BUFFER_SIZE);  
                byte[] buffer = new byte[BUFFER_SIZE];  
                while (in.read(buffer) > 0) {  
                    out.write(buffer);  
                }  
            } finally {  
                if (null != in) {  
                    in.close();  
                }  
                if (null != out) {  
                    out.close();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	
	@Action("outExcel")
	public String outExcel() throws Exception{

		List<StudentInfo> stuList = studentInfoService.queryAllStudentInfo();
		//创建HSSFWorkbook对象(excel的文档对象)  
	    HSSFWorkbook wb = new HSSFWorkbook();  
	    HSSFCellStyle cellStyle = wb.createCellStyle(); 
	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中 
	    //建立新的sheet对象（excel的表单）  
	    HSSFSheet sheet=wb.createSheet("学生信息表");  
	    //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个  
	    HSSFRow row1=sheet.createRow(0);  
	    //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个  
	    HSSFCell cell=row1.createCell(0);  
	      //设置单元格内容  
		cell.setCellValue("学生信息表一览");  
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,10));  
		
		//在sheet里创建第二行  
		HSSFRow row2=sheet.createRow(1);
		//创建单元格并设置单元格内容  

		row2.createCell(0).setCellValue("编号");
		row2.createCell(1).setCellValue("姓名");
		row2.createCell(2).setCellValue("出生年月");
		row2.createCell(3).setCellValue("性别");
		row2.createCell(4).setCellValue("入院时间");
		row2.createCell(5).setCellValue("身份职称");
		row2.createCell(6).setCellValue("所属医院");
		row2.createCell(7).setCellValue("导师");
		row2.createCell(8).setCellValue("住院级别");
		row2.createCell(9).setCellValue("规培状态");
		
		int index = 2 ;
		for (StudentInfo studentInfo : stuList) {
			HSSFRow row3=sheet.createRow(index);  
			row3.createCell(0).setCellValue(studentInfo.getId());
			row3.createCell(1).setCellValue(studentInfo.getName());
			row3.createCell(2).setCellValue(studentInfo.getBirth());
			row3.createCell(3).setCellValue(studentInfo.getSex());
			row3.createCell(4).setCellValue(studentInfo.getInTraningDate());
			row3.createCell(5).setCellValue(studentInfo.getTrainingType());
			
			row3.createCell(6).setCellValue(studentInfo.getHospitalID());
			if(studentInfo.getTeacherID()==null||"".equals(studentInfo.getTeacherID())){
				row3.createCell(7).setCellValue("");
			}else{
				TeacherInfo teaInfo = teacherInfoService.getTeacherInfoById(Integer.parseInt(studentInfo.getTeacherID()));
				row3.createCell(7).setCellValue(teaInfo.getName());
			}
			String inDate = studentInfo.getInTraningDate();
			int resdentClass = getRClass(inDate);
			row3.createCell(8).setCellValue(resdentClass);
			if("2".equals(studentInfo.getStudentStatus())){
				row3.createCell(9).setCellValue("正在轮转");
			}else if("1".equals(studentInfo.getStudentStatus())){
				row3.createCell(9).setCellValue("未完成");
			}else if("3".equals(studentInfo.getStudentStatus())){
				row3.createCell(9).setCellValue("已完成");
			}
			index = index + 1; 
		}
	  
	    //输出Excel文件  
	    OutputStream output=response.getOutputStream();  
	    response.reset();  
	    response.setHeader("Content-disposition", "attachment; filename=studentsInfo.xls");  
	    response.setContentType("application/msexcel");          
	    wb.write(output);  
	    output.close();
	    return jsonOut(output);
	}
	
	@Action("getAllteacher")
	public String getTeachInfo() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			List<TeacherInfo> tList = teacherInfoService.getAllTeacherInfo();
			jsonMap.put("tList", tList);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	/**
	 * 手动外院信息开始轮转
	 * @return
	 * @throws Exception
	 */
	@Action("stucrh")
	public String stuCrh() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", true);
		try {
			clinicalRotationConfig.setSubject("外院");
			int monthCount = clinicalRotationService.countMonth(clinicalRotationConfig.getStartTime(), clinicalRotationConfig.getEndTime());
			clinicalRotationConfig.setMonthCount(monthCount);
			String[] idArr = ids.split("-");
			for (int i = 0; i < idArr.length; i++) {
				StudentInfo stuInfo = studentInfoService.getStudentInfo(idArr[i]);
				if(stuInfo.getStudentStatus().equals("2")){
					continue;
				}
				stuInfo.setStudentStatus("2");
				List<ClinicalRotation> list = clinicalRotationService.getCrByStuId(idArr[i]);
				if(list.size()==0){
					studentInfoService.updateStuInfo(stuInfo);
					clinicalRotationService.addCRPriorityNew(stuInfo, clinicalRotationConfig);
				}
			}
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
	/**
	 * 默认外院信息开始轮转
	 * @return
	 * @throws Exception
	 */
	@Action("stucr")
	public String stuCr() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", true);
		try {
			String[] idsArr = ids.split("-");
			for (int i = 0; i < idsArr.length; i++) {
				StudentInfo stuInfo = studentInfoService.getStudentInfo(idsArr[i]);
				stuInfo.setStudentStatus("2");
				if(clinicalRotationService.getCrByStuId(idsArr[i]).size()==0){
					studentInfoService.updateStuInfo(stuInfo);
					clinicalRotationService.addCRPriorityNew(stuInfo, false);
				}
			}
			jsonMap.put("success", true);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
	@Action("getAllGrow")
	public String getAllGrow() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		jsonMap.put("success", true);
		try {
			 List<DtConfig> tList = dtConfigService.getAllGrow();
			 for (DtConfig dtConfig : tList) {
				 Map<String,Object> map = new HashMap<String, Object>();
				 map.put("id", dtConfig.getId());
				 map.put("name", dtConfig.getName());
				 String newNum = (Integer.parseInt(dtConfig.getValue())+1)+"";
				 map.put("value", newNum);
				 listResult.add(map);
			}
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			e.printStackTrace();
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("doEditGrow")
	public String doEditGrow() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			String value = dtConfig.getValue();
			String useValue = (Integer.parseInt(value)-1)+"";
			dtConfig.setValue(useValue);
			dtConfigService.updateDt(dtConfig);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	/**
	 * 删除增长编号
	 * @return
	 * @throws Exception
	 */
	@Action("doDelGrow")
	public String doDelGrow() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		boolean isExist = false;
		try {
			List<StudentInfo> stuList = studentInfoService.queryAllStudentInfo();
			String year = dtConfig.getName();
			for (StudentInfo studentInfo : stuList) {
				String stu_In_date = studentInfo.getInTraningDate();
				if(stu_In_date.contains(year)){
					isExist = true;
					break;
				}
			}
			if(isExist){
				jsonMap.put("msg", "年份正在使用!");
				jsonMap.put("success", false);
			}else{
				dtConfigService.deleteDtByName(year);
			}
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	

	public Integer getRClass(String inDate) throws Exception{
		
		String[] inDateArr = inDate.split("-");
		
		int inYear = Integer.parseInt(inDateArr[0]);
		
		String[] nowDateArr = DateUtil.getYmd().split("-");
		
		int nowYear = Integer.parseInt(nowDateArr[0]);
		int nowMonth = Integer.parseInt(nowDateArr[1]);
		
		int temp = nowYear - inYear;
		
		if(temp==0){
			return 1;
		}
		
		if(temp==1){
			if(nowMonth<9){
				return 1;
			}
			return 2;
		}
		
		if(temp==2){
			if(nowMonth<9){
				return 2;
			}
			return 3;
		}
		return 3;
	}
	
	
}
