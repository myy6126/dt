package com.doctortrain.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.StudentInfo;
import com.doctortrain.dao.StudentInfoDAO;

@Repository("studentInfoDAO")
public class StudentInfoDAOImpl implements StudentInfoDAO {

	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
		//return sessionFactory.openSession();
	}
	
	public void flush(){
		sessionFactory.getCurrentSession().flush();
	}
	
	public void addStudentInfo(StudentInfo stuInfo) throws Exception {
		getSession().save(stuInfo);
	}

	/**
	 * 删除操作(可以批量删除)
	 */
	public void deleteStudentInfo(String[] ids) throws Exception {
		StringBuilder hqlSb = new StringBuilder();
		//DELETE FROM table WHERE date in('c','e','f')
		hqlSb.append("delete StudentInfo stu where stu.id in(");
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
		/*
		 String hql="FROM A WHERE A.ID IN (:alist)";  
		 Query query = getSession().createQuery(hql);  
		 query.setParameterList("alist", a); 
		 */
		
	}

	public void updateStudentInfo(StudentInfo stuInfo) throws Exception {
		getSession().update(stuInfo);
	}

	public StudentInfo getStudentInfoById(String id) throws Exception {
		return (StudentInfo) getSession().createQuery("from StudentInfo stu where stu.id =:id")
				.setParameter("id", id)
				.uniqueResult();
	}
	
	public List<StudentInfo> getStudentInfoByRcId(String id) throws Exception {
		String hql = "from StudentInfo stu where stu.ResidentClass =:id";
		Query query = getSession().createQuery(hql).setParameter("id", id);
		List<StudentInfo> listResult = query.list();
		return listResult;
	}

	public List<StudentInfo> getAllStudentInfo() throws Exception {
		String hql = "from StudentInfo";
		Query query = getSession().createQuery(hql);
		List<StudentInfo> listResult = query.list();
		return listResult;
	}
	
}
