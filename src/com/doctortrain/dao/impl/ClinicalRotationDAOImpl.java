package com.doctortrain.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.Announcement;
import com.doctortrain.bean.ClinicalRotation;
import com.doctortrain.dao.ClinicalRotationDAO;

@Repository("clinicalRotationDao")
public class ClinicalRotationDAOImpl implements ClinicalRotationDAO {

	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void addCR(ClinicalRotation cr) throws Exception {
		getSession().save(cr);
	}

	public void deleteCR(int id) throws Exception {
		getSession().createQuery("delete ClinicalRotation cr where cr.id=:id")
		.setParameter("id", id)
		.executeUpdate();
	}
	
	public void deleteCRByStuId(String id) throws Exception {
		getSession().createQuery("delete ClinicalRotation cr where cr.stuId=:id")
		.setParameter("id", id)
		.executeUpdate();
	}

	public void updateCR(ClinicalRotation cr) throws Exception {
		getSession().update(cr);
	}

	public ClinicalRotation getCRById(int id) throws Exception {
		return (ClinicalRotation)getSession().createQuery("from ClinicalRotation cr where cr.id =:id")
				.setParameter("id", id)
				.uniqueResult();
	}

	public List<ClinicalRotation> getAllCR() throws Exception {
		String hql = "from ClinicalRotation cr";
		Query query = getSession().createQuery(hql);
		List<ClinicalRotation> listResult = query.list();
		return listResult;
	}
	
	public void closeSession() throws Exception{
		getSession().close();
	}

	public List<ClinicalRotation> getCrByStuId(String id) throws Exception {
		String hql = "from ClinicalRotation cr where cr.stuId =:stuId";
		Query query = getSession().createQuery(hql).setParameter("stuId", id);
		List<ClinicalRotation> listResult = query.list();
		return listResult;
	}

	public List<ClinicalRotation> getByYear(String year) throws Exception {
		String hql = "from ClinicalRotation cr where cr.year =:year";
		Query query = getSession().createQuery(hql).setParameter("year", year);
		List<ClinicalRotation> listResult = query.list();
		return listResult;
	}



}
