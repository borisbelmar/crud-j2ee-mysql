package com.dobleb.productos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dobleb.productos.model.Database;
import com.dobleb.productos.model.Categoria;

public class CategoriaDAO implements CRUD<Categoria> {
	
	private Database db;
	private Connection connection;
 
	public CategoriaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		db = new Database(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	@Override
	public boolean insert(Categoria categoria) throws SQLException {
		String sql = "INSERT INTO tbl_categoria (cat_nom_categoria) VALUES (?)";
		System.out.println(categoria.getNombre());
		db.connect();
		connection = db.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, categoria.getNombre());
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		db.disconnect();
		
		return rowInserted;
	}

	@Override
	public List<Categoria> findAll() throws SQLException {
		List<Categoria> categorias = new ArrayList<Categoria>();
		String sql = "SELECT cat_cod_categoria, cat_nom_categoria FROM tbl_categoria";
		db.connect();
		connection = db.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			
			int codigo = resulSet.getInt("cat_cod_categoria");
			String nombre = resulSet.getString("cat_nom_categoria");
			
			Categoria categoria = new Categoria(codigo, nombre);
			categorias.add(categoria);
		}
		db.disconnect();
		return categorias;
	}

	@Override
	public Categoria findById(int id) throws SQLException {
		Categoria categoria = null;
		 
		String sql = "SELECT cat_cod_categoria, cat_nom_categoria FROM tbl_categoria WHERE cat_cod_categoria=?";
		db.connect();
		connection = db.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			categoria = new Categoria(
				res.getInt("cat_cod_categoria"), 
				res.getString("cat_nom_categoria") 
			);
		}
		res.close();
		db.disconnect();
 
		return categoria;
	}

	@Override
	public boolean update(Categoria categoria) throws SQLException {
		boolean rowUpdate = false;
		
		String sql = "UPDATE tbl_categoria SET cat_nom_categoria=? WHERE cat_cod_categoria=?";
		db.connect();
		connection = db.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, categoria.getNombre());
		statement.setInt(2, categoria.getCodigo());
		
		rowUpdate = statement.executeUpdate() > 0;
		statement.close();
		db.disconnect();
		
		return rowUpdate;
	}

	@Override
	public boolean deleteById(int id) throws SQLException {
		boolean rowDelete = false;
		String sql = "DELETE FROM tbl_categoria WHERE cat_cod_categoria=?";
		db.connect();
		connection = db.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		rowDelete = statement.executeUpdate() > 0;
		statement.close();
		db.disconnect();
 
		return rowDelete;
	}
	
}
