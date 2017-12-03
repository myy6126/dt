package com.doctortrain.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.Role;
import com.doctortrain.bean.TeacherInfo;
import com.doctortrain.dao.RoleDAO;
import com.doctortrain.dao.TeacherInfoDAO;

@Repository("teacherInfoDao")
public class TeacherInfoDAOImpl implements TeacherInfoDAO {

	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public void addTeacherInfo(TeacherInfo teacherInfo) throws Exception {
		getSession().save(teacherInfo);
	}

	public void updateTeacherInfo(TeacherInfo teacherInfo) throws Exception {
		getSession().update(teacherInfo);
	}

	public TeacherInfo getTeacherInfoById(int id) throws Exception {
		return (TeacherInfo) getSession().createQuery("from TeacherInfo t where t.id =:id")
				.setParameter("id", id)
				.uniqueResult();
	}

	public void deleteTeacherInfoById(String[] ids) throws Exception {
		StringBuilder hqlSb = new StringBuilder();
		//DELETE FROM table WHERE date in('c','e','f')
		hqlSb.append("delete TeacherInfo t where t.id in(");
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
			 query.setParameter(i, Integer.valueOf(ids[i]));
		}
		int reulstInt = query.executeUpdate();

	}

	public List<TeacherInfo> getAllTeacherInfoes() throws Exception {
		String hql = "from TeacherInfo";
		Query query = getSession().createQuery(hql);
		List<TeacherInfo> listResult = query.list();
		return listResult;
	}
}
