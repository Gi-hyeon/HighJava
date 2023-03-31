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
 * Servlet implementation class CookieDel
 */
@WebServlet("/cookieDel.do")
public class CookieDel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		//  저장된 쿠키 정보 삭제하기
		//  -> 쿠키 정보의 삭제는 쿠키의 유지시간을 0으로 설정하는 방법을 사용한다.
		//  -> 유지 시간을 0으로 설정한 후 다시 addCookie() 메서드를 이용해서 다시 저장하면 기존에 쿠키를 덮어쓴다.
		//     형식) cookie객체변수.setMaxAge(0);
		//           response.addCookie(cookie객체변수);
		
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html><head><meta charset='UTF-8'><title>쿠키연습</title></head>");
		out.println("<body>");
		
		out.println("<h3><쿠키 정보 삭제하기/h3>");
		
		if (cookieArr != null) {
			// 쿠키 배열에서 삭제하려는 쿠키를 찾는다.
			for (Cookie cookie : cookieArr) {
				String name = cookie.getName(); // 쿠키이름 구하기
				
				// 쿠키 이름을 이용하여 삭제할 쿠키 찾기 (예: 쿠키이름이 gender인 쿠키 삭제하기)
				if ("gender".equals(name)) {
					cookie.setMaxAge(0); // 찾은 쿠키의 유지시간을 0으로 설정하기
					
					response.addCookie(cookie); // 유지시간을 변경한 쿠키를 다시 저장한다.(덮어쓰기한다.)
					out.println("<h3>쿠키 삭제 성공!!!</h3>");
				}
			}
			
		}
		
		out.println("<a href='"+ request.getContextPath() +"/basic/cookie/cookieTest01.jsp'>시작문서로 이동하기</a>");
		
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
