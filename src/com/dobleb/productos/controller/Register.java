package com.dobleb.productos.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dobleb.productos.dao.CategoriaDAO;
import com.dobleb.productos.dao.ProductoDAO;
import com.dobleb.productos.model.Categoria;
import com.dobleb.productos.model.Producto;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    ProductoDAO productoDAO;
    CategoriaDAO categoriaDAO;
    
    public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		try {
			productoDAO = new ProductoDAO(jdbcURL, jdbcUsername, jdbcPassword);
			categoriaDAO = new CategoriaDAO(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			System.out.println("Error en la conexión de Base de Datos");
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Categoria> categorias = categoriaDAO.findAll();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/new.jsp");
			request.setAttribute("categorias", categorias);
			dispatcher.forward(request, response);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Producto producto = new Producto(
					request.getParameter("nombre"), 
					Integer.parseInt(request.getParameter("precio")),
					Integer.parseInt(request.getParameter("categoria"))
				);
			if(productoDAO.insert(producto)) {
				response.sendRedirect(request.getContextPath());
				System.out.println("Insertado");
			} else {
				System.out.println("Error");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
