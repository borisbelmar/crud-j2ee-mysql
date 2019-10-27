package com.dobleb.productos.model;

public class Producto {
	private int codigo;
	private String nombre;
	private int precio;
	private int cod_categoria;
	private String nom_categoria;
	
	public Producto(String nombre, int precio, int cod_categoria) {
		this.nombre = nombre;
		this.precio = precio;
		this.cod_categoria = cod_categoria;
	}
	
	public Producto(int codigo, String nombre, int precio, int cod_categoria) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.cod_categoria = cod_categoria;
	}
	
	public Producto(int codigo, String nombre, int precio, int cod_categoria, String nom_categoria) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.cod_categoria = cod_categoria;
		this.nom_categoria = nom_categoria;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getCod_categoria() {
		return cod_categoria;
	}

	public void setCod_categoria(int cod_categoria) {
		this.cod_categoria = cod_categoria;
	}

	public String getNom_categoria() {
		return nom_categoria;
	}

	public void setNom_categoria(String nom_categoria) {
		this.nom_categoria = nom_categoria;
	}
	
}
