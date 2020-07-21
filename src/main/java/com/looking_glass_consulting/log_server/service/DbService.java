package com.looking_glass_consulting.log_server.service;

import java.util.List;

public interface DbService<T> {
	List<T> get();
	T getSingle(int id);
	void save(T t);
	void delete(int id);
}
