package com.doctortrain.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doctortrain.bean.ClinicalRotation;
import com.doctortrain.bean.ClinicalRotationConfig;
import com.doctortrain.bean.DtConfig;
import com.doctortrain.bean.StudentInfo;
import com.doctortrain.dao.ClinicalRotationConfigDAO;
import com.doctortrain.dao.ClinicalRotationDAO;
import com.doctortrain.service.ClinicalRotationConfigService;
import com.doctortrain.service.ClinicalRotationService;
import com.doctortrain.service.DtConfigService;
import com.doctortrain.service.StudentInfoService;
import com.doctortrain.util.CalculateUtil;
import com.doctortrain.util.DateUtil;
import com.doctortrain.util.PropertiesUtil;

@Service("clinicalRotationConfigService")
public class ClinicalRotationConfigServiceImpl implements ClinicalRotationConfigService {

	@Resource(name="clinicalRotationConfigDao")
	ClinicalRotationConfigDAO clinicalRotationConfigDAO;
	
	@Resource(name="dtConfigService")
	DtConfigService dtConfigService;
	
	@Resource(name="studentInfoService")
	public StudentInfoService studentInfoService;
	
	/**
	 * DT配置， 是否进行更新
	 */
	private boolean updateFlag = true;
	
	public void addCRC(ClinicalRotationConfig cr) throws Exception {
		clinicalRotationConfigDAO.addCRC(cr);
	}

	public void updateCRC(ClinicalRotationConfig cr) throws Exception {
		clinicalRotationConfigDAO.updateCRC(cr);
	}

	public void deleteCRCById(int id) throws Exception {
		clinicalRotationConfigDAO.deleteCRC(id);
	}

	public ClinicalRotationConfig getCRCById(int id) throws Exception {
		return clinicalRotationConfigDAO.getCRCById(id);
	}

	public List<ClinicalRotationConfig> getAllCRC() throws Exception {
		return clinicalRotationConfigDAO.getAllCRC();
	}
	
	/**
	 * 优先级
	 * 1.手动 外院8个月 - 手动
	 * 2.头颈 胸 腹 4个月
	 * 3.内 外 物理 影像
	 * 
	 * 顺序
	 * 1.头颈 胸 腹
	 * 2.内 外 物理 影像 
	 * 0  外院（随机）
	 * 
	 * ---------------------
	 * 
	 * 
	 */
	public Map<String,Object> getAllCrcByPriority() throws Exception{
		// 结果
		Map<String,Object> result = new HashMap<String, Object>();
		int count = 0;
		// 获取所有rule文件
		//List<Integer> intList = clinicalRotationConfigDAO.getAllRuleRum();
		
		// 获取所有 优先级 去重复 - 0,1,2,3
		List<Integer>  priorityList = clinicalRotationConfigDAO.getAllPriority();
		for (int i = 0; i < priorityList.size(); i++) {
			List<ClinicalRotationConfig> resultList = new ArrayList<ClinicalRotationConfig>();
			int priority = priorityList.get(i);
			// 获取每个优先级对应数组
			List<ClinicalRotationConfig> crcList = clinicalRotationConfigDAO.getCRCByPriority(priorityList.get(i));
			int theSize = crcList.size() - 1;
			if(priority!=0){
				
				// 获取乱序指针
				DtConfig dt = getRuleIndex(priority);
				int piy = Integer.parseInt(dt.getValue());
				
				for (int j = 0; j <= theSize; j++) {
					// 进行乱序处理
					resultList.add(crcList.get(piy));
					piy = piy + 1 > theSize ? piy - theSize : piy + 1;
				}
				++piy;
				if(piy>theSize){
					piy = 0;
				}
				dt.setValue(piy+"");
				saveRuleIndex(dt);
			}else{
				for (int j = 0; j <= theSize; j++) {
					resultList.add(crcList.get(j));
				}
			}
			result.put(i+"", resultList);
		}
		return result;
	}
	
	/**
	 * 排课
	 */
	public Map<String,Object> getAllCrcByStatus() throws Exception{
		// 结果
		Map<String,Object> result = new HashMap<String, Object>();
		
		//1.通过状态获取所有 
		
		
		
		// 0.手动固定 
		List<ClinicalRotationConfig> handsList = clinicalRotationConfigDAO.getAllByStutas("0");
		result.put("0", handsList);
		
		// 处理获取已用月份 从第几月到第几月已占用 
		Map<String,Object> countHandsUsedMap = new HashMap<String,Object>();
		for (ClinicalRotationConfig clinicalRotationConfig : handsList) {
			// 开始时间
			String startTime = clinicalRotationConfig.getStartTime();
			// 开始到结束 几个月
			int countMonth = CalculateUtil.countMonth(startTime, clinicalRotationConfig.getEndTime());
			// 开始到结束 是从第几月到第几月
			countHandsUsedMap.put(startTime, countMonth);
		}
		// 已用月份集合
		countHandsUsedMap = countUsedMonth(countHandsUsedMap);
		
		// 1.时间轮转  ------------------------------------------------------------------------------------------- start 
		
		// 获取已用月份（第几月到第几月已用）
		Map<String,Object> countTimeUsedMap = new HashMap<String,Object>();
		List<ClinicalRotationConfig> timeList = clinicalRotationConfigDAO.getAllByStutas("1");
		List<ClinicalRotationConfig> timeListChanged = new ArrayList<ClinicalRotationConfig>();
		for (ClinicalRotationConfig clinicalRotationConfig : timeList) {
			String startTime = clinicalRotationConfig.getStartTime();
			String endTime = clinicalRotationConfig.getEndTime();
			int countMonth = CalculateUtil.countMonth(startTime, endTime);
			countTimeUsedMap.put(startTime, countMonth);
		}
		countTimeUsedMap = countUsedMonth(countTimeUsedMap);
		
		// 平均分配
		int index = 0;
		String [] subject = new String[timeList.size()];
		String [] startTime = new String[timeList.size()];
		String [] endTime = new String[timeList.size()];
		// 开始进行规则化
		for (int i = 0; i < timeList.size(); i++) {
			ClinicalRotationConfig theCrc = timeList.get(i);
			subject[i] = theCrc.getSubject();
			startTime[i] = theCrc.getStartTime();
			endTime[i] = theCrc.getEndTime();
		}
		DtConfig dt = getRuleIndex("1");
		index = Integer.parseInt(dt.getValue());
		int theSize = endTime.length - 1;
		if(index > theSize){
			index = 0;
		}
		
		
		for (int j = 0 ; j <= theSize; j++) {
			ClinicalRotationConfig crci = new ClinicalRotationConfig();
			
			int changeJ = j + index > theSize ? j + index - theSize -1  : j + index;
			crci.setSubject(subject[j]);
			crci.setStartTime(startTime[changeJ]);
			crci.setEndTime(endTime[changeJ]);
			
			timeListChanged.add(crci);
		}
		// 保存指针
		index = index + 1;
		dt.setValue(index+"");
		saveRuleIndex(dt);
		
		//------------------------------------------------------------------------------------------------- end
		
		
		// 判断 手动占了后，那些还可以占
		// 2.位移轮转 ----------------------------------------------------------------------------------------- start
		List<ClinicalRotationConfig> moveList = clinicalRotationConfigDAO.getAllByStutas("2");
		List<ClinicalRotationConfig> moveListChanged = new ArrayList<ClinicalRotationConfig>();
		int moveListSize = moveList.size() - 1;
		// 获取乱序指针
		DtConfig dt2 = getRuleIndex("2");
		int piy = Integer.parseInt(dt2.getValue());
		
		for (int j = 0; j <= moveListSize; j++) {
			// 进行乱序处理
			moveListChanged.add(moveList.get(piy));
			piy = piy + 1 > moveListSize ? piy - moveListSize : piy + 1;
		}
		++piy;
		if(piy>moveListSize){
			piy = 0;
		}
		dt2.setValue(piy+"");
		saveRuleIndex(dt2);
		
		
		Map<String,Object> countMoveUsedMap = new HashMap<String,Object>();
		for (ClinicalRotationConfig clinicalRotationConfig : moveListChanged) {
			String startMoveTime = clinicalRotationConfig.getStartTime();
			String endMoveTime = clinicalRotationConfig.getEndTime();
			int countMonth = CalculateUtil.countMonth(startMoveTime, endMoveTime);
			countMoveUsedMap.put(startMoveTime, countMonth);
		}
		countMoveUsedMap = countUsedMonth(countMoveUsedMap);
		
		
		// 合并两个集合 -------------------------------------------------------------------------- end
		countHandsUsedMap.putAll(countTimeUsedMap);
		
		Map<String,Object> notUsedMap = new HashMap<String,Object>();
		notUsedMap = notUsed(countHandsUsedMap);
		
		handsList.addAll(timeListChanged);
		
		//List<ClinicalRotationConfig> moveFinishList = notUsedToUse(moveListChanged,notUsedMap);
		
		
		result.put("writeStraight", handsList);
		result.put("writeMove", moveListChanged);
		result.put("writeMovePermit", notUsedMap);
		
		return result;
	}
	
	
	private List<ClinicalRotationConfig> notUsedToUse(
			List<ClinicalRotationConfig> moveListChanged,
			Map<String, Object> notUsedMap) {
		List<ClinicalRotationConfig> resultList = new ArrayList<ClinicalRotationConfig>();
		List<ClinicalRotationConfig> unList = new ArrayList<ClinicalRotationConfig>();
		Map<String, Object> map = new HashMap<String,Object>();
		for (String key : notUsedMap.keySet()) {
			int startMonths = Integer.parseInt(key);
			int endMonths = Integer.parseInt((String)notUsedMap.get(key));
			int count = endMonths - startMonths;
			for (int i = 0; i < moveListChanged.size(); i++) {
				ClinicalRotationConfig crc = moveListChanged.get(i);
				if(crc.getMonthCount()<=count){
					resultList.add(crc);
					++startMonths;
				}else{
					map.put(startMonths+"", endMonths);
					unList.add(crc);
					continue;
				}
			}
		}
		
		if(map.size()>0){
			for (String key : notUsedMap.keySet()) {
				int startMonths = Integer.parseInt(key);
				int endMonths = Integer.parseInt((String)notUsedMap.get(key));
				int count = endMonths - startMonths;
				for (int i = 0; i < moveListChanged.size(); i++) {
					ClinicalRotationConfig crc = moveListChanged.get(i);
					if(crc.getMonthCount()<=count){
						resultList.add(crc);
						++startMonths;
					}else{
						map.put(startMonths+"", endMonths);
						unList.add(crc);
						continue;
					}
				}
			}
		}
			
		
		return null;
	}

	// 从配置表获取规则指针
	// 配置 对象
	public DtConfig getRuleIndex(int ruleRum) throws Exception{
		DtConfig dt = dtConfigService.getByName(ruleRum+"");
		int index = 0;
		if(dt == null){
			dt = new DtConfig();
			dt.setName(ruleRum+"");
			dt.setValue(index+"");
			updateFlag = false;
			return dt;
		}else{
			return dt;
		}
		
	}
	
	public DtConfig getRuleIndex(String ruleRum) throws Exception{
		DtConfig dt = dtConfigService.getByName(ruleRum);
		int index = 0;
		if(dt == null){
			dt = new DtConfig();
			dt.setName(ruleRum);
			dt.setValue(index+"");
			updateFlag = false;
			return dt;
		}else{
			return dt;
		}
		
	}
	
	// 保存规则指针置规则表
	public void saveRuleIndex(DtConfig dt) throws Exception{
		if(updateFlag){
			dtConfigService.updateDt(dt);
		}else{
			dtConfigService.addDt(dt);
		}
	}
	
	/**
	 * 指针指向思考
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public int indexThink(int count,int size)throws Exception{
		int result = 0;
		for (int i = 0; i < count; i++) {
			if(result == size - 1){
				result = 0;
			}
			result = result + 1;
		}
		return result;
	}
	
	/**
	 * 计算已用月份
	 * 
	 * 开始第几月，结束第几月
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> countUsedMonth(Map<String,Object> map) throws Exception{
			Map<String,Object> result = new HashMap<String,Object>();
			for (String key : map.keySet()) {
				//key 开始年月
				  String year = key.split("-")[0];
				  String month = key.split("-")[1];
				  if("1".equals(year)){
					  int mon = Integer.parseInt(month)-8;
					  int count = Integer.parseInt((String)map.get(key)) + mon;
					  result.put(mon+"", count);
				  }
				  if("2".equals(year)){
					  int mon = Integer.parseInt(month)+4;
					  int count = Integer.parseInt((String)map.get(key)) + mon;
					  result.put(mon+"", count);
				  }
				  if("3".equals(year)){
					  int mon = Integer.parseInt(month)+16;
					  int count = Integer.parseInt((String)map.get(key)) + mon;
					  result.put(mon+"", count);
				  }
				  if("4".equals(year)){
					  int mon = Integer.parseInt(month)+28;
					  int count = Integer.parseInt((String)map.get(key)) + mon;
					  result.put(mon+"", count);
				  }
			 }
			return result;
		}
	
	/**
	 * 没用的Map
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> notUsed(Map<String,Object> map) throws Exception{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		// 1-17-17
		//int total = 33;
		int[] total = new int[33];
		for (int i = 0; i < total.length; i++) {
			total[i]=0;
		}
		//1-8 10-15 17-24
		
		for (String key : map.keySet()) {
			int timeStart = Integer.parseInt(key);
			int timeEnd = Integer.parseInt((String)map.get(key));
			for (int i = timeStart; i < timeEnd-timeStart; i++) {
				total[i]=1;
			}
		}
		
		boolean writeBegin = false;
		boolean writeEnd = false;
		String beginTime = "";
		String endTime = "";
		for (int i = 0; i < total.length; i++) {
			if(total[i]==0){
				if(total[i]==0&&writeBegin==true){
					beginTime = total[i]+"";
					writeBegin = false;
					writeEnd = true;
				}
			}
			if(total[i]!=0&&writeEnd){
				endTime = total[i-1]+"";
				writeEnd = false;
				resultMap.put(beginTime,endTime);
			}
		}
		
		return resultMap;
	}
	

}
