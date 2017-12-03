package com.doctortrain.service;

public interface InitDataService {
	
	/**
	 * 导入xml文件  第一次搭建好环境初始化
	 * @param xmlFile
	 * @throws Exception
	 */
	public void importXmlFile(String xmlFile) throws Exception;
}
