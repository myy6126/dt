package com.doctortrain.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.Role;
import com.doctortrain.dao.RoleDAO;

@Repository("roleDao")
public class RoleDAOImpl implements RoleDAO {

	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void addRole(Role role) throws Exception {
		getSession().save(role);
	}

	public void updateRole(Role role) throws Exception {
		getSession().update(role);
	}

	public Role getRoleById(String id) throws Exception {
		return (Role) getSession().createQuery("from Role r where r.id =:id")
				.setParameter("id", id)
				.uniqueResult();
	}

	public void deleteRoleById(String[] ids) throws Exception {
		StringBuilder hqlSb = new StringBuilder();
		//DELETE FROM table WHERE date in('c','e','f')
		hqlSb.append("delete Role role where role.id in(");
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

	public List<Role> getAllRoles() throws Exception {
		String hql = "from Role";
		Query query = getSession().createQuery(hql);
		List<Role> listResult = query.list();
		return listResult;
	}

	public List<Role> getRolesByType(String type) throws Exception {
		String hql = "from Role r where r.type =:type";
		Query query = getSession().createQuery(hql).setParameter("type", type);
		List<Role> listResult = query.list();
		return listResult;
	}

}
