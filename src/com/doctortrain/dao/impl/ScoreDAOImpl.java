package com.doctortrain.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.Role;
import com.doctortrain.bean.Score;
import com.doctortrain.dao.ScoreDAO;

@Repository("scoreDao")
public class ScoreDAOImpl implements ScoreDAO {
	
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public void addScore(Score score) throws Exception {
		getSession().merge(score);
	}

	public void updateScore(Score score) throws Exception {
		getSession().update(score);
	}

	public void deleteScore(Score score) throws Exception {
		getSession().delete(score);
	}

	public Score getScoreAllById(String id) throws Exception {
		return (Score) getSession().createQuery("from Score s where s.id =:id")
				.setParameter("id", id)
				.uniqueResult();
	}

	public List<Score> getScoreAll() throws Exception {
		String hql = "from Score";
		Query query = getSession().createQuery(hql);
		List<Score> listResult = query.list();
		return listResult;
	}

	public void deleteById(String id) throws Exception {
		getSession().createQuery("delete Score s where s.ID=:id")
		.setParameter("id", id)
		.executeUpdate();
	}

}
