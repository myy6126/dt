package com.doctortrain.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.Permission;
import com.doctortrain.dao.PermissionDAO;

@Repository("permissionDao")
public class PermissionDAOImpl implements PermissionDAO {

	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public Permission getPermissionById(String id) throws Exception {
		return (Permission) getSession().createQuery("from Permission permission where permission.id =:id")
				.setParameter("id", id)
				.uniqueResult();
	}

	public List<Permission> getAllPermission() throws Exception {
		String hql = "from Permission";
		Query query = getSession().createQuery(hql);
		List<Permission> listResult = query.list();
		return listResult;
	}

	public void updatePermission(Permission permission) throws Exception {
		getSession().update(permission);
	}

	public void deletePermissionById(String[] ids) throws Exception {
		StringBuilder hqlSb = new StringBuilder();
		//DELETE FROM table WHERE date in('c','e','f')
		hqlSb.append("delete Permission per where per.id in(");
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
		System.out.println(reulstInt);

	}

	public void addPermission(Permission per) throws Exception {
		getSession().save(per);
	}

	public List<Permission> getPermissionByPId(String pid) throws Exception {
		return (List<Permission>) getSession().createQuery("from Permission permission where permission.pid =:pid")
				.setParameter("pid", pid).list();
	}

}
