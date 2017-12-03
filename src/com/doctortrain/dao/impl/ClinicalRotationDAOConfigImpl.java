package com.doctortrain.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.ClinicalRotationConfig;
import com.doctortrain.dao.ClinicalRotationConfigDAO;

@Repository("clinicalRotationConfigDao")
public class ClinicalRotationDAOConfigImpl implements ClinicalRotationConfigDAO {

	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void addCRC(ClinicalRotationConfig cr) throws Exception {
		getSession().save(cr);
	}

	public void deleteCRC(int id) throws Exception {
		getSession().createQuery("delete ClinicalRotationConfig cr where cr.id=:id")
		.setParameter("id", id)
		.executeUpdate();
	}

	public void updateCRC(ClinicalRotationConfig cr) throws Exception {
		getSession().update(cr);
	}

	public ClinicalRotationConfig getCRCById(int id) throws Exception {
		return (ClinicalRotationConfig)getSession().createQuery("from ClinicalRotationConfig cr where cr.id =:id")
				.setParameter("id", id)
				.uniqueResult();
	}
	
	public List<ClinicalRotationConfig> getCRCByPriority(int id) throws Exception {
		return (List<ClinicalRotationConfig>)getSession().createQuery("from ClinicalRotationConfig cr where cr.priority =:id")
				.setParameter("id", id)
				.list();
	}

	public List<ClinicalRotationConfig> getAllCRC() throws Exception {
		String hql = "from ClinicalRotationConfig cr";
		Query query = getSession().createQuery(hql);
		List<ClinicalRotationConfig> listResult = query.list();
		return listResult;
	}
	
	public List<Integer> getAllRuleRum() throws Exception {
		String hql = "select distinct(cr.ruleNum) from ClinicalRotationConfig cr";
		Query query = getSession().createQuery(hql);
		List<Integer> listResult = query.list();
		return listResult;
	}
	
	public List<Integer> getAllPriority() throws Exception {
		String hql = "select distinct(cr.priority) from ClinicalRotationConfig cr order by cr.priority";
		Query query = getSession().createQuery(hql);
		List<Integer> listResult = query.list();
		return listResult;
	}

	public List<ClinicalRotationConfig> getAllByStutas(String status) throws Exception {
		String hql = "select subject from ClinicalRotationConfig cr where cr.status=:status";
		Query query = getSession().createQuery(hql).setParameter("status", status);
		List<ClinicalRotationConfig> listResult = query.list();
		return listResult;
	}

}
