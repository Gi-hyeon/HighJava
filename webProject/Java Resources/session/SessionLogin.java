package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionLogin
 */
@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("userid");
		String userPass = request.getParameter("userpass");
		
		HttpSession session = request.getSession();
		
		//out.println("<html><head><meta charset='UTF-8'><title>Session Login</title></head>");
		//out.println("<body>");
		
		if ("admin".equals(userId) && "1234".equals(userPass) ) {
			session.setAttribute("USERID", userId);
			//session.setAttribute("USERPASS", userPass);
			
			//String sessionValue = (String) session.getAttribute("USERID");
			
			//out.println("<h2>" + sessionValue + "님 반갑습니다.</h2>");
			
			//out.println("<a href='" + request.getContextPath() +"/basic/session/sessionLogin.jsp'>로그아웃</a>");
			response.sendRedirect(request.getContextPath() +"/basic/session/sessionLogin.jsp");
		} else {
			response.sendRedirect(request.getContextPath() +"/basic/session/sessionLogin.jsp");
		}
		
		
	//	out.println("</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
