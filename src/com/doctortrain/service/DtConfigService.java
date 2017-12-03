package com.doctortrain.service;

import java.util.List;

import com.doctortrain.bean.DtConfig;

public interface DtConfigService {
	public void addDt(DtConfig dc)throws Exception;
	public void updateDt(DtConfig dc)throws Exception;
	public void deleteDt(DtConfig dc)throws Exception;
	public void deleteDtByName(String name)throws Exception;
	public DtConfig getByName(String name)throws Exception;
	public List<DtConfig> getAll() throws Exception;
	public List<DtConfig> getAllGrow() throws Exception;
}
