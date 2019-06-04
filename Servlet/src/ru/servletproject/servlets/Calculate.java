package ru.servletproject.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.servletproject.servlets.supportobject.CalcOperation;
import ru.servletproject.servlets.supportobject.OperationFactory;
import ru.servletproject.servlets.supportobject.Operations;

/**
 * Servlet implementation class Calculate
 */

public class Calculate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Calculate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> listOperations;
		PrintWriter writer = response.getWriter();
		writer.append("<!DOCTYPE html PUBLIC \\\"-//W3C//DTD XHTML 1.0 Strict//EN\\\" \\r\\n\" + \"  \\\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
		// считывание переметров запроса
		String paramNumber1 = request.getParameter("one");
		String paramNumber2 = request.getParameter("two");
		String paramOparation = request.getParameter("operation");
		int number1 = 0;
		int number2 = 0;
		Operations operation = null;
		writer.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + " <head>\r\n" + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
		writer.append("<title>Калькулятор</title>\r\n" + " </head>\r\n");
		writer.println("<body>");

		try {
			// приводим параметры к int
			number1 = Integer.parseInt(paramNumber1);
			number2 = Integer.parseInt(paramNumber2);

			// получаем тип операции
			operation = OperationFactory.getOperations(paramOparation);
			HttpSession session = request.getSession(true);

			// создаем новый калькулятор с данными и типм операции
			CalcOperation calculator = new CalcOperation(number1, number2, operation);
			int resultCalculate = 0;

			// получение результата операции
			resultCalculate = calculator.calc();

			// для новой сессии создаем новый список
			if (session.isNew()) {
				listOperations = new ArrayList<>();
				// либо получаем лист из сессии
			} else
				listOperations = (ArrayList<String>) session.getAttribute("formula");
			// добавление новой операции в список и атрибут сессии
			listOperations.add(number1 + " " + operation.toString() + " " + number2 + " = " + resultCalculate);
			session.setAttribute("formula", listOperations);

			// вывод всех операций
			writer.println("<h1>ID вашей сессии равен: " + session.getId() + "</h1>");
			writer.println("<h3>Список операций (всего:" + listOperations.size() + ") </h3>");

			for (String oper : listOperations) {
				writer.println("<h3>" + oper + "</h3>");
			}

		} catch (Exception e) {
			// предупреждение пользователю в случае ошибки
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			writer.println("<h3 style=\"color:red\">Возникла ошибка. Проверьте параметры</h3>");
			writer.println("<h3>Имена параметров должны быть one, two, operation</h3>");
			writer.println("<h3>Operation должен принимать 1 из 4 значений: add, subtract, multiply, divide</h3>");
			writer.println("<h3>Значения one и two должны быть числами</h3>");
			writer.println("<h1>Пример</h1>");
			writer.println("<h3>?one=1&two=3&operation=add</h3>");

		} finally {
			writer.println("</body>");
			writer.println("</html>");
			writer.close();
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
