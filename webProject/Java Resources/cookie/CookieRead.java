package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieRead
 */
@WebServlet("/cookieRead.do")
public class CookieRead extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		// 저장된 쿠키 정보 읽어오기
		
		// 1. 전체 쿠키 정보를 request 객체를 통해서 가져온다 -> 가져온 쿠키 정보들은 'Cookie[]'형태로 반환된다.
		Cookie[] cookieArr = request.getCookies();
		
		out.println("<html><head><meta charset='UTF-8'><title>쿠키연습</title></head>");
		out.println("<body>");
		
		out.println("<h3>저장된 쿠키정보 확인하기</h3><br>");
		
		if (cookieArr == null || cookieArr.length == 0) {
			out.println("<h3>저장된 쿠키가 하나도 없습니다.</h3>");
		} else {
			// 2. 쿠키 배열에서 해당쿠키 정보를 구해온다.
			for (Cookie cookie : cookieArr) {
				String name = cookie.getName(); // '쿠키이름' 가져오기
				
				//String value = cookie.getValue(); // '쿠키값' 가져오기
				
				// 쿠키값 중에 한글로 저장된 데이터는 데이터를 가져와서 디코딩 후 사용해야 한다.
				String value = URLDecoder.decode(cookie.getValue(), "UTF-8");
				
				out.println("쿠키이름 : " + name + "<br>");
				out.println("쿠키값 : " + value + "<br><hr>");
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
