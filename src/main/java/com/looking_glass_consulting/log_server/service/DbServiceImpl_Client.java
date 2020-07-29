package com.looking_glass_consulting.log_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.looking_glass_consulting.log_server.dao.DbDAO;
import com.looking_glass_consulting.log_server.entity.Client;

@Service
public class DbServiceImpl_Client implements DbService<Client> {

	@Autowired
	private DbDAO<Client> dao;
	
	@Override
	@Transactional
	public List<Client> get() {
		return dao.get();
	}

	@Override
	@Transactional
	public Client getSingle(int id) {
		return dao.getSingle(id);
	}

	@Override
	@Transactional
	public void save(Client t) {
		dao.save(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}
}
