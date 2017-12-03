package com.doctortrain.service;

import java.util.List;

import com.doctortrain.bean.Announcement;

public interface AnnouncementService {
	
	/**
	 * 添加公告
	 * @param at
	 * @throws Exception
	 */
	public void addAnnouncement(Announcement at) throws Exception;

	/**
	 * 获取公告ById
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Announcement getAnnouncementById(int id) throws Exception;
	
	/**
	 * 获取所有公告
	 * @return
	 * @throws Exception
	 */
	public List<Announcement> getAllAnn() throws Exception;
	
	/**
	 * 获取所有发布公告
	 * @return
	 * @throws Exception
	 */
	public List<Announcement> getAllPublishAnn(int startIndex,int pageSize) throws Exception;

	/**
	 * 修改公告
	 * @param at
	 * @throws Exception
	 */
	public void updateAnnouncement(Announcement at) throws Exception;
	
	/**
	 * 删除公告ById
	 * @param ids
	 * @throws Exception
	 */
	public void deleteByAnnouncementId(int[] ids) throws Exception;
	
	/**
	 * 获取记录数
	 * @return
	 * @throws Exception
	 */
	public int getCount() throws Exception;
	
}
