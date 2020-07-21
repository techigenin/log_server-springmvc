package com.looking_glass_consulting.log_server.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.looking_glass_consulting.log_server.dao.DbDAO;
import com.looking_glass_consulting.log_server.entity.User;

@Service
public class DbServiceImpl_User implements DbService<User> {

	@Autowired
	private DbDAO<User> dao;
	
	@Override
	@Transactional
	public List<User> get() {
		return dao.get();
	}

	@Override
	@Transactional
	public User getSingle(int id) {
		return dao.getSingle(id);
	}

	@Override
	@Transactional
	public void save(User t) {
		dao.save(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}
}
