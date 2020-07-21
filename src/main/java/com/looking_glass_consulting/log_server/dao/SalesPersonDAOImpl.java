package com.looking_glass_consulting.log_server.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.looking_glass_consulting.log_server.entity.SalesPerson;

@Repository
public class SalesPersonDAOImpl implements DbDAO<SalesPerson> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<SalesPerson> get() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		Query<SalesPerson> theQuery = currentSession.createQuery("from SalesPerson", SalesPerson.class);
		
		List<SalesPerson> salesPersons = theQuery.getResultList();
		
		return salesPersons;
	}

	@Override
	public SalesPerson getSingle(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<SalesPerson> theQuery = currentSession.createQuery("from SalesPerson where salesPersonId=:id", SalesPerson.class);
		theQuery.setParameter("id", id);
		
		SalesPerson salesPerson  = null;
		
		try {
			salesPerson = theQuery.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("Exception --> No SalesPerson found with Id: " + id);
		}
		
		return salesPerson;
	}

	@Override
	public void save(SalesPerson salesPerson) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(salesPerson);
	}

	@Override
	public void delete(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<SalesPerson> theQuery = currentSession.createQuery("delete from SalesPerson where id=:id", SalesPerson.class);
		theQuery.setParameter("id", id);
		
		theQuery.executeUpdate();
	}

}
