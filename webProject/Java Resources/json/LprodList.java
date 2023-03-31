package kr.or.ddit.basic.json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.vo.LprodVO;

/**
 * Servlet implementation class LprodList
 */
@WebServlet("/lprodList.do")
public class LprodList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		response.setContentType("application/json; chartset=UTF-8"); // JSON으로 응답할 때 ContentType 설정
		
		ILprodService service = LprodServiceImpl.getService();

		List<LprodVO> list = service.getLprodList();
		
		//request.setAttribute("listValue", list);
		
		//request.getRequestDispatcher("<%=request.getContextPath()%>/lprodList.jsp").forward(request, response);
		
		Gson gson = new Gson();
		
		String jsonData = gson.toJson(list);
		
		response.getWriter().write(jsonData);
		response.flushBuffer();
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
