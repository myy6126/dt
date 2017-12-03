package com.doctortrain.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.doctortrain.bean.Permission;
import com.doctortrain.bean.Score;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.service.PermissionService;
import com.doctortrain.service.RolePermissionRelationService;
import com.doctortrain.service.ScoreService;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.util.DateUtil;

@Controller("ScoreAction")
@Scope("prototype")
@Namespace("/score")
public class ScoreAction extends BaseAction {
	
	
	@Resource(name="scoreService")
	public ScoreService scoreService;
	@Resource(name="studentInfoService")
	public StudentInfoService studentInfoService;
	
	private Score score;
	private StudentInfo studentInfo;
	private String ids;
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	@Action(value="index",results={@Result(location="/WEB-INF/jsp/score_info.jsp")})
	public String index() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toAdd",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/score_add.jsp")})
	public String stuAdd() throws Exception{
		return SUCCESS;
	}
	
	/**
	 * 去编辑页面
	 * @return
	 * @throws Exception
	 */
	@Action(value="toEdit",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/score_edit.jsp")})
	public String toEdit() throws Exception{
		String id = score.getID();
		score = scoreService.getScoreById(id);
		studentInfo = studentInfoService.getStudentInfo(id);
		return SUCCESS;
	}
	
	/**
	 * 做修改操作
	 */
	@Action("doEdit")
	public String doEdit() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("success", true);
		try {
			scoreService.updateScore(score);
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
	@Action("getAll")
	public String getAllPermission() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		List<Score> scoreList = new ArrayList<Score>();
		jsonMap.put("success", true);
		try {
			scoreList = scoreService.getAllScore();
			for (Score score : scoreList) {
				Map<String,Object> map = new HashMap<String, Object>();
				StudentInfo stuInfo = studentInfoService.getStudentInfo(score.getID());
				map.put("id", score.getID());
				if(stuInfo!=null){
					map.put("name", stuInfo.getName());
				}else{
					map.put("name", "");
				}
				map.put("rad", score.getRadScore());
				map.put("medOnc", score.getMedOncScore());
				map.put("surOnc", score.getSurOncScore());
				map.put("Phthon", score.getPhthonScore());
				map.put("medIma", score.getMedImaScore());
				map.put("first", score.getStageExamFirst());
				map.put("second", score.getStageExamSecond());
				map.put("middle", score.getMiddleExam());
				map.put("final", score.getFinalExam());
				map.put("remark", score.getRemark());
				listResult.add(map);
			}
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("getById")
	public String getById() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		Score score = null;
		jsonMap.put("success", true);
		String id = (String)session.getAttribute("username");
		try {
			StudentInfo stuInfo = studentInfoService.getStudentInfo(id);
			score = scoreService.getScoreById(id);
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", score.getID());
				map.put("name", stuInfo.getName());
				map.put("rad", score.getRadScore());
				map.put("medOnc", score.getMedOncScore());
				map.put("surOnc", score.getSurOncScore());
				map.put("Phthon", score.getPhthonScore());
				map.put("medIma", score.getMedImaScore());
				map.put("first", score.getStageExamFirst());
				map.put("second", score.getStageExamSecond());
				map.put("middle", score.getMiddleExam());
				map.put("final", score.getFinalExam());
				map.put("remark", score.getRemark());
				listResult.add(map);
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("add")
	public String addUser() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
		} catch (Exception e) {
			jsonMap.put("success", false);
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
				//permissionRoleRelationService.deleteAssignByPermissionId(ids);
				//permissionService.deletePermissionById(idsArr);
			}else{
				jsonMap.put("success", false);
			}
		} catch (Exception e) {
			jsonMap.put("success", false);
			e.printStackTrace();
		}
		return jsonOut(jsonMap);
	}
	
	
	@Action("outExcel")
	public String outExcel() throws Exception{
		List<StudentInfo> stuList = studentInfoService.queryAllStudentInfo();
		//创建HSSFWorkbook对象(excel的文档对象)  
	    HSSFWorkbook wb = new HSSFWorkbook();  
	    HSSFCellStyle cellStyle = wb.createCellStyle(); 
	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中 
	    //建立新的sheet对象（excel的表单）  
	    HSSFSheet sheet=wb.createSheet("学生成绩表");  
	    //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个  
	    HSSFRow row1=sheet.createRow(0);  
	    //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个  
	    HSSFCell cell=row1.createCell(0);  
	      //设置单元格内容  
		cell.setCellValue("学生成绩表一览");  
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,10));  
		
		//在sheet里创建第二行  
		HSSFRow row2=sheet.createRow(1);
		//创建单元格并设置单元格内容  

		row2.createCell(0).setCellValue("学生编号");
		row2.createCell(1).setCellValue("学生姓名");
		row2.createCell(2).setCellValue("放疗科");
		row2.createCell(3).setCellValue("肿瘤内科");
		row2.createCell(4).setCellValue("肿瘤外科");
		row2.createCell(5).setCellValue("病理科");
		row2.createCell(6).setCellValue("影像科");
		row2.createCell(7).setCellValue("第一阶段考试");
		row2.createCell(8).setCellValue("第二阶段考试");
		row2.createCell(9).setCellValue("期中考试");
		row2.createCell(10).setCellValue("结业考试");
		int index = 2 ;
		for (StudentInfo studentInfo : stuList) {
			HSSFRow row3=sheet.createRow(index);  
			score = scoreService.getScoreById(studentInfo.getId());
			row3.createCell(0).setCellValue(studentInfo.getId());
			row3.createCell(1).setCellValue(studentInfo.getName());
			row3.createCell(2).setCellValue(score.getRadScore());
			row3.createCell(3).setCellValue(score.getMedOncScore());
			row3.createCell(4).setCellValue(score.getSurOncScore());
			row3.createCell(5).setCellValue(score.getPhthonScore());
			row3.createCell(6).setCellValue(score.getMedImaScore());
			row3.createCell(7).setCellValue(score.getStageExamFirst());
			row3.createCell(8).setCellValue(score.getStageExamSecond());
			row3.createCell(9).setCellValue(score.getMiddleExam());
			row3.createCell(10).setCellValue(score.getFinalExam());
			index = index + 1;
		}
	    //输出Excel文件  
	    OutputStream output=response.getOutputStream();  
	    response.reset();  
	    response.setHeader("Content-disposition", "attachment; filename=scoreInfo.xls");  
	    response.setContentType("application/msexcel");          
	    wb.write(output);  
	    output.close();
	    return jsonOut(output);
	}
	
}
