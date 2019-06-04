package ru.servletproject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserVisitStat
 */
@WebServlet("/UserVisitStat")
public class UserVisitStat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserVisitStat() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		// начало страницы и заголовок
		writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML  4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");
		writer.println("<html>\r\n" + " <head>\r\n" + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
		writer.println("<title>Статистика посещений и операций пользователей</title>");
		writer.println(" </head>\r\n<body>");
		// получаем мапу из контекста
		@SuppressWarnings("unchecked")
		Map<String, ArrayList<String>> statMap = (Map<String, ArrayList<String>>) getServletContext().getAttribute("statMap");
		if (statMap != null) {
			writer.println("<h3>Статистика посещений и операций пользователей</h3>");
			writer.println("<table border=\"1\" >");
			writer.println("<tr>");
			// добавляем статистику в таблицу и выводим
			for (Map.Entry<String, ArrayList<String>> map : statMap.entrySet()) {
				writer.println("<td>");
				writer.println("<p> ID сессии клиента: " + map.getKey() + "</p>");
				for (String operation : map.getValue()) {
					writer.println("<p>" + operation + "</p>");
				}
				writer.println("</td>");
			}
			writer.println("</tr>");
			writer.println("</table>");
		} else {
			writer.println("<h3>Посещения отсутствуют<h3>");
		}
		writer.println("</body>\r\n</html>");
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
