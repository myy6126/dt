package com.doctortrain.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.Announcement;
import com.doctortrain.dao.AnnouncementDAO;

@Repository("announcementDao")
public class AnnouncementDAOImpl implements AnnouncementDAO {

	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void addAnc(Announcement at) throws Exception {
		getSession().save(at);
	}

	public void updateAnc(Announcement at) throws Exception {
		getSession().update(at);
	}

	public List<Announcement> getAllAncs() throws Exception {
		String hql = "from Announcement ann order by ann.createTime desc";
		Query query = getSession().createQuery(hql);
		List<Announcement> listResult = query.list();
		return listResult;
	}

	public Announcement getAncById(int id) throws Exception {
		return (Announcement)getSession().createQuery("from Announcement ann where ann.id =:id")
				.setParameter("id", id)
				.uniqueResult();
	}

	public void deleteAncById(int[] ids) throws Exception {
		StringBuilder hqlSb = new StringBuilder();
		//DELETE FROM table WHERE date in('c','e','f')
		hqlSb.append("delete Announcement ann where ann.id in(");
		if(ids.length==1){
			hqlSb.append("?)");
		}else{
			for(int i=0;i<ids.length;i++){
				if(i==0){
					hqlSb.append("?");
				}else if(i==ids.length-1){
					hqlSb.append(",?)");
				}else{
					hqlSb.append(",?");
				}
			}
		}
		 Query query = getSession().createQuery(hqlSb.toString());
		 for (int i = 0; i < ids.length; i++) {
			 query.setParameter(i, ids[i]);
		}
		int reulstInt = query.executeUpdate();
	}

	public List<Announcement> getAllPublish(int startIndex,int pageSize) throws Exception {
		String hql = "from Announcement ann where ann.status='1' order by ann.publishTime desc";
		Query query = getSession().createQuery(hql).setFirstResult(startIndex).setMaxResults(pageSize);
		List<Announcement> listResult = query.list();
		return listResult;
	}

	public int getCount() throws Exception {
		return ((Long)getSession().createQuery("select count(*) from Announcement ann")//
		.uniqueResult()) //
		.intValue();
	}

}
