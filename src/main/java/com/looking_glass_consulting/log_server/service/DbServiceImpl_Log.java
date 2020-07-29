package com.looking_glass_consulting.log_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.looking_glass_consulting.log_server.dao.DbDAO;
import com.looking_glass_consulting.log_server.entity.Log;

@Service
public class DbServiceImpl_Log implements DbService<Log> {

	@Autowired
	private DbDAO<Log> dao;
	
	@Override
	@Transactional
	public List<Log> get() {
		return dao.get();
	}

	@Override
	@Transactional
	public Log getSingle(int id) {
		return dao.getSingle(id);
	}

	@Override
	@Transactional
	public void save(Log t) {
		dao.save(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}
}
