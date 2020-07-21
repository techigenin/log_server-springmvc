package com.looking_glass_consulting.log_server.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public interface DbDAO<T> {
	
	public abstract List<T> get();
	public abstract T getSingle(int id);
	public abstract void save(T t);
	public abstract void delete(int id);
}
