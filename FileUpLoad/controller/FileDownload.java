package kr.or.ddit.basic.fileupload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.basic.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.basic.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileinfoVO;

/**
 * Servlet implementation class FileDownload
 */
@WebServlet("/fileDownload.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 파라미터로 넘어온 파일번호(fileno)를 구한다.
		String strFileNo = request.getParameter("fileno");
		int fileNo = Integer.parseInt(strFileNo);
		
		// 파일번호를 이용하여 DB에서 해당 파일에 대한 정보를 가져온다.
		IFileinfoService service = FileinfoServiceImpl.getService();
		
		FileinfoVO fvo = service.getFileinfo(fileNo);
		
		// 업로드된 파일이 저장될 폴더 설정
		String uploadPath = "D:/D_Other/uploadFiles";

		// 저장될 폴더가 없으면 새로 만든다.
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		// 다운 받을 파일의 정보를 갖는 File객체 생성 -> 실제 저장된 파일명은 지정한다.
		File dowFile = new File(f, fvo.getSave_file_name());
		
		if (dowFile.exists()) { // 파일이 있을 때만 작업해야한다.
			// contentType 설정
			//response.setContentType("text/html; charset=UTF-8"); // html
			//response.setContentType("application/json; charset=UTF-8"); // json
			response.setContentType("application/octet-stream; charset=UTF-8"); // 파일
			
			// Response 객체에 'content-disposition'헤더 속성을 추가한다.
			String headerKey = "content-disposition";
			
			// 위 헤더속성의 value값에는 클라이언트가 다운받아서 저장할 파일명을 지정한다.
			// -> 업로드할 때의 원본 파일명을 지정해준다.
			//String headerValue = "attachment; filename=\"" + fvo.getOrigin_file_name() + "\"";
			String headerValue = "attachment; " + getEncodedFileName(request, fvo.getOrigin_file_name());
			
			response.setHeader(headerKey, headerValue);
			
			// 서버쪽의 파일을 읽어서 클라이언트로 전송한다.
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			
			try {
				// 출력용 스트림 객체 생성 -> response 객체 이용
				bout = new BufferedOutputStream(response.getOutputStream());
				
				// 파일 입력용 스트림 객체 생성
				bin = new BufferedInputStream(new FileInputStream(dowFile));
				
				byte[] temp = new byte[1024];
				int len = 0;
				while ((len = bin.read(temp)) > 0) {
					bout.write(temp, 0, len);
				}
				bout.flush();
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("입출력 오류 : " + e.getMessage());
			} finally {
				if(bout != null) try { bout.close(); }catch (IOException e) {}
				if(bin != null) try { bin.close(); }catch (IOException e) {}
			}
			
		}else {
			//파일이 없을 때
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println("<h3>" + fvo.getOrigin_file_name() + "파일이 존재하지 않습니다...</h3>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	// 다운로드 파일명에 한글이 포함되었을 때 한글이 깨지는 것을 방지하는 메서드
	private String getEncodedFileName(HttpServletRequest request, String filename) {
		String encodedFilename = "";
		
		String userAgent = request.getHeader("user-agent");
		
		try {
			// MSIE 10 버전 이하의 웹브라우저 찾기
			if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
				encodedFilename = "filename=\"" + URLEncoder.encode(filename, "UTF-8").replace("\\+", "\\ ") + "\"";
			} else {
				encodedFilename = "filename*=UTF-8''" + URLEncoder.encode(filename, "UTF-8").replace("\\+", "%20");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("지원하지 않는 웹브라우저입니다...");
		}
		
		return encodedFilename;
	}

}
