package com.doctortrain.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.UserRoleRelation;
import com.doctortrain.dao.UserRoleRelationDAO;

@Repository("userRoleRelationDao")
public class UserRoleRelationDAOImpl implements UserRoleRelationDAO {
	
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserRoleRelation> getByUsername(String username) {
		return getSession().createQuery("from UserRoleRelation urr where urr.user.username=:username")
				.setParameter("username",username)
				.list();
	}
	
	public List<UserRoleRelation> getByRoleId(String roleId) {
		return getSession().createQuery("from UserRoleRelation urr where urr.role.id=:roleId")
				.setParameter("roleId",roleId)
				.list();
	}

	public UserRoleRelation getByUsernameAndRoleId(String username, String roleId) {
		return (UserRoleRelation) getSession().createQuery("from UserRoleRelation urr where urr.user.username=:username and urr.role.id =:roleId")
				.setParameter("username", username)
				.setParameter("roleId", roleId)
				.uniqueResult();
	}

	public void save(UserRoleRelation userRoleRelation) {
		getSession().save(userRoleRelation);
	}

	public void delete(UserRoleRelation userRoleRelation) {
		getSession().delete(userRoleRelation);
	}
	
}
