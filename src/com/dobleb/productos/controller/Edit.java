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
 * Servlet implementation class Edit
 */
@WebServlet("/edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
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
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Categoria> categorias = categoriaDAO.findAll();
			Producto producto = productoDAO.findById(Integer.parseInt(request.getParameter("code")));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/edit.jsp");
			if(producto != null) {
				request.setAttribute("producto", producto);
				request.setAttribute("categorias", categorias);
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath());
				System.out.println("Código no encontrado");
			}
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
				Integer.parseInt(request.getParameter("codigo")), 
				request.getParameter("nombre"), 
				Integer.parseInt(request.getParameter("precio")), 
				Integer.parseInt(request.getParameter("categoria"))
			);
			if(productoDAO.update(producto)) {
				response.sendRedirect(request.getContextPath());
				System.out.println("Actualizado");
			} else {
				System.out.println("Error al actualizar");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}
