package com.tpm.dao;

import java.util.List;

public interface BaseDao<T> {
	
	void add(T t);
	
	void update(T t);
	
	void delete(T t);
	
	T get(String id);
	
	T get(int id);
	
	List<T> findAll();
	
	int findCount();
	
	List<T> findT(int begin,int pageSize);
}
