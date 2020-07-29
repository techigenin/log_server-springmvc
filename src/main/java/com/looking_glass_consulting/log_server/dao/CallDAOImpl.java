package com.looking_glass_consulting.log_server.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.looking_glass_consulting.log_server.entity.Call;

@Repository
public class CallDAOImpl implements DbDAO<Call> {

	@Autowired
	protected SessionFactory sessionFactory;
	
	@Override
	public List<Call> get() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Call> theQuery = 
				currentSession.createQuery(
						"FROM Call c "
						+ "JOIN FETCH c.salesPerson "
						+ "JOIN FETCH c.client", 
						Call.class);
		
		List<Call> calls = theQuery.getResultList();
		
		return calls;
	}

	@Override
	public Call getSingle(int callId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Call> theQuery =
				currentSession.createQuery(
						"SELECT c from Call c "
						+ "JOIN FETCH c.salesPerson "
						+ "JOIN FETCH c.client "
						+ "WHERE c.callId=:callId", 
						Call.class);
		theQuery.setParameter("callId", callId);
		
		Call call = null;
		
		try {
			call = theQuery.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("Exception --> No Call found with Id: " + callId);
		}
		
		return call;
	}

	@Override
	public void save(Call call) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(call);
	}

	@Override
	public void delete(int callId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Call> theQuery = currentSession.createQuery("delete from Call where callId=:callId", Call.class);
		theQuery.setParameter("callId", callId);
		
		theQuery.executeUpdate();
	}

}
