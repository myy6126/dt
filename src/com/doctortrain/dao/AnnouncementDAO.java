package com.doctortrain.dao;

import java.util.List;

import com.doctortrain.bean.Announcement;

public interface AnnouncementDAO {

	/**
	 * 添加公告
	 * @param at
	 * @throws Exception
	 */
	public void addAnc(Announcement at) throws Exception;
	
	/**
	 * 修改公告
	 * @param at
	 * @throws Exception
	 */
	public void updateAnc(Announcement at) throws Exception;
	
	/**
	 * 获取所有公告
	 * @return
	 * @throws Exceptoin
	 */
	public List<Announcement> getAllAncs() throws Exception;
 	
	/**
	 * 获取公告ById
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Announcement getAncById(int id) throws Exception;
	
	/**
	 * 删除公告ById
	 * @param ids
	 * @throws Exception
	 */
	public void deleteAncById(int[] ids) throws Exception; 
	
	/**
	 * 获取所有发布公告
	 * @return
	 * @throws Exception
	 */
	public List<Announcement> getAllPublish(int startIndex,int pageSize) throws Exception;
	
	/**
	 * 获取所有记录条数
	 * @return
	 * @throws Exception
	 */
	public int getCount() throws Exception;
	
}
