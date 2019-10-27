package com.dobleb.productos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dobleb.productos.dao.CategoriaDAO;
import com.dobleb.productos.dao.ProductoDAO;
import com.dobleb.productos.model.Producto;

/**
 * Servlet implementation class Index
 */
@WebServlet("/")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
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
		boolean error = false;
		try {
			if(request.getParameter("type") != null) {
				if(request.getParameter("type").equals("code") && request.getParameter("search") != null && request.getParameter("search") != "") {
					int code = Integer.parseInt(request.getParameter("search"));
					
					List<Producto> productos = new ArrayList<Producto>();
					RequestDispatcher dispatcher = request.getRequestDispatcher("/views/show.jsp");
					productos.add(productoDAO.findById(code));;
					request.setAttribute("productos", productos);
					dispatcher.forward(request, response);
				} else if(request.getParameter("type").equals("keyword") && request.getParameter("search") != null && request.getParameter("search") != "") {
					String keyword = request.getParameter("search");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/views/show.jsp");
					List<Producto> productos = productoDAO.findByKeyword(keyword);
					request.setAttribute("productos", productos);
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/views/show.jsp");
					List<Producto> productos = productoDAO.findAll();
					request.setAttribute("productos", productos);
					dispatcher.forward(request, response);
				}
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/show.jsp");
				List<Producto> productos = productoDAO.findAll();
				request.setAttribute("productos", productos);
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/show.jsp");
			System.out.println(e);
			error = true;
			request.setAttribute("error", error);
			dispatcher.forward(request, response);
			e.getStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
