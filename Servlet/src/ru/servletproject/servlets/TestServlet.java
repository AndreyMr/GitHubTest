package ru.servletproject.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
// @WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \r\n" + "  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
		response.getWriter()
				.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + " <head>\r\n" + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + "  <title>Высота ячеек</title>\r\n" + "  <style type=\"text/css\">\r\n"
						+ "   TABLE {\r\n" + "    width: 100%; /* Ширина таблицы */\r\n" + "   }\r\n" + "   TD { \r\n" + "    padding: 10px; /* Поля в ячейках */ \r\n" + "   }\r\n" + "   TD.content {\r\n"
						+ "    background: #f0f0f0; /* Цвет фона левой колонки */\r\n" + "   }\r\n" + "   TD.menu {\r\n" + "    width: 120px; /* Ширина правой колонки*/\r\n" + "    background: #9c3022; /* Цвет фона правой колонки */\r\n"
						+ "    color: #fff; /* Цвет текста */\r\n" + "    vertical-align: top;\r\n" + "   }\r\n" + "  </style>\r\n" + " </head>\r\n" + " <body>\r\n" + "  <table>\r\n" + "   <tr>\r\n" + "    <td class=\"content\">\r\n"
						+ "     <p>Мясо отварить до готовности. Промыть свеклу, очистить, \r\n" + "     нарезать соломкой и тушить с помидорами до полуготовности.</p>\r\n" + "     <p>Бульон процедить, мясо нарезать кусочками. В бульон добавить \r\n"
						+ "     нарезанный дольками картофель, довести до кипения, опустить нарезанную \r\n" + "     соломкой свежую капусту и варить 10-15 минут, добавить пассерованные \r\n"
						+ "     овощи, болгарский перец, нашинкованный тонкой соломкой, \r\n" + "     специи и довести до готовности.</p>\r\n" + "     <p>Готовому борщу дать настояться в течение 20-25 минут. \r\n"
						+ "     При подаче к столу добавить сметану, мясо, зелень.</p>\r\n" + "    </td>\r\n" + "    <td class=\"menu\">Борщ</td>\r\n" + "   </tr>\r\n" + "  </table>\r\n" + " </body>\r\n" + "</html>");
		response.getWriter().append("Served at: Андрей ").append(request.getContextPath());
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
