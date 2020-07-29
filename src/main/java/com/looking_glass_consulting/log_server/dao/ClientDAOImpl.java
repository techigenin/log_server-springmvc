package com.looking_glass_consulting.log_server.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.looking_glass_consulting.log_server.entity.Client;

@Repository
public class ClientDAOImpl implements DbDAO<Client> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Client> get() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		Query<Client> theQuery = currentSession.createQuery("from Client", Client.class);
		
		List<Client> clients = theQuery.getResultList();
		
		return clients;
	}

	@Override
	public Client getSingle(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Client> theQuery = currentSession.createQuery("from Client where clientId=:id", Client.class);
		theQuery.setParameter("id", id);
		
		Client client = null;
		
		try {
			System.out.println(theQuery.getQueryString());
			client = theQuery.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("Exception --> No Client found with Id: " + id);
		}
		
		return client;
	}

	@Override
	public void save(Client client) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(client);
	}

	@Override
	public void delete(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Client> theQuery = currentSession.createQuery("delete from Client where id=:id", Client.class);
		theQuery.setParameter("id", id);
		
		theQuery.executeUpdate();
	}

}
