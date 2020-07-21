package com.looking_glass_consulting.log_server.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.looking_glass_consulting.log_server.dao.DbDAO;
import com.looking_glass_consulting.log_server.entity.Comment;

@Service
public class DbServiceImpl_Comment implements DbService<Comment> {

	@Autowired
	private DbDAO<Comment> dao;
	
	@Override
	@Transactional
	public List<Comment> get() {
		return dao.get();
	}

	@Override
	@Transactional
	public Comment getSingle(int id) {
		return dao.getSingle(id);
	}

	@Override
	@Transactional
	public void save(Comment t) {
		dao.save(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}
}
