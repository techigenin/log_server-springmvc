package com.looking_glass_consulting.log_server.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.looking_glass_consulting.log_server.entity.Comment;

@Repository
public class CommentDAOImpl implements DbDAO<Comment> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Comment> get() {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Comment> theQuery = currentSession.createQuery(
				"FROM Comment co "
				+ "JOIN FETCH co.log l "
				+ "JOIN FETCH l.call c "
				+ "JOIN FETCH l.user "
				+ "JOIN FETCH c.salesPerson "
				+ "JOIN FETCH c.client", 
				Comment.class);
		
		List<Comment> comments = theQuery.getResultList();
		
		return comments;
	}

	@Override
	public Comment getSingle(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Comment> theQuery = currentSession.createQuery(
				"FROM Comment co "
				+ "JOIN FETCH co.log l "
				+ "JOIN FETCH l.call c "
				+ "JOIN FETCH l.user "
				+ "JOIN FETCH c.salesPerson "
				+ "JOIN FETCH c.client "
				+ "WHERE commentId=:id", 
				Comment.class);
		theQuery.setParameter("id", id);
		
		Comment comment = null;
		
		try {
			comment = theQuery.getSingleResult();
		} catch (NoResultException ex) {
			System.out.println("Exception --> No Comment found with Id: " + id);
		}
		
		return comment;
	}

	@Override
	public void save(Comment comment) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(comment);
	}

	@Override
	public void delete(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Comment> theQuery = currentSession.createQuery("delete from Comment where commentId=:id", Comment.class);
		theQuery.setParameter("id", id);
		
		theQuery.executeUpdate();
	}

}
