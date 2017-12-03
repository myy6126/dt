package com.doctortrain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doctortrain.bean.DtConfig;
import com.doctortrain.dao.DtConfigDAO;
import com.doctortrain.service.DtConfigService;

@Service("dtConfigService")
public class DtConfigServiceImpl implements DtConfigService {

	@Resource(name="dtConfigDao")
	private DtConfigDAO dtConfigDAO;
	
	public void addDt(DtConfig dc) throws Exception {
		dtConfigDAO.addDc(dc);
	}

	public void updateDt(DtConfig dc) throws Exception {
		dtConfigDAO.updateDc(dc);
	}

	public void deleteDt(DtConfig dc) throws Exception {
		dtConfigDAO.deleteDc(dc);
	}
	
	public void deleteDtByName(String name) throws Exception {
		dtConfigDAO.deleteDcByName(name);
	}

	public DtConfig getByName(String name) throws Exception {
		return dtConfigDAO.getDcByName(name);
	}

	public List<DtConfig> getAll() throws Exception {
		return dtConfigDAO.getAll();
	}

	public List<DtConfig> getAllGrow() throws Exception {
		return dtConfigDAO.getAllGrow();
	}

}
