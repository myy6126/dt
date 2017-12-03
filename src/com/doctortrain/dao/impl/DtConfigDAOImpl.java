package com.doctortrain.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.DtConfig;
import com.doctortrain.dao.DtConfigDAO;

@Repository("dtConfigDao")
public class DtConfigDAOImpl implements DtConfigDAO {
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public void addDc(DtConfig dc) throws Exception {
		getSession().save(dc);
	}

	public void updateDc(DtConfig dc) throws Exception {
		getSession().update(dc);
	}

	public void deleteDc(DtConfig dc) throws Exception {
		getSession().delete(dc);
	}

	public DtConfig getDcByName(String name) throws Exception {
		return (DtConfig) getSession().createQuery("from DtConfig dc where dc.name =:name")
				.setParameter("name", name)
				.uniqueResult();
	}
	
	public List<DtConfig> getAll() throws Exception {
		return (List<DtConfig>) getSession().createQuery("from DtConfig").list();
	}
	public void deleteDcByName(String name) throws Exception {
		getSession().createQuery("delete DtConfig dc where dc.name=:name")
		.setParameter("name", name)
		.executeUpdate();
	}
	public List<DtConfig> getAllGrow() throws Exception {
		return getSession().createQuery("from DtConfig dc where length(dc.name)=4").list();
	}

}
