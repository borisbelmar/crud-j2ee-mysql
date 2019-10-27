package com.dobleb.productos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dobleb.productos.model.Database;
import com.dobleb.productos.model.Producto;

public class ProductoDAO implements CRUD<Producto> {
	
	private Database db;
	private Connection connection;
 
	public ProductoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		db = new Database(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
	@Override
	public boolean insert(Producto producto) throws SQLException {
		String sql = "INSERT INTO tbl_producto (prod_nombre, prod_precio, prod_cod_categoria) VALUES (?, ?, ?)";
		db.connect();
		connection = db.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, producto.getNombre());
		statement.setInt(2, producto.getPrecio());
		statement.setInt(3, producto.getCod_categoria());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		db.disconnect();
		
		return rowInserted;
	}

	@Override
	public List<Producto> findAll() throws SQLException {
		List<Producto> productos = new ArrayList<Producto>();
		String sql = "SELECT prod_codigo, prod_nombre, prod_precio, prod_cod_categoria, cat_nom_categoria FROM tbl_producto LEFT JOIN tbl_categoria ON prod_cod_categoria = cat_cod_categoria";
		db.connect();
		connection = db.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			int codigo = resulSet.getInt("prod_codigo");
			String nombre = resulSet.getString("prod_nombre");
			int precio = resulSet.getInt("prod_precio");
			int cod_categoria = resulSet.getInt("prod_cod_categoria");
			String nom_categoria = resulSet.getString("cat_nom_categoria");
			
			Producto producto = new Producto(codigo, nombre, precio, cod_categoria, nom_categoria);
			productos.add(producto);
		}
		db.disconnect();
		return productos;
	}

	@Override
	public Producto findById(int id) throws SQLException {
		Producto producto = null;
		
		String sql = "SELECT prod_codigo, prod_nombre, prod_precio, prod_cod_categoria, cat_nom_categoria FROM tbl_producto LEFT JOIN tbl_categoria ON prod_cod_categoria = cat_cod_categoria WHERE prod_codigo = ?";
		db.connect();
		connection = db.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, id);
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			producto = new Producto(
				res.getInt("prod_codigo"), 
				res.getString("prod_nombre"), 
				res.getInt("prod_precio"), 
				res.getInt("prod_cod_categoria"), 
				res.getString("cat_nom_categoria")
			);
		}
		res.close();
		db.disconnect();
 
		return producto;
	}
	
	public List<Producto> findByKeyword(String keyword) throws SQLException {
		List<Producto> productos = new ArrayList<Producto>();
		String sql = "SELECT prod_codigo, prod_nombre, prod_precio, prod_cod_categoria, cat_nom_categoria FROM tbl_producto LEFT JOIN tbl_categoria ON prod_cod_categoria = cat_cod_categoria WHERE prod_nombre LIKE '%"+keyword+"%';";
		db.connect();
		connection = db.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resulSet = statement.executeQuery(sql);
		while (resulSet.next()) {
			int codigo = resulSet.getInt("prod_codigo");
			String nombre = resulSet.getString("prod_nombre");
			int precio = resulSet.getInt("prod_precio");
			int cod_categoria = resulSet.getInt("prod_cod_categoria");
			String nom_categoria = resulSet.getString("cat_nom_categoria");
			
			Producto producto = new Producto(codigo, nombre, precio, cod_categoria, nom_categoria);
			productos.add(producto);
		}
		
		db.disconnect();
		return productos;
	}

	@Override
	public boolean update(Producto producto) throws SQLException {
		boolean rowUpdate = false;
		
		String sql = "UPDATE tbl_producto SET prod_nombre=?, prod_precio=?, prod_cod_categoria=? WHERE prod_codigo=?";
		db.connect();
		connection = db.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, producto.getNombre());
		statement.setInt(2, producto.getPrecio());
		statement.setInt(3, producto.getCod_categoria());
		statement.setInt(4, producto.getCodigo());
		
		rowUpdate = statement.executeUpdate() > 0;
		statement.close();
		db.disconnect();
		
		return rowUpdate;
	}

	@Override
	public boolean deleteById(int id) throws SQLException {
		boolean rowDelete = false;
		
		String sql = "DELETE FROM tbl_producto WHERE prod_codigo=?";
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
