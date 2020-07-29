package com.looking_glass_consulting.log_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.looking_glass_consulting.log_server.dao.DbDAO;
import com.looking_glass_consulting.log_server.entity.Call;

@Service
public class DbServiceImpl_Call implements DbService<Call> {

	@Autowired
	private DbDAO<Call> dao;
	
	@Override
	@Transactional
	public List<Call> get() {
		return dao.get();
	}

	@Override
	@Transactional
	public Call getSingle(int id) {
		return dao.getSingle(id);
	}

	@Override
	@Transactional
	public void save(Call t) {
		dao.save(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}
}
