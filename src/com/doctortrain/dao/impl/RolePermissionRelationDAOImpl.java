package com.doctortrain.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.RolePermissionRelation;
import com.doctortrain.dao.RolePermissionRelationDAO;

@Repository("rolePermissionRelationDao")
public class RolePermissionRelationDAOImpl implements RolePermissionRelationDAO {
	
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public RolePermissionRelation getRoleIdAndPermissionId(String roleId,String permissionId) {
		return (RolePermissionRelation) getSession().createQuery("from RolePermissionRelation rpr where rpr.role.id=:roleId and rpr.permission.id=:permissionId")
				.setParameter("roleId", roleId)
				.setParameter("permissionId", permissionId)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<RolePermissionRelation> getByRoleId(String roleId) {
		return getSession().createQuery("from RolePermissionRelation rpr where rpr.role.id=:roleId")
				.setParameter("roleId", roleId)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<RolePermissionRelation> getByPermissionId(String permissionId) {
		return getSession().createQuery("from RolePermissionRelation rpr where rpr.permission.id=:permissionId")
				.setParameter("permissionId", permissionId)
				.list();
	}

	public void delete(RolePermissionRelation rolePermissionRelation) {
		getSession().delete(rolePermissionRelation);
	}

	public void save(RolePermissionRelation rolePermissionRelation) {
		getSession().save(rolePermissionRelation);
	}

	public void flush() {
		getSession().flush();
	}

}
