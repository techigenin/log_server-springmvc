package com.looking_glass_consulting.log_server.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.looking_glass_consulting.log_server.entity.Log;
import com.looking_glass_consulting.log_server.entity.User;

@Repository
public class LogsDAOImpl implements DbDAO<Log> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Log> get() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Log> theQuery =
				currentSession.createQuery("FROM Log l "
						+ "JOIN FETCH l.call c "
						+ "JOIN FETCH l.user "
						+ "JOIN FETCH c.salesPerson "
						+ "JOIN FETCH c.client", Log.class);
		
		List<Log> logs = theQuery.getResultList();
		
		return logs;
	}

	@Override
	public Log getSingle(int logId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Log> theQuery =
				currentSession.createQuery("SELECT l FROM Log l "
						+ "JOIN FETCH l.call c "
						+ "JOIN FETCH l.user "
						+ "JOIN FETCH c.salesPerson "
						+ "JOIN FETCH c.client WHERE l.logId=:logId", Log.class);
		theQuery.setParameter("logId", logId);
		
		Log log = null;
		
		try {
			log = theQuery.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("Exception --> No Log found with Id: " + logId);
		}
		
		return log;
	}

	@Override
	public void save(Log log) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(log);
	}

	@Override
	public void delete(int logId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Log> theQuery = currentSession.createQuery("delete from Log where logId=:logId", Log.class);
		theQuery.setParameter("logId", logId);
		
		theQuery.executeUpdate();
	}
}
