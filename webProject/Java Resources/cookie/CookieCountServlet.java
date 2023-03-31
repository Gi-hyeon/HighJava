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
 * Servlet implementation class CookieCountServlet
 */
@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용하든 안하든 구비하는 게 좋음 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int count = 0; // 현재 count 값이 저장될 변수
		
		// 쿠키 이름을 count로 한다. -> 현재 이 쿠키가 있는지 검사한다.
		Cookie[] cookieArr = request.getCookies();
		if (cookieArr != null) {
			for (Cookie cookie : cookieArr) {
				if("count".equals(cookie.getName())) {  // 'count라는 쿠키가 있는지 검사'
					String value = cookie.getValue();	// 현재의 쿠키값 가져오기
					count = Integer.parseInt(value);
				}
			}
		}
		
		count++; // count 증가
		
		// 증가된 값을 쿠키값으로 갖는 Cookie 객체 생성
		//Cookie countCookie = new Cookie("count", count + "");
		Cookie countCookie = new Cookie("count", String.valueOf(count));
		
		// 생성된 쿠키정보 저장하기
		response.addCookie(countCookie);
		
		out.println("<html><head><meta charset='UTF-8'><title>쿠키 Count 연습</title></head>");
		out.println("<body>");
		
		out.println("<h2>어서오세요. 당신은 " + count + "번째 방문입니다.</h2><br><br>");
		out.println("<a href='" + request.getContextPath() + "/cookieCountServlet.do'>카운트 증가하기</a>");
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
