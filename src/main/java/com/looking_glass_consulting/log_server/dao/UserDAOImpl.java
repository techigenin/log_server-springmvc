package com.looking_glass_consulting.log_server.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.looking_glass_consulting.log_server.entity.User;

@Repository
public class UserDAOImpl implements DbDAO<User> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> get() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		Query<User> theQuery = currentSession.createQuery("from User", User.class);
		
		List<User> users = theQuery.getResultList();
		
		return users;
	}

	@Override
	public User getSingle(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> theQuery = currentSession.createQuery("from User where id=:id", User.class);
		theQuery.setParameter("id", id);
		
		User user = null;
		
		try {
			user = theQuery.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("Exception --> No User found with Id: " + id);
		}
		
		return user;
	}

	@Override
	public void save(User user) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(user);
	}

	@Override
	public void delete(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		System.out.println("deleting user: " + id);
		
		Query theQuery = currentSession.createQuery("delete from User where id=:id");
		theQuery.setParameter("id", id);
		
		theQuery.executeUpdate();
	}

}
