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

import com.doctortrain.bean.ClinicalRotation;
import com.doctortrain.bean.ClinicalRotationConfig;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.service.ClinicalRotationConfigService;
import com.doctortrain.service.ClinicalRotationService;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.util.DateUtil;


@Controller("ClinicalRotationAction")
@Scope("prototype")
@Namespace("/clinicalRotation")
public class ClinicalRotationAction extends BaseAction {

	private String ids;
	private String rcId;
	private String theId;
	private String stuId;
	private ClinicalRotation clinicalRotation;
	private StudentInfo studentInfo;
	private ClinicalRotationConfig clinicalRotationConfig;
	
	List<ClinicalRotationConfig> crcList ;
	
	@Resource(name="clinicalRotationConfigService")
	public ClinicalRotationConfigService clinicalRotationConfigService;
	
	@Resource(name="clinicalRotationService")
	public ClinicalRotationService clinicalRotationService;
	
	@Resource(name="studentInfoService")
	public StudentInfoService studentInfoService;
	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public List<ClinicalRotationConfig> getCrcList() {
		return crcList;
	}

	public void setCrcList(List<ClinicalRotationConfig> crcList) {
		this.crcList = crcList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
                                                                                                                                                 
	public String getTheId() {
		return theId;
	}

	public void setTheId(String theId) {
		this.theId = theId;
	}

	public String getRcId() {
		return rcId;
	}

	public void setRcId(String rcId) {
		this.rcId = rcId;
	}

	public ClinicalRotation getClinicalRotation() {
		return clinicalRotation;
	}
	
	public void setClinicalRotation(ClinicalRotation clinicalRotation) {
		this.clinicalRotation = clinicalRotation;
	}
	
	public ClinicalRotationConfig getClinicalRotationConfig() {
		return clinicalRotationConfig;
	}

	public void setClinicalRotationConfig(
			ClinicalRotationConfig clinicalRotationConfig) {
		this.clinicalRotationConfig = clinicalRotationConfig;
	}

	@Action(value="index",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/cr_info.jsp")})
	public String index() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toAdd",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/cr_add.jsp")})
	public String stuAdd() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toEdit",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/cr_edit.jsp")})
	public String toEdit() throws Exception{
		crcList = clinicalRotationConfigService.getAllCRC();
		clinicalRotation = clinicalRotationService.getCRById(Integer.valueOf(theId));
		studentInfo = studentInfoService.getStudentInfo(clinicalRotation.getStuId());
		return SUCCESS;
	}
	
	@Action(value="toConfig",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/crc_info.jsp")})
	public String toConfig() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toConfigAdd",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/crc_add.jsp")})
	public String toCrcAdd() throws Exception{
		return SUCCESS;
	}
	
	@Action(value="toConfigEdit",interceptorRefs={@InterceptorRef("dtStack")},results={@Result(location="/WEB-INF/jsp/crc_edit.jsp")})
	public String toCrcEdit() throws Exception{
		clinicalRotationConfig =clinicalRotationConfigService.getCRCById(clinicalRotationConfig.getId()); 
		return SUCCESS;
	}
	@Action("getConfigAll")
	public String getConfigAll() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		List<ClinicalRotationConfig> crList = new ArrayList<ClinicalRotationConfig>();
		jsonMap.put("success", true);
		try {
			crList = clinicalRotationConfigService.getAllCRC();
			for (ClinicalRotationConfig cr : crList) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", cr.getId());
				map.put("subject", cr.getSubject());
				String[] start = cr.getStartTime().split("-");
				String[] end = cr.getEndTime().split("-");
				map.put("startTime", start[0]+"年"+start[1]+"月");
				map.put("endTime", end[0]+"年"+end[1]+"月");
				map.put("monthCount", cr.getMonthCount());
				map.put("remark", cr.getRemark());
				map.put("priority", cr.getPriority());
				if(cr.getPriority()==0){
					map.put("priority", "手动填写");
				}
				if(cr.getPriority()==1){
					map.put("priority", "自动平衡1");
				}
				if(cr.getPriority()==2){
					map.put("priority", "自动平衡2");
				}
				listResult.add(map);
			}
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	@Action("getAll")
	public String getAll() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		List<ClinicalRotation> crList = new ArrayList<ClinicalRotation>();
		jsonMap.put("success", true);
		try {
			crList = clinicalRotationService.getAllCR();
			for (ClinicalRotation cr : crList) {
				StudentInfo stu = studentInfoService.getStudentInfo(cr.getStuId());
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", cr.getID());
				map.put("stuid", cr.getStuId());
				map.put("name", stu.getName());
				map.put("year", cr.getYear());
				map.put("jan", cr.getJan());
				map.put("feb", cr.getFeb());
				map.put("mar", cr.getMar());
				map.put("apr", cr.getApr());
				map.put("may", cr.getMay());
				map.put("jun", cr.getJun());
				map.put("jul", cr.getJul());
				map.put("aug", cr.getAug());
				map.put("sep", cr.getSep());
				map.put("oct", cr.getOct());
				map.put("nov", cr.getNov());
				map.put("dec", cr.getDecb());
				map.put("remark", cr.getRemark());
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
		jsonMap.put("success", true);
		String id = (String)session.getAttribute("username");
		try {
			List<ClinicalRotation> list = clinicalRotationService.getCrByStuId(id);
			StudentInfo stu = studentInfoService.getStudentInfo(id);
			for (ClinicalRotation cr : list) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", cr.getID());
				map.put("stuid", cr.getStuId());
				map.put("name", stu.getName());
				map.put("year", cr.getYear());
				map.put("jan", cr.getJan());
				map.put("feb", cr.getFeb());
				map.put("mar", cr.getMar());
				map.put("apr", cr.getApr());
				map.put("may", cr.getMay());
				map.put("jun", cr.getJun());
				map.put("jul", cr.getJul());
				map.put("aug", cr.getAug());
				map.put("sep", cr.getSep());
				map.put("oct", cr.getOct());
				map.put("nov", cr.getNov());
				map.put("dec", cr.getDecb());
				map.put("remark", cr.getRemark());
				listResult.add(map);
			}
			jsonMap.put("rows", listResult);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	/**
	 * 添加规则
	 * @throws Exception
	 */
	@Action("doAddPriority")
	public String doAddRule() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			String[] idArr = ids.split("-");
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	/**
	 * 删除规则
	 * @throws Exception
	 */
	@Action("doDelRule")
	public String doDelRule() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		try {
			String[] idArr = ids.split("-");
//			boolean flag = clinicalRotationConfigService.deleteRule(idArr);
			boolean flag = false;
			jsonMap.put("success", flag);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("doConfigAdd")
	public String doAdd() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			int monthCount = clinicalRotationService.countMonth(clinicalRotationConfig.getStartTime(), clinicalRotationConfig.getEndTime());
			clinicalRotationConfig.setCreateTime(DateUtil.getSystemTime());
			clinicalRotationConfig.setMonthCount(monthCount);
			clinicalRotationConfigService.addCRC(clinicalRotationConfig);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("doConfigEdit")
	public String doEdit() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			int monthCount = clinicalRotationService.countMonth(clinicalRotationConfig.getStartTime(), clinicalRotationConfig.getEndTime());
			clinicalRotationConfig.setUpdateTime(DateUtil.getSystemTime());
			clinicalRotationConfig.setMonthCount(monthCount);
			clinicalRotationConfigService.updateCRC(clinicalRotationConfig);
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("update")
	public String dorcEdit() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		clinicalRotation.setUpdateTime(DateUtil.getSystemTime());
		clinicalRotationService.updateCR(clinicalRotation);
		jsonMap.put("success", true);
		try {
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	@Action("doConfigDel")
	public String doDel() throws Exception{
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		jsonMap.put("success", true);
		try {
			String [] idArr = ids.split("-");
			for (int i = 0; i < idArr.length; i++) {
				clinicalRotationConfigService.deleteCRCById(Integer.parseInt(idArr[i]));
			}
		} catch (Exception e) {
			jsonMap.put("success", false);
		}
		return jsonOut(jsonMap);
	}
	
	
	@Action("outExcel")
	public String outExcel() throws Exception{

		//创建HSSFWorkbook对象(excel的文档对象)  
	    HSSFWorkbook wb = new HSSFWorkbook();  
	    HSSFCellStyle cellStyle = wb.createCellStyle(); 
	    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中 
	    //建立新的sheet对象（excel的表单）  
	    HSSFSheet sheet=wb.createSheet("轮转信息表");  
	    //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个  
	    HSSFRow row1=sheet.createRow(0);  
	    //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个  
	    HSSFCell cell=row1.createCell(0);  
	      //设置单元格内容  
		cell.setCellValue("轮转信息表一览");  
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,10));  
		
		//在sheet里创建第二行  
		HSSFRow row2=sheet.createRow(1);
		//创建单元格并设置单元格内容  
		
		row2.createCell(0).setCellValue("编号");
		row2.createCell(1).setCellValue("姓名");
		row2.createCell(2).setCellValue("年份");
		row2.createCell(3).setCellValue("九月");
		row2.createCell(4).setCellValue("十月");
		row2.createCell(5).setCellValue("十一月");
		row2.createCell(6).setCellValue("十二月");
		row2.createCell(7).setCellValue("年份");
		row2.createCell(8).setCellValue("一月");
		row2.createCell(9).setCellValue("二月");
		row2.createCell(10).setCellValue("三月");
		row2.createCell(11).setCellValue("四月");
		row2.createCell(12).setCellValue("五月");
		row2.createCell(13).setCellValue("六月");
		row2.createCell(14).setCellValue("七月");
		row2.createCell(15).setCellValue("八月");
		row2.createCell(16).setCellValue("九月");
		row2.createCell(17).setCellValue("十月");
		row2.createCell(18).setCellValue("十一月");
		row2.createCell(19).setCellValue("十二月");
		row2.createCell(20).setCellValue("年份");
		row2.createCell(21).setCellValue("一月");
		row2.createCell(22).setCellValue("二月");
		row2.createCell(23).setCellValue("三月");
		row2.createCell(24).setCellValue("四月");
		row2.createCell(25).setCellValue("五月");
		row2.createCell(26).setCellValue("六月");
		row2.createCell(27).setCellValue("七月");
		row2.createCell(28).setCellValue("八月");
		row2.createCell(29).setCellValue("九月");
		row2.createCell(30).setCellValue("十月");
		row2.createCell(31).setCellValue("十一月");
		row2.createCell(32).setCellValue("十二月");
		row2.createCell(33).setCellValue("年份");
		row2.createCell(34).setCellValue("一月");
		row2.createCell(35).setCellValue("二月");
		row2.createCell(36).setCellValue("三月");
		row2.createCell(37).setCellValue("四月");
		row2.createCell(38).setCellValue("五月");
		
		List<ClinicalRotation> crList = clinicalRotationService.getAllCR();
		int index = 2 ;
		int index4 = 0;
		HSSFRow row3=sheet.createRow(index);  
		for (ClinicalRotation clinicalRotation : crList) {
			
			if(index4==0){
				row3.createCell(0).setCellValue(clinicalRotation.getStuId());
				StudentInfo stuInfo = studentInfoService.getStudentInfo(clinicalRotation.getStuId());
				row3.createCell(1).setCellValue(stuInfo.getName());
				row3.createCell(2).setCellValue(clinicalRotation.getYear());
				row3.createCell(3).setCellValue(clinicalRotation.getSep());
				row3.createCell(4).setCellValue(clinicalRotation.getOct());
				row3.createCell(5).setCellValue(clinicalRotation.getNov());
				row3.createCell(6).setCellValue(clinicalRotation.getDecb());
				++index4;
				continue;
			}
			if(index4==1){
				row3.createCell(7).setCellValue(clinicalRotation.getYear());
				row3.createCell(8).setCellValue(clinicalRotation.getJan());
				row3.createCell(9).setCellValue(clinicalRotation.getFeb());
				row3.createCell(10).setCellValue(clinicalRotation.getMar());
				row3.createCell(11).setCellValue(clinicalRotation.getApr());
				row3.createCell(12).setCellValue(clinicalRotation.getMay());
				row3.createCell(13).setCellValue(clinicalRotation.getJun());
				row3.createCell(14).setCellValue(clinicalRotation.getJul());
				row3.createCell(15).setCellValue(clinicalRotation.getAug());
				row3.createCell(16).setCellValue(clinicalRotation.getSep());
				row3.createCell(17).setCellValue(clinicalRotation.getOct());
				row3.createCell(18).setCellValue(clinicalRotation.getNov());
				row3.createCell(19).setCellValue(clinicalRotation.getDecb());
				++index4;
				continue;
			}
			if(index4==2){
				row3.createCell(20).setCellValue(clinicalRotation.getYear());
				row3.createCell(21).setCellValue(clinicalRotation.getJan());
				row3.createCell(22).setCellValue(clinicalRotation.getFeb());
				row3.createCell(23).setCellValue(clinicalRotation.getMar());
				row3.createCell(24).setCellValue(clinicalRotation.getApr());
				row3.createCell(25).setCellValue(clinicalRotation.getMay());
				row3.createCell(26).setCellValue(clinicalRotation.getJun());
				row3.createCell(27).setCellValue(clinicalRotation.getJul());
				row3.createCell(28).setCellValue(clinicalRotation.getAug());
				row3.createCell(29).setCellValue(clinicalRotation.getSep());
				row3.createCell(30).setCellValue(clinicalRotation.getOct());
				row3.createCell(31).setCellValue(clinicalRotation.getNov());
				row3.createCell(32).setCellValue(clinicalRotation.getDecb());
				++index4;
				continue;
			}
			if(index4==3){
				row3.createCell(33).setCellValue(clinicalRotation.getYear());
				row3.createCell(34).setCellValue(clinicalRotation.getJan());
				row3.createCell(35).setCellValue(clinicalRotation.getFeb());
				row3.createCell(36).setCellValue(clinicalRotation.getMar());
				row3.createCell(37).setCellValue(clinicalRotation.getApr());
				row3.createCell(38).setCellValue(clinicalRotation.getMay());
				++index4;
				index = index + 1; 
			}
			if(index4==4){
				row3 = sheet.createRow(index);
				index4 = 0;
			}
			
		}
	    //输出Excel文件  
	    OutputStream output=response.getOutputStream();  
	    response.reset();  
	    response.setHeader("Content-disposition", "attachment; filename=ClinicalRotation.xls");  
	    response.setContentType("application/msexcel");          
	    wb.write(output);  
	    output.close();
	    return jsonOut(output);
	}
	
	@Action("delCr")
	public String delCr() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		try {
			String[] idArr = ids.split("-");
			for (int i = 0; i < idArr.length; i++) {
				clinicalRotationService.deleteCRById(Integer.parseInt(idArr[i]));
			}
		} catch (Exception e) {
			map.put("success", false);
		}
		return jsonOut(map);
	}
	
}
