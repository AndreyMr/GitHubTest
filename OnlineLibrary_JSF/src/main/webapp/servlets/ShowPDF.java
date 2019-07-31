package main.webapp.servlets;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.webapp.controllers.SearchController;

/**
 * Servlet implementation class ShowPDF
 */
@WebServlet(name = "PDFcontent", urlPatterns = { "/ShowPDF" })

public class ShowPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowPDF() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/pdf");
		OutputStream out = response.getOutputStream();
		try {
			int id = Integer.valueOf(request.getParameter("id"));
			SearchController searchController = (SearchController) request.getSession(false).getAttribute("searchController");

			byte[] contentPDF = searchController.getPDFContent(id);
			response.setContentLength(contentPDF.length);
			out.write(contentPDF);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
