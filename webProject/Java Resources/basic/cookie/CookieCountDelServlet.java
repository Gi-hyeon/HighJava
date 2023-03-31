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
 * Servlet implementation class CookieCountDelServlet
 */
@WebServlet("/cookieCountDelServlet.do")
public class CookieCountDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='UTF-8'><title>쿠키 CountDel 연습</title></head>");
		out.println("<body>");
		
		Cookie[] cookieArr = request.getCookies();
		
		//쿠키이름이 'count'라는 쿠키 찾기
		if (cookieArr != null) {
			for (Cookie cookie : cookieArr) {
				
				String name = cookie.getName();
				
				if("count".equals(cookie.getName())) {  
					// 'count'라는 쿠키를 찾아서 유지 시간을 0으로 한 후에 다시 저장한다.(삭제 작업)
					cookie.setMaxAge(0);
					response.addCookie(cookie); 
					
					out.println("<h3>카운트가 초기화되었습니다.</h3>");
				}
			}
		}
		
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest02.jsp'>시작 문서로 이동하기</a>");
		
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
