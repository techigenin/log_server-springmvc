package com.looking_glass_consulting.log_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.looking_glass_consulting.log_server.dao.DbDAO;
import com.looking_glass_consulting.log_server.entity.SalesPerson;

@Service
public class DbServiceImpl_SalesPerson implements DbService<SalesPerson> {

	@Autowired
	private DbDAO<SalesPerson> dao;
	
	@Override
	@Transactional
	public List<SalesPerson> get() {
		return dao.get();
	}

	@Override
	@Transactional
	public SalesPerson getSingle(int id) {
		return dao.getSingle(id);
	}

	@Override
	@Transactional
	public void save(SalesPerson t) {
		dao.save(t);
	}

	@Override
	@Transactional
	public void delete(int id) {
		dao.delete(id);
	}
}
