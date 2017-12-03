package com.doctortrain.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.User;
import com.doctortrain.dao.UserDAO;

@Repository("userDao")
public class UserDAOImpl implements UserDAO {

	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public User getUserByUsername(String username) throws Exception {
		return (User) getSession().createQuery("from User user where user.username =:username")
				.setParameter("username", username)
				.uniqueResult();
	}

	public List<User> getAllUser() throws Exception {
		String hql = "from User";
		Query query = getSession().createQuery(hql);
		List<User> listResult = query.list();
		return listResult;
	}

	public void updateUser(User user) throws Exception {
		getSession().update(user);
	}

	public void deleteUserByUsername(String[] Usernames) throws Exception {
		StringBuilder hqlSb = new StringBuilder();
		//DELETE FROM table WHERE date in('c','e','f')
		hqlSb.append("delete User user where user.id in(");
		if(Usernames.length==1){
			hqlSb.append("?)");
		}else{
			for(int i=0;i<Usernames.length;i++){
				if(i==0){
					hqlSb.append("?");
				}else if(i==Usernames.length-1){
					hqlSb.append(",?)");
				}else{
					hqlSb.append(",?");
				}
			}
		}
		 Query query = getSession().createQuery(hqlSb.toString());
		for (int i = 0; i < Usernames.length; i++) {
			query.setParameter(i, Usernames[i]);
		}
		int reulstInt = query.executeUpdate();
		System.out.println(reulstInt);

	}

	public void addUser(User user) throws Exception {
		getSession().save(user);
	}

	public User getUserByUsernameAndPassword(String username, String password) {
		return (User) getSession().createQuery("from User u where u.username =:username and u.password =:password")
				.setParameter("username", username)
				.setParameter("password", password)
				.uniqueResult();
	}

}
