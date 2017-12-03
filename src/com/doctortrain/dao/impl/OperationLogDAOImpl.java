package com.doctortrain.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.doctortrain.bean.OperationLog;
import com.doctortrain.dao.OperationLogDAO;

@Repository("operationLogDao")
public class OperationLogDAOImpl implements OperationLogDAO {
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@SuppressWarnings("unchecked")
	public List<OperationLog> getByPage(Integer pageNo,
			Integer pageSize, String operatorName, String operationType,
			String startTime, String endTime) {
		/*StringBuilder hql = new StringBuilder("from OperationLog ol join fetch ol.operator where 1=1 ");
		//准备一个list集合 (有下标、自动扩展)
		List<Object> paramList = new ArrayList<Object>();
		if(StringUtil.isNotEmpty(operatorName)){
			hql.append(" and ol.operator.name like ?");
			paramList.add(operatorName+"%");
		}
		if(StringUtil.isNotEmpty(operationType)){
			hql.append(" and ol.type = ?");
			paramList.add(operationType);
		}
		if(StringUtil.isNotEmpty(startTime)){
			hql.append(" and ol.time >=?");	
			paramList.add(startTime);
		}
		if(StringUtil.isNotEmpty(endTime)){
			hql.append(" and ol.time <=?");
			paramList.add(endTime);
		}
		hql.append(" order by ol.time desc ");
		
		Query query =  getSession().createQuery(hql.toString());
		for (int i = 0; i < paramList.size(); i++) {
			query.setParameter(i, paramList.get(i));
		}
		query.setFirstResult((pageNo-1)*pageSize);
		query.setMaxResults(pageSize);
		List<OperationLog> dataList = query.list();
		//------------------------------------------------------------
		StringBuilder hqlCount = new StringBuilder("select count(*) from OperationLog ol where 1=1 ");
		//准备一个list集合 (有下标、自动扩展)
		List<Object> paramListCount = new ArrayList<Object>();
		if(StringUtil.isNotEmpty(operatorName)){
			hqlCount.append(" and ol.operator.name like ?");
			paramListCount.add(operatorName+"%");
		}
		if(StringUtil.isNotEmpty(operationType)){
			hqlCount.append(" and ol.type = ?");
			paramListCount.add(operationType);
		}
		if(StringUtil.isNotEmpty(startTime)){
			hqlCount.append(" and ol.time >=?");	
			paramListCount.add(startTime);
		}
		if(StringUtil.isNotEmpty(endTime)){
			hqlCount.append(" and ol.time <=?");
			paramListCount.add(endTime);
		}
		
		Query queryCount =  getSession().createQuery(hqlCount.toString());
		for (int i = 0; i < paramListCount.size(); i++) {
			queryCount.setParameter(i, paramListCount.get(i));
		}
		Long total = (Long) queryCount.uniqueResult();
		
		PaginationVO<OperationLog> paginationVO = new PaginationVO<OperationLog>();
		paginationVO.setTotal(total);
		paginationVO.setDataList(dataList);*/
		return null;
	}
	public void save(OperationLog ol) {
		getSession().save(ol);
	}
	@SuppressWarnings("unchecked")
	public List<OperationLog> getAll() {
		return getSession().createQuery("from OperationLog ol order by ol.time desc")
				.list();
	}
	public int deleteOperationLog(String id) {
		return getSession().createQuery("delete OperationLog ol where ol.id=:id")
		.setParameter("id", id)
		.executeUpdate();
	}
	public void deleteAll() {
		getSession().createQuery("delete from OperationLog")
		.executeUpdate();
	}

}

