package com.dobleb.productos.dao;

import java.sql.SQLException;
import java.util.List;

interface CRUD<T> {
	
	boolean insert(T t) throws SQLException;
	
	List<T> findAll() throws SQLException;
	
	T findById(int id) throws SQLException;
	
	boolean update(T t) throws SQLException;
	
	boolean deleteById(int id) throws SQLException;
	
}