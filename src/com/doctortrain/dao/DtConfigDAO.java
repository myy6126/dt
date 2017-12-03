package com.doctortrain.dao;

import java.util.List;

import com.doctortrain.bean.DtConfig;

public interface DtConfigDAO {
	public void addDc(DtConfig dc)throws Exception;
	public void updateDc(DtConfig dc)throws Exception;
	public void deleteDc(DtConfig dc)throws Exception;
	public void deleteDcByName(String name)throws Exception;
	public DtConfig getDcByName(String name)throws Exception;
	public List<DtConfig> getAll() throws Exception;
	public List<DtConfig> getAllGrow() throws Exception;
}
