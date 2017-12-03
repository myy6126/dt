package com.doctortrain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doctortrain.bean.Announcement;
import com.doctortrain.dao.AnnouncementDAO;
import com.doctortrain.service.AnnouncementService;

@Service("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService {

	@Resource(name="announcementDao")
	public AnnouncementDAO announcementDao;
	
	public void addAnnouncement(Announcement at) throws Exception {
		announcementDao.addAnc(at);
	}

	public Announcement getAnnouncementById(int id) throws Exception {
		return announcementDao.getAncById(id);
	}

	public List<Announcement> getAllAnn() throws Exception {
		return announcementDao.getAllAncs();
	}

	public void updateAnnouncement(Announcement at) throws Exception {
		announcementDao.updateAnc(at);
	}

	public void deleteByAnnouncementId(int[] ids) throws Exception {
		announcementDao.deleteAncById(ids);
	}

	public List<Announcement> getAllPublishAnn(int startIndex,int pageSize) throws Exception {
		return announcementDao.getAllPublish(startIndex,pageSize);
	}

	public int getCount() throws Exception {
		return announcementDao.getCount();
	}

}
