package com.doctortrain.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doctortrain.bean.ClinicalRotation;
import com.doctortrain.bean.ClinicalRotationConfig;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.dao.ClinicalRotationConfigDAO;
import com.doctortrain.dao.ClinicalRotationDAO;
import com.doctortrain.service.ClinicalRotationConfigService;
import com.doctortrain.service.ClinicalRotationService;
import com.doctortrain.service.DtConfigService;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.service.TeacherInfoService;
import com.doctortrain.util.DateUtil;

@Service("clinicalRotationService")
public class ClinicalRotationServiceImpl implements ClinicalRotationService {

	@Resource(name="clinicalRotationDao")
	private ClinicalRotationDAO clinicalRotationDAO;
	
	@Resource(name="dtConfigService")
	private DtConfigService dtConfigService;
	
	@Resource(name="studentInfoService")
	private StudentInfoService studentInfoService;
	
	@Resource(name="clinicalRotationConfigDao")
	private ClinicalRotationConfigDAO clinicalRotationConfigDAO;
	@Resource(name="clinicalRotationConfigService")
	private ClinicalRotationConfigService clinicalRotationConfigService;
	@Resource(name="teacherInfoService")
	private TeacherInfoService teacherInfoService; 
	
	Map<String,Object> chechHandsMap = new HashMap<String,Object>();

	public void updateCR(ClinicalRotation cr) throws Exception {
		clinicalRotationDAO.updateCR(cr);
	}

	public void deleteCRById(int id) throws Exception {
		clinicalRotationDAO.deleteCR(id);
	}
	
	public void deleteCRByStuId(String id) throws Exception {
		clinicalRotationDAO.deleteCRByStuId(id);
	}

	public ClinicalRotation getCRById(int id) throws Exception {
		return clinicalRotationDAO.getCRById(id);
	}

	public List<ClinicalRotation> getAllCR() throws Exception {
		return clinicalRotationDAO.getAllCR();
	}

	public void closeSession() throws Exception {
		clinicalRotationDAO.closeSession();
	}

	/**
	 * 添加轮转信息
	 */ 
	public void addCR(StudentInfo studentInfo,boolean add) throws Exception {
		
		// 获取规则化后的配置
	//	List<ClinicalRotationConfig> crcList = clinicalRotationConfigService.getAllCrcByRule();
		List<ClinicalRotationConfig> crcList = clinicalRotationConfigDAO.getAllCRC();
		String stulevel = studentInfo.getResidentClass();
		ClinicalRotation cr_1 = new ClinicalRotation();
		ClinicalRotation cr_2 = new ClinicalRotation();
		ClinicalRotation cr_3 = new ClinicalRotation();
		ClinicalRotation cr_4 = new ClinicalRotation();
	
		int firstYear = -1;
		if(add){
			firstYear = Integer.parseInt(studentInfo.getInTraningDate().split("-")[0]);
			cr_1.setCreateTime(DateUtil.getSystemTime());
			cr_2.setCreateTime(DateUtil.getSystemTime());
			cr_3.setCreateTime(DateUtil.getSystemTime());
			cr_4.setCreateTime(DateUtil.getSystemTime());
		}else{
			firstYear = Integer.parseInt(DateUtil.getYmd().split("-")[0]);
			cr_1.setUpdateTime(DateUtil.getSystemTime());
			cr_2.setUpdateTime(DateUtil.getSystemTime());
			cr_3.setUpdateTime(DateUtil.getSystemTime());
			cr_4.setUpdateTime(DateUtil.getSystemTime());
		}
		
		int firestYearStr_1 = 0;
		int firestYearStr_2 = 0;
		int firestYearStr_3 = 0;
		int firestYearStr_4 = 0;
		if(stulevel.equals("1")){
			firestYearStr_1 = firstYear;
			firestYearStr_2 = firstYear+1;
			firestYearStr_3 = firstYear+2;
			firestYearStr_4 = firstYear+3;
		}
		if(stulevel.equals("2")){
			firestYearStr_2 = firstYear;
			firestYearStr_3 = firstYear+1;
			firestYearStr_4 = firstYear+2;
		}
		if(stulevel.equals("3")){
			firestYearStr_3 = firstYear;
			firestYearStr_4 = firstYear+1;
		}
		
		cr_1.setYear(firestYearStr_1+"");
		cr_2.setYear(firestYearStr_2+"");
		cr_3.setYear(firestYearStr_3+"");
		cr_4.setYear(firestYearStr_4+"");
		
		// 设置对应学生id
		cr_1.setStuId(studentInfo.getId());
		cr_2.setStuId(studentInfo.getId());
		cr_3.setStuId(studentInfo.getId());
		cr_4.setStuId(studentInfo.getId());

		for (ClinicalRotationConfig clinicalRotationConfig : crcList) {
			String startTime = clinicalRotationConfig.getStartTime();
			String EndTime = clinicalRotationConfig.getEndTime();
			//开始年份
			int startYear = Integer.parseInt(startTime.split("-")[0]);
			//开始月份
			int startMonth = Integer.parseInt(startTime.split("-")[1]);
			//区间月数
			int countMonth = countMonth(startTime,EndTime);
			
			// 第一年
			if(startYear==1&&!stulevel.equals("2")&&!stulevel.equals("3")){
				for (int i = 0; i < countMonth; i++) {
					int months = startMonth + i;
					if(months>12){
						setSubject(clinicalRotationConfig,cr_2,months - 12);
					}else{
						setSubject(clinicalRotationConfig,cr_1,months);
					}
				}
			}
			
			// 第二年
			if(startYear==2&&!stulevel.equals("3")){
				for (int i = 0; i < countMonth; i++) {
					int months = startMonth + i;
					if(months>12){
						setSubject(clinicalRotationConfig,cr_3,months - 12);
					}else{
						setSubject(clinicalRotationConfig,cr_2,months);
					}
				}			
			}
			
			// 第三年
			if(startYear==3){
				for (int i = 0; i < countMonth; i++) {
					int months = startMonth + i;
					if(months>12){
						setSubject(clinicalRotationConfig,cr_4,months - 12);
					}else{
						setSubject(clinicalRotationConfig,cr_3,months);
					}
				}
			}
			
			// 第四年
			if(startYear==4){
				for (int i = 0; i < countMonth; i++) {
					int months = startMonth + i;
						setSubject(clinicalRotationConfig,cr_4,months);
				}
			}
			
		}
		//保存 1、2、3、4 年
		addCR(cr_1);
		addCR(cr_2);
		addCR(cr_3);
		addCR(cr_4);
	}
	
	/**
	 * 添加轮转信息 2.0
	 */
	public void addCRPriority(StudentInfo studentInfo,boolean add) throws Exception {
		Map<String, Object> handsMap = new HashMap<String,Object>();
		// 获取最早的开始时间
		int firstStartMonth = getFistStartMonth();
		// <优先级，List> 乱序后
		Map<String, Object> reulsktMap = clinicalRotationConfigService.getAllCrcByPriority();
		
		String stulevel = studentInfo.getResidentClass();
		
		ClinicalRotation cr_1 = new ClinicalRotation();
		ClinicalRotation cr_2 = new ClinicalRotation();
		ClinicalRotation cr_3 = new ClinicalRotation();
		ClinicalRotation cr_4 = new ClinicalRotation();
	
		int firstYear = -1;
		if(add){
			firstYear = Integer.parseInt(studentInfo.getInTraningDate().split("-")[0]);
			cr_1.setCreateTime(DateUtil.getSystemTime());
			cr_2.setCreateTime(DateUtil.getSystemTime());
			cr_3.setCreateTime(DateUtil.getSystemTime());
			cr_4.setCreateTime(DateUtil.getSystemTime());
		}else{
			firstYear = Integer.parseInt(DateUtil.getYmd().split("-")[0]);
			cr_1.setUpdateTime(DateUtil.getSystemTime());
			cr_2.setUpdateTime(DateUtil.getSystemTime());
			cr_3.setUpdateTime(DateUtil.getSystemTime());
			cr_4.setUpdateTime(DateUtil.getSystemTime());
		}
		
		int firestYearStr_1 = 0;
		int firestYearStr_2 = 0;
		int firestYearStr_3 = 0;
		int firestYearStr_4 = 0;
		if(stulevel.equals("1")){
			firestYearStr_1 = firstYear;
			firestYearStr_2 = firstYear+1;
			firestYearStr_3 = firstYear+2;
			firestYearStr_4 = firstYear+3;
		}
		if(stulevel.equals("2")){
			firestYearStr_2 = firstYear;
			firestYearStr_3 = firstYear+1;
			firestYearStr_4 = firstYear+2;
		}
		if(stulevel.equals("3")){
			firestYearStr_3 = firstYear;
			firestYearStr_4 = firstYear+1;
		}
		
		cr_1.setYear(firestYearStr_1+"");
		cr_2.setYear(firestYearStr_2+"");
		cr_3.setYear(firestYearStr_3+"");
		cr_4.setYear(firestYearStr_4+"");
		
		// 设置对应学生id
		cr_1.setStuId(studentInfo.getId());
		cr_2.setStuId(studentInfo.getId());
		cr_3.setStuId(studentInfo.getId());
		cr_4.setStuId(studentInfo.getId());
		
		
		int startMons = -1;
		int endMons = - 1;
		
		
		int index = 0;
		for (int j = 0; j < reulsktMap.keySet().size(); j++) {
			if(j!=0){
				// 优先级获取配置
				List<ClinicalRotationConfig> list = (List<ClinicalRotationConfig>) reulsktMap.get(j+"");
					
				for (int i = 0; i < list.size(); i++) {
					ClinicalRotationConfig crc = list.get(i);
					crc.getStartTime();
					crc.getEndTime();
					int theMonth = crc.getMonthCount();
					
					for (int k = 0; k < theMonth; k++) {
						// 跳过已占
						if(index >= startMons - 1 && index<endMons){
							index = index + (endMons - startMons);
						}
						if(index>=0&&index<6){
							setSubject(list.get(i), cr_1, 7+index);
						}
						if(index>=6&&index<18){
							setSubject(list.get(i), cr_2, index - 5);
						}
						if(index>=18&&index<30){
							setSubject(list.get(i), cr_3, index - 17);
						}
						if(index>=30&&index<42){
							setSubject(list.get(i), cr_4, index - 29);
						}
						++index;
					}
				}
				
				// 先处理固定时间日期的
			}else{
				List<ClinicalRotationConfig> list = (List<ClinicalRotationConfig>) reulsktMap.get(j+"");
				for (ClinicalRotationConfig clinicalRotationConfig : list) {
					String startTime = clinicalRotationConfig.getStartTime();
					String EndTime = clinicalRotationConfig.getEndTime();
					//开始年份
					int startYear = Integer.parseInt(startTime.split("-")[0]);
					//开始月份
					int startMonth = Integer.parseInt(startTime.split("-")[1]);
					//区间月数
					int countMonth = clinicalRotationConfig.getMonthCount();
					
					// 保存开始时间结束时间
					handsMap.put(startTime+"", countMonth+"");
					// 第一年
					if(startYear==1&&!stulevel.equals("2")&&!stulevel.equals("3")){
						for (int i = 0; i < countMonth; i++) {
							int months = startMonth + i;
							if(months>12){
								setSubject(clinicalRotationConfig,cr_2,months - 12);
							}else{
								setSubject(clinicalRotationConfig,cr_1,months);
							}
						}
					}
					
					// 第二年
					if(startYear==2&&!stulevel.equals("3")){
						for (int i = 0; i < countMonth; i++) {
							int months = startMonth + i;
							if(months>12){
								setSubject(clinicalRotationConfig,cr_3,months - 12);
							}else{
								setSubject(clinicalRotationConfig,cr_2,months);
							}
						}			
					}
					
					// 第三年
					if(startYear==3){
						for (int i = 0; i < countMonth; i++) {
							int months = startMonth + i;
							if(months>12){
								setSubject(clinicalRotationConfig,cr_4,months - 12);
							}else{
								setSubject(clinicalRotationConfig,cr_3,months);
							}
						}
					}
					
					// 第四年
					if(startYear==4){
						for (int i = 0; i < countMonth; i++) {
							int months = startMonth + i;
								setSubject(clinicalRotationConfig,cr_4,months);
						}
					}
				}
				// 写死校验（写死的不写入初始化）
				chechHandsMap = countHands(handsMap,firstStartMonth);
				for (String key : chechHandsMap.keySet()) {
					startMons = Integer.parseInt(key);
					endMons = (Integer)chechHandsMap.get(key);
				}
			}
		}
		
		//保存 1、2、3、4 年
		addCR(cr_1);
		addCR(cr_2);
		addCR(cr_3);
		addCR(cr_4);
	}
	
	//获取最早的开始时间
	public int getFistStartMonth() throws Exception{
		
		Set<Integer> treeSet = new TreeSet<Integer>();
		Set<Integer> treeSetRight = new TreeSet<Integer>();
		List<ClinicalRotationConfig> list = clinicalRotationConfigService.getAllCRC();
		/*// 获取最早的开始时间1.0 - start 
		String firstStartime = "";
		Map<String,Object> map = new TreeMap<String,Object>();
		for (ClinicalRotationConfig clinicalRotationConfig : list) {
			treeSet.add(clinicalRotationConfig.getStartTime());
		}
		for (String tStartTime : treeSet) {
			firstStartime = tStartTime;
			break;
		}
		firstStartime = firstStartime.split("-")[1];
		// 获取最早的开始时间 1.0- end
*/		
		
		// 获取最早的开始时间 2.0- start
		Integer left =-1;
		for (ClinicalRotationConfig clinicalRotationConfig : list) {
			treeSet.add(Integer.valueOf(clinicalRotationConfig.getStartTime().split("-")[0]));
		}
		for (Integer theLeft : treeSet) {
			left = theLeft;
			break;
		}
		for (ClinicalRotationConfig clinicalRotationConfig : list) {
			String temp = clinicalRotationConfig.getStartTime();
			if(temp.contains((left+"-"))){
				treeSetRight.add(Integer.valueOf(clinicalRotationConfig.getStartTime().split("-")[1]));
			}
		}
		Integer right = -1;
		for (Integer theRight : treeSetRight) {
			right = theRight;
			break;
		}
		
		// 获取最早的开始时间2.0 - end
		
		return Integer.valueOf(right);
	}
	
	/**
	 * 添加轮转信息 2.5
	 */
	public void addCRPriorityNew(StudentInfo studentInfo,boolean add) throws Exception {
		Map<String, Object> handsMap = new HashMap<String,Object>();
		
		// 获取最早的开始时间
		int firstStartMonth = getFistStartMonth();
		
		// <优先级，List> 乱序后
		Map<String, Object> reulsktMap = clinicalRotationConfigService.getAllCrcByPriority();
		String id = studentInfo.getTeacherID();
		String tsub;
		if(id==null||"".equals(id)){
			tsub = "";
		}else{
			tsub = teacherInfoService.getTeacherInfoById(Integer.parseInt(studentInfo.getTeacherID())).getDepartment();
			if(!"放疗头颈".equals(tsub)){
				if(!"放疗胸".equals(tsub)){
					if(!"放疗腹".equals(tsub)){
						tsub = "";
					}
				}
			}
		}
		List<ClinicalRotationConfig> list1 = (List<ClinicalRotationConfig>) reulsktMap.get(1+"");
		
		int inDate = Integer.parseInt(studentInfo.getInTraningDate().split("-")[0]);
		int nowDate = Integer.parseInt(DateUtil.getYmd().split("-")[0]);
		
		String stulevel = (nowDate - inDate + 1)+"";
		
		if(tsub!=null&&!"".equals(tsub)){
			String sub = (String)list1.get(0).getSubject();
			while(!tsub.equals(sub)){
				reulsktMap= clinicalRotationConfigService.getAllCrcByPriority();
				list1 = (List<ClinicalRotationConfig>) reulsktMap.get(1+"");
				sub = list1.get(0).getSubject();
			}
		}
		
		ClinicalRotation cr_1 = new ClinicalRotation();
		ClinicalRotation cr_2 = new ClinicalRotation();
		ClinicalRotation cr_3 = new ClinicalRotation();
		ClinicalRotation cr_4 = new ClinicalRotation();
	
		int firstYear = -1;
		if(add){
			firstYear = Integer.parseInt(studentInfo.getInTraningDate().split("-")[0]);
			cr_1.setCreateTime(DateUtil.getSystemTime());
			cr_2.setCreateTime(DateUtil.getSystemTime());
			cr_3.setCreateTime(DateUtil.getSystemTime());
			cr_4.setCreateTime(DateUtil.getSystemTime());
		}else{
			firstYear = Integer.parseInt(DateUtil.getYmd().split("-")[0]);
			cr_1.setUpdateTime(DateUtil.getSystemTime());
			cr_2.setUpdateTime(DateUtil.getSystemTime());
			cr_3.setUpdateTime(DateUtil.getSystemTime());
			cr_4.setUpdateTime(DateUtil.getSystemTime());
		}
		
		int firestYearStr_1 = 0;
		int firestYearStr_2 = 0;
		int firestYearStr_3 = 0;
		int firestYearStr_4 = 0;
		if(stulevel.equals("1")){
			firestYearStr_1 = firstYear;
			firestYearStr_2 = firstYear+1;
			firestYearStr_3 = firstYear+2;
			firestYearStr_4 = firstYear+3;
		}
		if(stulevel.equals("2")){
			firestYearStr_2 = firstYear;
			firestYearStr_3 = firstYear+1;
			firestYearStr_4 = firstYear+2;
		}
		if(stulevel.equals("3")){
			firestYearStr_3 = firstYear;
			firestYearStr_4 = firstYear+1;
		}
		
		cr_1.setYear(firestYearStr_1+"");
		cr_2.setYear(firestYearStr_2+"");
		cr_3.setYear(firestYearStr_3+"");
		cr_4.setYear(firestYearStr_4+"");
		
		// 设置对应学生id
		cr_1.setStuId(studentInfo.getId());
		cr_2.setStuId(studentInfo.getId());
		cr_3.setStuId(studentInfo.getId());
		cr_4.setStuId(studentInfo.getId());
		
		
		int startMons = -1;
		int endMons = - 1;
		
		
		int index = 0;
			List<ClinicalRotationConfig> list = (List<ClinicalRotationConfig>) reulsktMap.get(0+"");
				// 直接填写外院时间
				for (ClinicalRotationConfig clinicalRotationConfig : list) {
					String startTime = clinicalRotationConfig.getStartTime();
					String EndTime = clinicalRotationConfig.getEndTime();
					//开始年份
					int startYear = Integer.parseInt(startTime.split("-")[0]);
					//开始月份
					int startMonth = Integer.parseInt(startTime.split("-")[1]);
					//区间月数
					int countMonth = clinicalRotationConfig.getMonthCount();
					
					// 保存开始时间结束时间
					handsMap.put(startTime+"", countMonth+"");
					// 第一年
					if(startYear==1&&!stulevel.equals("2")&&!stulevel.equals("3")){
						for (int i = 0; i < countMonth; i++) {
							int months = startMonth + i;
							if(months>12){
								setSubject(clinicalRotationConfig,cr_2,months - 12);
							}else{
								setSubject(clinicalRotationConfig,cr_1,months);
							}
						}
					}
					
					// 第二年
					if(startYear==2&&!stulevel.equals("3")){
						for (int i = 0; i < countMonth; i++) {
							int months = startMonth + i;
							if(months>12){
								setSubject(clinicalRotationConfig,cr_3,months - 12);
							}else{
								setSubject(clinicalRotationConfig,cr_2,months);
							}
						}			
					}
					
					// 第三年
					if(startYear==3){
						for (int i = 0; i < countMonth; i++) {
							int months = startMonth + i;
							if(months>12){
								setSubject(clinicalRotationConfig,cr_4,months - 12);
							}else{
								setSubject(clinicalRotationConfig,cr_3,months);
							}
						}
					}
					
					// 第四年
					if(startYear==4){
						for (int i = 0; i < countMonth; i++) {
							int months = startMonth + i;
								setSubject(clinicalRotationConfig,cr_4,months);
						}
					}
				}
				// 外院 8 个月 第几-第几
				chechHandsMap = countHands(handsMap,firstStartMonth);
				for (String key : chechHandsMap.keySet()) {
					startMons = Integer.parseInt(key) - 1;
					endMons = (Integer)chechHandsMap.get(key) - 1;
				}
			
			// start ---------------------------------
			// 计算 外院 与 胸头腹的距离
			int cha = -1;
			int lcha = 33 - endMons;
			int chaStart = -1;
			int chaEnd = -1;
			/**
			 * 3外院8
			 * 4外院8
			 * 12外院8
			 */
			if(startMons/4==0){
				cha = startMons;
				chaStart = 0;
				chaEnd = chaStart + cha;
			}
			if(startMons/4==1){
				cha = startMons-4;
				chaStart = 4;
				chaEnd = chaStart + cha;
			}
			if(startMons/4==2){
				cha = startMons-8;
				chaStart = 8;
				chaEnd = chaStart + cha;
			}
			if(startMons/4>3){
				cha = startMons-12;
				chaStart = 12;
				chaEnd = chaStart + cha;
			}
			
			// end ---------------------------------
			// 头 胸 腹
			;
			for (int i = 0; i < list1.size(); i++) {
				ClinicalRotationConfig crc = list1.get(i);
				/*if(chaStart==0){
					index = index + cha;
				}*/
				

				int theMonth = crc.getMonthCount();
				
				for (int k = 0; k < theMonth; k++) {
					// 跳过已占 外院
					/*if(index >= startMons - 1 && index<endMons){
						index = index + 8;
					}*/
					// 跳过已占 外院 和小于4的格
					if(chaStart==index){
						index = index + cha + 8;
					}
					writeMonth(list1.get(i),cr_1,cr_2,cr_3,cr_4,index,firstStartMonth);
					++index;
				}
				
			}
			
			// 内 外 病理 影像
			List<ClinicalRotationConfig> list2 = (List<ClinicalRotationConfig>) reulsktMap.get(2+"");
			
			
			if(lcha<5){
					// 写入中间空的
					for (ClinicalRotationConfig clinicalRotationConfig : list2) {
						if(lcha==clinicalRotationConfig.getMonthCount()){
							for (int i = 0; i < lcha; i++) {
								writeMonth(clinicalRotationConfig,cr_1,cr_2,cr_3,cr_4,endMons++,firstStartMonth);
							}
							list2.remove(clinicalRotationConfig);
							break;
						}
					}
			}else{
				if(cha>0&&cha<5){
					// 写入中间空的
					for (ClinicalRotationConfig clinicalRotationConfig : list2) {
						if(cha==clinicalRotationConfig.getMonthCount()){
							for (int i = 0; i < cha; i++) {
								writeMonth(clinicalRotationConfig,cr_1,cr_2,cr_3,cr_4,chaStart++,firstStartMonth);
								if(chaStart>=12){
									++index;
								}
							}
							list2.remove(clinicalRotationConfig);
							break;
					}
				}
			}
				
			
			if(cha>4){
				// 4 3 2 1 1
				boolean stop = false;
				for (int i = 0; i < list2.size(); i++) {
					// 两个相加
					for (int k = 1; k < list2.size()-1; k++) {
						ClinicalRotationConfig crc1 = list2.get(i);
						ClinicalRotationConfig crc2 = list2.get(k);
						int count1 = crc1.getMonthCount();
						int count2 = crc2.getMonthCount();
						if(cha==count1+count2){
							list2.remove(crc1);
							list2.remove(crc2);
							int start = 12;
							for (int h = 0; h < crc1.getMonthCount(); h++) {
								writeMonth(crc1,cr_1,cr_2,cr_3,cr_4,start,firstStartMonth);
								start = start + 1;
								++index;
							}
							for (int g = 0; g < crc2.getMonthCount(); g++) {
								writeMonth(crc2,cr_1,cr_2,cr_3,cr_4,start,firstStartMonth);
								start = start + 1;
								++index;
							}
							stop = true;
							break;
						}
					}
					if(stop){
						break;
					}
				}
			}
			}
				
				// 优先级获取配置
				for (int i = 0; i < list2.size(); i++) {
					ClinicalRotationConfig crc = list2.get(i);
					int theMonth = crc.getMonthCount();
					for (int k = 0; k < theMonth; k++) {
						// 跳过已占 外院
						//写入月份
						if(startMons==index){
							index = index + 8;
						}
						writeMonth(list2.get(i),cr_1,cr_2,cr_3,cr_4,index,firstStartMonth);
						++index;
					}
				}
		
		//保存 1、2、3、4 年
		addCR(cr_1);
		addCR(cr_2);
		addCR(cr_3);
		addCR(cr_4);
	}
	
	
	
	/**
	 * 学生模块所用的添加轮转
	 * @param studentInfo
	 * @param crc
	 * @throws Exception
	 */
	public void addCRPriorityNew(StudentInfo studentInfo,ClinicalRotationConfig crc) throws Exception {
		Map<String, Object> handsMap = new HashMap<String,Object>();
		// 获取最早的开始时间
		int firstStartMonth = getFistStartMonth();
		// <优先级，List> 乱序后 ==> 获取所有优先级及内容
		Map<String, Object> reulsktMap = clinicalRotationConfigService.getAllCrcByPriority();
		String id = studentInfo.getTeacherID();
		String tsub;
		if(id==null||"".equals(id)){
			tsub = "";
		}else{
			tsub = teacherInfoService.getTeacherInfoById(Integer.parseInt(studentInfo.getTeacherID())).getDepartment();
			if(!"放疗头颈".equals(tsub)){
				if(!"放疗胸".equals(tsub)){
					if(!"放疗腹".equals(tsub)){
						tsub = "";
					}
				}
			}
		}
		List<ClinicalRotationConfig> list1 = (List<ClinicalRotationConfig>) reulsktMap.get(1+"");
		
		int inDate = Integer.parseInt(studentInfo.getInTraningDate().split("-")[0]);
		int nowDate = Integer.parseInt(DateUtil.getYmd().split("-")[0]);
		
		String stulevel = (nowDate - inDate + 1)+"";
		
		if(tsub!=null&&!"".equals(tsub)){
			String sub = (String)list1.get(0).getSubject();
			while(!tsub.equals(sub)){
				reulsktMap= clinicalRotationConfigService.getAllCrcByPriority();
				list1 = (List<ClinicalRotationConfig>) reulsktMap.get(1+"");
				sub = list1.get(0).getSubject();
			}
		}
		
		ClinicalRotation cr_1 = new ClinicalRotation();
		ClinicalRotation cr_2 = new ClinicalRotation();
		ClinicalRotation cr_3 = new ClinicalRotation();
		ClinicalRotation cr_4 = new ClinicalRotation();
	
		int firstYear = -1;
		firstYear = Integer.parseInt(studentInfo.getInTraningDate().split("-")[0]);
		cr_1.setCreateTime(DateUtil.getSystemTime());
		cr_2.setCreateTime(DateUtil.getSystemTime());
		cr_3.setCreateTime(DateUtil.getSystemTime());
		cr_4.setCreateTime(DateUtil.getSystemTime());
		
		int firestYearStr_1 = 0;
		int firestYearStr_2 = 0;
		int firestYearStr_3 = 0;
		int firestYearStr_4 = 0;
		if(stulevel.equals("1")){
			firestYearStr_1 = firstYear;
			firestYearStr_2 = firstYear+1;
			firestYearStr_3 = firstYear+2;
			firestYearStr_4 = firstYear+3;
		}
		if(stulevel.equals("2")){
			firestYearStr_2 = firstYear;
			firestYearStr_3 = firstYear+1;
			firestYearStr_4 = firstYear+2;
		}
		if(stulevel.equals("3")){
			firestYearStr_3 = firstYear;
			firestYearStr_4 = firstYear+1;
		}
		
		cr_1.setYear(firestYearStr_1+"");
		cr_2.setYear(firestYearStr_2+"");
		cr_3.setYear(firestYearStr_3+"");
		cr_4.setYear(firestYearStr_4+"");
		
		// 设置对应学生id
		cr_1.setStuId(studentInfo.getId());
		cr_2.setStuId(studentInfo.getId());
		cr_3.setStuId(studentInfo.getId());
		cr_4.setStuId(studentInfo.getId());
		
		
		int startMons = -1;
		int endMons = - 1;
		
		
		int index = 0;
				
					String startTime = crc.getStartTime();
					String EndTime = crc.getEndTime();
					//开始年份
					int startYear = Integer.parseInt(startTime.split("-")[0]);
					//开始月份
					int startMonth = Integer.parseInt(startTime.split("-")[1]);
					//区间月数
					int countMonth = crc.getMonthCount();
					
					// 保存开始时间结束时间
					handsMap.put(startTime+"", countMonth+"");
					// 第一年
					if(startYear==1&&!stulevel.equals("2")&&!stulevel.equals("3")){
						for (int i = 0; i < countMonth; i++) {
							int months = startMonth + i;
							if(months>12){
								setSubject(crc,cr_2,months - 12);
							}else{
								setSubject(crc,cr_1,months);
							}
						}
					}
					
					// 第二年
					if(startYear==2&&!stulevel.equals("3")){
						for (int i = 0; i < countMonth; i++) {
							int months = startMonth + i;
							if(months>12){
								setSubject(crc,cr_3,months - 12);
							}else{
								setSubject(crc,cr_2,months);
							}
						}			
					}
					
					// 第三年
					if(startYear==3){
						for (int i = 0; i < countMonth; i++) {
							int months = startMonth + i;
							if(months>12){
								setSubject(crc,cr_4,months - 12);
							}else{
								setSubject(crc,cr_3,months);
							}
						}
					}
					
					// 第四年
					if(startYear==4){
						for (int i = 0; i < countMonth; i++) {
							int months = startMonth + i;
								setSubject(crc,cr_4,months);
						}
					}
				// 外院 8 个月 第几-第几
				chechHandsMap = countHands(handsMap,firstStartMonth);
				for (String key : chechHandsMap.keySet()) {
					startMons = Integer.parseInt(key) - 1;
					endMons = (Integer)chechHandsMap.get(key) - 1;
				}
			
			// start ---------------------------------
			// 计算 外院 与 胸头腹的距离
			int cha = -1;
			int lcha = 33 - endMons;
			int chaStart = -1;
			int chaEnd = -1;
			/**
			 * 3外院8
			 * 4外院8
			 * 12外院8
			 */
			if(startMons/4==0){
				cha = startMons;
				chaStart = 0;
				chaEnd = chaStart + cha;
			}
			if(startMons/4==1){
				cha = startMons-4;
				chaStart = 4;
				chaEnd = chaStart + cha;
			}
			if(startMons/4==2){
				cha = startMons-8;
				chaStart = 8;
				chaEnd = chaStart + cha;
			}
			if(startMons/4>3){
				cha = startMons-12;
				chaStart = 12;
				chaEnd = chaStart + cha;
			}
			
			// end ---------------------------------
			// 头 胸 腹
			;
			for (int i = 0; i < list1.size(); i++) {
				ClinicalRotationConfig crc1 = list1.get(i);
				/*if(chaStart==0){
					index = index + cha;
				}*/
				

				int theMonth = crc1.getMonthCount();
				
				for (int k = 0; k < theMonth; k++) {
					// 跳过已占 外院
					/*if(index >= startMons - 1 && index<endMons){
						index = index + 8;
					}*/
					// 跳过已占 外院 和小于4的格
					if(chaStart==index){
						index = index + cha + 8;
					}
					writeMonth(list1.get(i),cr_1,cr_2,cr_3,cr_4,index,firstStartMonth);
					++index;
				}
				
			}
			
			// 内 外 病理 影像
			List<ClinicalRotationConfig> list2 = (List<ClinicalRotationConfig>) reulsktMap.get(2+"");
			
			
			if(lcha<5){
					// 写入中间空的
					for (ClinicalRotationConfig clinicalRotationConfig : list2) {
						if(lcha==clinicalRotationConfig.getMonthCount()){
							for (int i = 0; i < lcha; i++) {
								writeMonth(clinicalRotationConfig,cr_1,cr_2,cr_3,cr_4,endMons++,firstStartMonth);
							}
							list2.remove(clinicalRotationConfig);
							break;
						}
					}
			}else{
				if(cha>0&&cha<5){
					// 写入中间空的
					for (ClinicalRotationConfig clinicalRotationConfig : list2) {
						if(cha==clinicalRotationConfig.getMonthCount()){
							for (int i = 0; i < cha; i++) {
								writeMonth(clinicalRotationConfig,cr_1,cr_2,cr_3,cr_4,chaStart++,firstStartMonth);
								if(chaStart>=12){
									++index;
								}
							}
							list2.remove(clinicalRotationConfig);
							break;
					}
				}
			}
				
			
			if(cha>4){
				// 4 3 2 1 1
				boolean stop = false;
				for (int i = 0; i < list2.size(); i++) {
					// 两个相加
					for (int k = 1; k < list2.size()-1; k++) {
						ClinicalRotationConfig crc1 = list2.get(i);
						ClinicalRotationConfig crc2 = list2.get(k);
						int count1 = crc1.getMonthCount();
						int count2 = crc2.getMonthCount();
						if(cha==count1+count2){
							list2.remove(crc1);
							list2.remove(crc2);
							int start = 12;
							for (int h = 0; h < crc1.getMonthCount(); h++) {
								writeMonth(crc1,cr_1,cr_2,cr_3,cr_4,start,firstStartMonth);
								start = start + 1;
								++index;
							}
							for (int g = 0; g < crc2.getMonthCount(); g++) {
								writeMonth(crc2,cr_1,cr_2,cr_3,cr_4,start,firstStartMonth);
								start = start + 1;
								++index;
							}
							stop = true;
							break;
						}
					}
					if(stop){
						break;
					}
				}
			}
			}
				
				// 优先级获取配置
				for (int i = 0; i < list2.size(); i++) {
					ClinicalRotationConfig crc2 = list2.get(i);
					int theMonth = crc2.getMonthCount();
					for (int k = 0; k < theMonth; k++) {
						// 跳过已占 外院
						//写入月份
						if(startMons==index){
							index = index + 8;
						}
						writeMonth(list2.get(i),cr_1,cr_2,cr_3,cr_4,index,firstStartMonth);
						++index;
					}
				}
		
		//保存 1、2、3、4 年
		addCR(cr_1);
		addCR(cr_2);
		addCR(cr_3);
		addCR(cr_4);
	}
	
	/**
	 * 课时中写入科目
	 * @param crc 科室
	 * @param cr_1 第二年
	 * @param cr_2 第二年
	 * @param cr_3 第三年
	 * @param cr_4 第四年
	 * @param theMonth 第几个月
	 * @throws Exception 异常信息
	 */
	public void writeMonth(ClinicalRotationConfig crc
							,ClinicalRotation cr_1
							,ClinicalRotation cr_2
							,ClinicalRotation cr_3
							,ClinicalRotation cr_4
							,int theMonth
							,int firstStartMonth) throws Exception{
		
		// 跳过已占 外院
		if(theMonth>=0&&theMonth<(13-firstStartMonth)){
			setSubject(crc, cr_1, theMonth + firstStartMonth);
		}
		if(theMonth>=(13-firstStartMonth)&&theMonth<(25-firstStartMonth)){
			setSubject(crc, cr_2, theMonth - (12-firstStartMonth));
		}
		if(theMonth>=(25-firstStartMonth)&&theMonth<(37-firstStartMonth)){
			setSubject(crc, cr_3, theMonth - (24-firstStartMonth));
		}
		if(theMonth>=(37-firstStartMonth)&&theMonth<(49-firstStartMonth)){
			setSubject(crc, cr_4, theMonth - (36-firstStartMonth));
		}
	}
	
	/**
	 * 添加轮转信息 3.0
	 */
	public void addCRByStatus(StudentInfo studentInfo,boolean add) throws Exception {
		List<ClinicalRotationConfig> handsList = new ArrayList<ClinicalRotationConfig>();
		List<ClinicalRotationConfig> moveList = new ArrayList<ClinicalRotationConfig>();
		Map<String,Object> permitMap = new HashMap<String,Object>();
		// <优先级，List> 乱序后
		Map<String, Object> reulsktMap = clinicalRotationConfigService.getAllCrcByStatus();
		
		handsList = (List<ClinicalRotationConfig>) reulsktMap.get("writeStraight");
		moveList = (List<ClinicalRotationConfig>) reulsktMap.get("writeMove");
		permitMap = (Map<String,Object>)reulsktMap.get("writeMovePermit");
		
		String stulevel = studentInfo.getResidentClass();
		
		ClinicalRotation cr_1 = new ClinicalRotation();
		ClinicalRotation cr_2 = new ClinicalRotation();
		ClinicalRotation cr_3 = new ClinicalRotation();
		ClinicalRotation cr_4 = new ClinicalRotation();
	
		int firstYear = -1;
		if(add){
			firstYear = Integer.parseInt(studentInfo.getInTraningDate().split("-")[0]);
			cr_1.setCreateTime(DateUtil.getSystemTime());
			cr_2.setCreateTime(DateUtil.getSystemTime());
			cr_3.setCreateTime(DateUtil.getSystemTime());
			cr_4.setCreateTime(DateUtil.getSystemTime());
		}else{
			firstYear = Integer.parseInt(DateUtil.getYmd().split("-")[0]);
			cr_1.setUpdateTime(DateUtil.getSystemTime());
			cr_2.setUpdateTime(DateUtil.getSystemTime());
			cr_3.setUpdateTime(DateUtil.getSystemTime());
			cr_4.setUpdateTime(DateUtil.getSystemTime());
		}
		
		int firestYearStr_1 = 0;
		int firestYearStr_2 = 0;
		int firestYearStr_3 = 0;
		int firestYearStr_4 = 0;
		if(stulevel.equals("1")){
			firestYearStr_1 = firstYear;
			firestYearStr_2 = firstYear+1;
			firestYearStr_3 = firstYear+2;
			firestYearStr_4 = firstYear+3;
		}
		if(stulevel.equals("2")){
			firestYearStr_2 = firstYear;
			firestYearStr_3 = firstYear+1;
			firestYearStr_4 = firstYear+2;
		}
		if(stulevel.equals("3")){
			firestYearStr_3 = firstYear;
			firestYearStr_4 = firstYear+1;
		}
		
		cr_1.setYear(firestYearStr_1+"");
		cr_2.setYear(firestYearStr_2+"");
		cr_3.setYear(firestYearStr_3+"");
		cr_4.setYear(firestYearStr_4+"");
		
		// 设置对应学生id
		cr_1.setStuId(studentInfo.getId());
		cr_2.setStuId(studentInfo.getId());
		cr_3.setStuId(studentInfo.getId());
		cr_4.setStuId(studentInfo.getId());
		
		
		int startMons = -1;
		int endMons = - 1;
		
		
		
		
		// ----------------------------------------------------------------------------------- begin 
		for (ClinicalRotationConfig clinicalRotationConfig : handsList) {
			String startTime = clinicalRotationConfig.getStartTime();
			//开始年份
			int startYear = Integer.parseInt(startTime.split("-")[0]);
			//开始月份
			int startMonth = Integer.parseInt(startTime.split("-")[1]);
			//区间月数
			int countMonth = clinicalRotationConfig.getMonthCount();
			
			// 第一年
			if(startYear==1&&!stulevel.equals("2")&&!stulevel.equals("3")){
				for (int i = 0; i < countMonth; i++) {
					int months = startMonth + i;
					if(months>12){
						setSubject(clinicalRotationConfig,cr_2,months - 12);
					}else{
						setSubject(clinicalRotationConfig,cr_1,months);
					}
				}
			}
			
			// 第二年
			if(startYear==2&&!stulevel.equals("3")){
				for (int i = 0; i < countMonth; i++) {
					int months = startMonth + i;
					if(months>12){
						setSubject(clinicalRotationConfig,cr_3,months - 12);
					}else{
						setSubject(clinicalRotationConfig,cr_2,months);
					}
				}			
			}
			
			// 第三年
			if(startYear==3){
				for (int i = 0; i < countMonth; i++) {
					int months = startMonth + i;
					if(months>12){
						setSubject(clinicalRotationConfig,cr_4,months - 12);
					}else{
						setSubject(clinicalRotationConfig,cr_3,months);
					}
				}
			}
			
			// 第四年
			if(startYear==4){
				for (int i = 0; i < countMonth; i++) {
					int months = startMonth + i;
						setSubject(clinicalRotationConfig,cr_4,months);
				}
			}
		}
		
		// ----------------------------------------------------------------------------------- End 
		int index = 0;
		for (String key : permitMap.keySet()) {
			   System.out.println("key= "+ key + " and value= " + permitMap.get(key));
			   
			   int startMonth = Integer.parseInt(key);
			   int EndMonth = Integer.parseInt((String)permitMap.get(key));
			   int SECount = EndMonth - startMonth;
			  
				ClinicalRotationConfig crc = moveList.get(index);
				crc.getStartTime();
				crc.getEndTime();
				
				int theMonth = crc.getMonthCount();
				
				
				
				for (int k = 0; k < SECount; k++) {
					if(startMonth>=0&&startMonth<4){
						setSubject(crc, cr_1, 9 + startMonth);
					}
					if(startMonth>=4&&startMonth<16){
						setSubject(crc, cr_2, startMonth - 3);
					}
					if(startMonth>=16&&startMonth<28){
						setSubject(crc, cr_3, startMonth - 15);
					}
					if(startMonth>=28&&startMonth<40){
						setSubject(crc, cr_4, startMonth - 27);
					}
					++startMonth;
				}
				++index;
		}
		
		//保存 1、2、3、4 年
		addCR(cr_1);
		addCR(cr_2);
		addCR(cr_3);
		addCR(cr_4);
	}
	
	
	/**
	 * 计算开始时间结束时间
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 几个月
	 * @throws Exception
	 */
	public int countMonth(String startTime,String endTime) throws Exception{
		int startYear = Integer.parseInt(startTime.split("-")[0]);
		int startMonth = Integer.parseInt(startTime.split("-")[1]);
		int endYear = Integer.parseInt(endTime.split("-")[0]);
		int endMonth = Integer.parseInt(endTime.split("-")[1]);
		
		int Yesrs = endYear-startYear;
		if(Yesrs==0){
			int months = endMonth - startMonth;
			if(months==0){
				return 1;
			}else{
				return months + 1;
			}
		}else if(Yesrs==1){
			return 12 - startMonth + endMonth + 1;
		}
		
		return -1;
	}
	
	
	/**
	 * 给特定时间添加课程
	 * @param crc 规则配置
	 * @param cr 轮转对象
	 * @param month 要配置的月份
	 * @return 轮转对象
	 * @throws Exception
	 */
	public ClinicalRotation setSubject(ClinicalRotationConfig crc,ClinicalRotation cr,int month)throws Exception{
		if(1==month){
			cr.setJan(crc.getSubject());
		}
		if(2==month){
			cr.setFeb(crc.getSubject());
		}
		if(3==month){
			cr.setMar(crc.getSubject());
		}
		if(4==month){
			cr.setApr(crc.getSubject());
		}
		if(5==month){
			cr.setMay(crc.getSubject());
		}
		if(6==month){
			cr.setJun(crc.getSubject());
		}
		if(7==month){
			cr.setJul(crc.getSubject());
		}
		if(8==month){
			cr.setAug(crc.getSubject());
		}
		if(9==month){
			cr.setSep(crc.getSubject());
		}
		if(10==month){
			cr.setOct(crc.getSubject());
		}
		if(11==month){
			cr.setNov(crc.getSubject());
		}
		if(12==month){
			cr.setDecb(crc.getSubject());
		}
		return cr;
		
	}
	
	// 计算是不是写死的配置
	public Map<String,Object> countHands(Map<String,Object> map,int firstMonth) throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		for (String key : map.keySet()) {
			   System.out.println("key= "+ key + " and value= " + map.get(key));
			  String year = key.split("-")[0];
			  String month = key.split("-")[1];
			  //int tempJian = firstMonth - 1;
			  //int tempJia = 13 - firstMonth;
			  if("1".equals(year)){
				  int mon = Integer.parseInt(month)-(firstMonth - 1);
				  int count = Integer.parseInt((String)map.get(key)) + mon;
				  result.put(mon+"", count);
			  }
			  if("2".equals(year)){
				  int mon = Integer.parseInt(month)+(13 - firstMonth);
				  int count = Integer.parseInt((String)map.get(key)) + mon;
				  result.put(mon+"", count);
			  }
			  if("3".equals(year)){
				  int mon = Integer.parseInt(month)+(25 - firstMonth);
				  int count = Integer.parseInt((String)map.get(key)) + mon;
				  result.put(mon+"", count);
			  }
			  if("4".equals(year)){
				  int mon = Integer.parseInt(month)+(37 - firstMonth);
				  int count = Integer.parseInt((String)map.get(key)) + mon;
				  result.put(mon+"", count);
			  }
		 }
		return result;
	}
	
	// 写死配置 校验
	public ClinicalRotation setSubject(ClinicalRotationConfig crc,ClinicalRotation cr,int month,int count)throws Exception{
		
		return setSubject(crc,cr,month);
	}

	/**
	 * 获取轮转科目
	 */
	public String getCrNow(String id) throws Exception {
		
		// 一个同学所有轮转记录
		List<ClinicalRotation> crList = clinicalRotationDAO.getCrByStuId(id);
		for (ClinicalRotation clinicalRotation : crList) {
			String nowDate = DateUtil.getYmd();
			String nowYear = nowDate.split("-")[0];
			String nowMonth = nowDate.split("-")[1];
			//年份相对
			if(nowYear.equals(clinicalRotation.getYear())){
				// 通过月份获取轮转科目
				return getMonth(nowMonth,clinicalRotation); 
			}
		}
		
		return null;
	}
	
	/**
	 * 获取轮转科目
	 * @param month 月份
	 * @param cr 轮转对象
	 * @return
	 * @throws Exception
	 */
	public String getMonth(String month,ClinicalRotation cr) throws Exception{
		if("01".equals(month)||"1".equals(month)){
			return cr.getJan();
		}
		if("02".equals(month)||"2".equals(month)){
			return cr.getFeb();
		}
		if("03".equals(month)||"3".equals(month)){
			return cr.getMar();
		}
		if("04".equals(month)||"4".equals(month)){
			return cr.getApr();
		}
		if("05".equals(month)||"5".equals(month)){
			return cr.getMay();
		}
		if("06".equals(month)||"6".equals(month)){
			return cr.getJun();
		}
		if("07".equals(month)||"7".equals(month)){
			return cr.getJul();
		}
		if("08".equals(month)||"8".equals(month)){
			return cr.getAug();
		}
		if("09".equals(month)||"9".equals(month)){
			return cr.getSep();
		}
		if("10".equals(month)){
			return cr.getOct();
		}
		if("11".equals(month)){
			return cr.getNov();
		}
		if("12".equals(month)){
			return cr.getDecb();
		}
		return null;
	}

	public List<ClinicalRotation> getCrByStuId(String stuId) throws Exception {
		return clinicalRotationDAO.getCrByStuId(stuId);
	};
	

	public void addCR(ClinicalRotation cr) throws Exception {
		clinicalRotationDAO.addCR(cr);
	}

	public Map<String,Object> getNowMonthSub(List<ClinicalRotation> crList)
			throws Exception {
		int  theIndex = 0;
		Map<String,Object> resultMap = new HashMap<String, Object>();
		List<String> subList = new ArrayList<String>();
		List<ClinicalRotation> theCrList = new ArrayList<ClinicalRotation>();
		String[] dateStr = DateUtil.getYmd().split("-");
		String year = dateStr[0];
		String month = dateStr[1];
		if(month.contains("0")){
			month = month.substring(1,2);
		}
		int monthInt = Integer.parseInt(month);
		// 1. 第几个月 共几个月
		
		for (ClinicalRotation cr : crList) {
			String subject = getMonth(month,cr);
			String stuid = cr.getStuId();
			List<ClinicalRotation> list = clinicalRotationDAO.getCrByStuId(stuid);
			
			// 所有的课
			List<String> strlist = countMonth(list);
			
			// 判断是不是最后一个月
			int index=0;
			int save = 0;;
			int index2 = 0;
			for (int i = 0; i < strlist.size(); i++) {
				++index2;
				if(subject==null){
					continue;
				}
				if(subject.equals(strlist.get(i))){
					++index;
					if(i<4){
						if(monthInt==index2 + 8){
							save = index;
						}
					}
					if(i>=4&&i<16){
						if(monthInt==index2 - 4){
							save = index;
						}
					}
					if(i>=16&&i<28){
						if(monthInt==index2 - 16){
							save = index;
						}
					}
					if(i>=28&&i<34){
						if(monthInt==index2 - 28){
							save = index;
						}
					}
				}
			}
			String sub = getMonth(month,cr);
			if(sub==null){
				continue;
			}
			// 保存名单，保存学号
			if(save == index){
				if(theIndex>0){
					if(!subList.contains(sub)){
						subList.add(sub);
						++theIndex;
					}
				}else{
					subList.add(sub);
					++theIndex;
				}
				theCrList.add(cr);
			}
		}
		
		resultMap.put("subList", subList);
		resultMap.put("crList", theCrList);
		return resultMap;
	}
	
	public List<String> countMonth(List<ClinicalRotation> list) throws Exception{
		 List<String> arrayList = new ArrayList<String>();
		 
		for (ClinicalRotation clinicalRotation : list) {
			if(clinicalRotation.getJan()!=null&&!"".equals(clinicalRotation.getJan())){
				arrayList.add(clinicalRotation.getJan());
			}
			if(clinicalRotation.getFeb()!=null&&!"".equals(clinicalRotation.getFeb())){
				arrayList.add(clinicalRotation.getFeb());
			}
			if(clinicalRotation.getMar()!=null&&!"".equals(clinicalRotation.getMar())){
				arrayList.add(clinicalRotation.getMar());
			}
			if(clinicalRotation.getApr()!=null&&!"".equals(clinicalRotation.getApr())){
				arrayList.add(clinicalRotation.getApr());
			}
			if(clinicalRotation.getMay()!=null&&!"".equals(clinicalRotation.getMay())){
				arrayList.add(clinicalRotation.getMay());
			}
			if(clinicalRotation.getJun()!=null&&!"".equals(clinicalRotation.getJun())){
				arrayList.add(clinicalRotation.getJun());
			}
			if(clinicalRotation.getJul()!=null&&!"".equals(clinicalRotation.getJul())){
				arrayList.add(clinicalRotation.getJul());
			}
			if(clinicalRotation.getAug()!=null&&!"".equals(clinicalRotation.getAug())){
				arrayList.add(clinicalRotation.getAug());
			}
			if(clinicalRotation.getSep()!=null&&!"".equals(clinicalRotation.getSep())){
				arrayList.add(clinicalRotation.getSep());
			}
			if(clinicalRotation.getOct()!=null&&!"".equals(clinicalRotation.getOct())){
				arrayList.add(clinicalRotation.getOct());
			}
			if(clinicalRotation.getNov()!=null&&!"".equals(clinicalRotation.getNov())){
				arrayList.add(clinicalRotation.getNov());
			}
			if(clinicalRotation.getDecb()!=null&&!"".equals(clinicalRotation.getDecb())){
				arrayList.add(clinicalRotation.getDecb());
			}
		}
		return arrayList;
	}

	public Map<String, List<String>> getExamList(List<ClinicalRotation> crList,
			List<String> subList) throws Exception {
		
		clinicalRotationDAO.getCrByStuId("");
		
		
		String month = DateUtil.getYmd().split("-")[1];
		Map<String, List<String>> resultMap = new HashMap<String, List<String>>();
		
		for (int i = 0; i < subList.size(); i++) {
			List<String> list = new ArrayList<String>();
			for (ClinicalRotation clinicalRotation : crList) {
				if(getMonth(month,clinicalRotation).equals(subList.get(i))){
					StudentInfo studentinfo = studentInfoService.getStudentInfo(clinicalRotation.getStuId());
					list.add(studentinfo.getId()+"-"+studentinfo.getName());
				}
			}
			resultMap.put(subList.get(i)+"-"+list.size(), list);
		}
		
		return resultMap;
	}
	
	
	public String getMonth(String month) throws Exception{
		if("01".equals(month)||"1".equals(month)){
			return "Jan";
		}
		if("02".equals(month)||"2".equals(month)){
			return "Feb";
		}
		if("03".equals(month)||"3".equals(month)){
			return "Mar";
		}
		if("04".equals(month)||"4".equals(month)){
			return "Apr";
		}
		if("05".equals(month)||"5".equals(month)){
			return "May";
		}
		if("06".equals(month)||"6".equals(month)){
			return "Jun";
		}
		if("07".equals(month)||"7".equals(month)){
			return "Jul";
		}
		if("08".equals(month)||"8".equals(month)){
			return "Aug";
		}
		if("09".equals(month)||"9".equals(month)){
			return "Oct";
		}
		if("10".equals(month)){
			return "Nov";
		}
		if("11".equals(month)){
			return "Decb";
		}
		if("12".equals(month)){
			return "Jan";
		}
		return "";
	}


	public List<ClinicalRotation> getYearList(String year) throws Exception {
		return clinicalRotationDAO.getByYear(year);
	}



}
