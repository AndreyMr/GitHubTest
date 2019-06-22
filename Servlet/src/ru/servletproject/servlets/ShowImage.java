package ru.servletproject.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.servletproject.web.beans.Book;

/**
 * Servlet implementation class ShowImage
 */
@WebServlet("/ShowImage")
public class ShowImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		OutputStream out = response.getOutputStream();
		try {
			int index = Integer.valueOf(request.getParameter("index"));
			List<Book> bookList = (List<Book>) request.getSession().getAttribute("currentBookList");
			Book book = bookList.get(index);
			response.setContentLength(book.getImage().length);
			out.write(book.getImage());
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
