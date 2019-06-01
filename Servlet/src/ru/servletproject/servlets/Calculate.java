package ru.servletproject.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.getWriter().append("<!DOCTYPE html PUBLIC \\\"-//W3C//DTD XHTML 1.0 Strict//EN\\\" \\r\\n\" + \"  \\\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
		String paramNumber1 = request.getParameter("one");
		String paramNumber2 = request.getParameter("two");
		String paramOparation = request.getParameter("operation");
		int number1 = 0;
		int number2 = 0;
		Operations operation = null;
		response.getWriter().append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + " <head>\r\n" + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + "  <title>Калькулятор</title>\r\n" + " </head>\r\n"
				+ " <body>\r\n" + "<p>Результат операции: ");
		try {
			number1 = Integer.parseInt(paramNumber1);
			number2 = Integer.parseInt(paramNumber2);
			operation = OperationFactory.getOperations(paramOparation);

			CalcOperation calculator = new CalcOperation(number1, number2, operation);
			int resultCalculate = 0;

			resultCalculate = calculator.calc();
			response.getWriter().append("" + resultCalculate);
		} catch (ArithmeticException | NumberFormatException e) {
			response.getWriter().append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + " <head>\r\n" + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + "  <title>Калькулятор</title>\r\n" + " </head>\r\n"
					+ " <body>\r\n" + "<p>Результат операции: " + e.getMessage() + " </p>\r\n" + " </body>\r\n" + "</html>");
		}
		response.getWriter().append(" </p>\r\n" + " </body>\r\n" + "</html>");
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
