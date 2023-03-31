package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RequestTest02() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");

		//int num1 = Integer.parseInt(request.getParameter("input1"));

		String operator = request.getParameter("operator");

		//int num2 = Integer.parseInt(request.getParameter("input2"));
		
		String num1 = request.getParameter("input1");
		String num2 = request.getParameter("input2");

		double result = 0; // 계산 결과가 저장될 변수 선언
		boolean calcOK = true; // 계산 성공 여부를 나타내는 변수
		
		if ((num1.matches("^[0-9]+$")) && (num2.matches("^[0-9]+$"))) {
			int intNum1 = Integer.parseInt(num1);
			int intNum2 = Integer.parseInt(num2);
			
			switch (operator) {
			case "+":
				result = intNum1 + intNum2;
				break;
			case "-":
				result = intNum1 - intNum2;
				break;
			case "*":
				result = intNum1 * intNum2;
				break;
			case "/":
				if (intNum2 == 0) {
					calcOK = false;
				} else {
					result = (double)intNum1 / intNum2;
					calcOK = true;
				}
				break;
			case "%":
				if (intNum2 == 0) {
					calcOK = false;
				} else {
					result = intNum1 % intNum2;
				}
				break;
			default:
				break;
			}
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><meta charset='UTF-8'><title>Request객체 연습</title></head>");
			out.println("<body>");
			out.println("<h3>" + num1 + " " + operator + " " + num2 + " = " + result + "</h3>");
			out.println("</body></html>");
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><head><meta charset='UTF-8'><title>Request객체 연습</title></head>");
			out.println("<body>");
			out.println("<h3>입력값 오류: 양수를 입력하세요.</h3>");
			out.println("</body></html>");
		}
		 



		request.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("text/html; charset=UTF-8");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

	}

}
