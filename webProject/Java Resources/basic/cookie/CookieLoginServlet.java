package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieLoginServlet
 */
@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 클라이언트가 보내온 데이터 받기
		String userId = request.getParameter("userid");
		String userPass = request.getParameter("userpass");
		String chkId = request.getParameter("chkid");
		
		// 쿠키 객체 생성(쿠키이름 : 'USERID', 쿠키값 : 로그인한 ID)
		Cookie loginCookie = new Cookie("USERID", userId);
		
		// checkbox가 체크된 상태이면 쿠키 거장, 해제된 상태이면 쿠키 삭제
		if (chkId == null) { // checkbox의 해제 여부 검사
			loginCookie.setMaxAge(0);
		}
		
		response.addCookie(loginCookie);
		
		// --------------------------------------------
		// 로그인 성공 여부(id: test, password: 1234)
		if ("test".equals(userId) && "1234".equals(userPass)) { // 로그인 성공
			// a 태그를 사용하면 링크를 눌러야 이동을 하기 때문에 
			// 자료를 활용하지 않기 때문에 sendRedirect를 사용한다.
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieMain.jsp");
		} else { // 로그인 실패
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieLogin.jsp");
		}
		
		
		/*
		 * PrintWriter writer = response.getWriter();
		 * 
		 * 
		 * writer.println("<html><head><meta charset='UTF-8'><title>쿠키연습</title></head>"
		 * ); writer.println("<body>");
		 * 
		 * writer.println("<h4>"+id+"<br>"+check+"</h4><br>");
		 * 
		 * writer.println("</body></html>");
		 */
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
