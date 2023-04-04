package kr.or.ddit.basic.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.basic.fileupload.service.FileinfoServiceImpl;
import kr.or.ddit.basic.fileupload.service.IFileinfoService;
import kr.or.ddit.vo.FileinfoVO;

/*
 * - Servlet 3.0이상에서 파일 업로드를 작성하는 서블릿에 @MultipartConfig 애노테이션을 설정애야한다.
 *   ㄴ 설정을 안하면 request 등 요청하여 파일을 받을 수 없다.
 * - @MultipartConfig의 설정 값들...
 * 	 1. location : 업로드한 파일이 임시적으로 저장될 경로를 지정한다. (기본값 : "")
 * 	 2. fileSizeThreshold : 이 곳에 지정한 값보다 큰 파일이 전송되면 location에 지정한 임시 디렉토리에 저장한다.
 * 		ㄴ 단위 : byte, 기본값 : 0(무조건 임시 디렉토리 사용)
 * 	 3. maxFileSize : 1개 파일의 최대 크기(단위 : byte, 기본값 : -1L(무제한))
 * 	 4. maxRequestSize : 서버로 전송되는 전체 Request데이터의 전체 최대 크기(단위 : byte, 기본값 : -1L(무제한))
 *  
 */
@WebServlet("/fileUpload.do")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30,
		maxRequestSize = 1024 * 1024 * 100
		)
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// 파일 입력 폼을 보여주는 View페이지로 이동
		request.getRequestDispatcher("/basic/fileupload/fileUpload.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("파일 업로드 post 방식...");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// 업로드된 파일이 저장될 폴더 설정
		String uploadPath = "D:/D_Other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 만든다.
		File f = new File(uploadPath);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		// 파일이 아닌 일반 데이터 getParameter()메서드나 getParameterValues()메서드를 이용해서 구한다.
		String userName = request.getParameter("username");
		
		System.out.println("일반 파라미터 데이터 : " + userName);
		
		//----------------------------------------------------------------------------
		// 수신 받을 파일 데이터 처리하기
		String fileName = "";	// 파일명이 저장될 변수 선언
		
		// 수신받은 파일이 여러개일 때 Upload한 파일 목록이 저장될 List객체 생성
		List<FileinfoVO> fileList = new ArrayList<>();
		
		/*
		 * - Servlet 3.0 이상에서 새롭게 추가된 Upload용 메서드
		 *   1. request.getParts() -> 전체 Part 객체를 Collection 객체에 담아서 반환한다.
		 *   2. request.getPart("part이름") -> 지정된 'part이름'을 가진 개별 Part객체를 반환한다.
		 *   		'part이름' -> <form>태그 안의 입력 요소의 name속성 값으로 구별한다.
		 */
		
		// 전체 Part객체 개수만큼 반복 처리하기
		for (Part part : request.getParts()) {
			fileName = extractFileName(part);
			
			// 찾은 파일명이 빈문자열("")이면 이것은 파일이 아닌 일반 파라미터 데이터라는 의미이다.
			
			
			if (!"".equals(fileName)) {	// 파일인지 검사
				// 1개의 Upload파일에 대한 정보를 저장하기 위한 VO객체 생성
				FileinfoVO fvo = new FileinfoVO();
				
				fvo.setFile_writer(userName); // 작성자를 VO 저장
				fvo.setOrigin_file_name(fileName);	// 실제 파일명을 VO에 저장
				
				// 실제 저장되는 파일 이름이 중복되는 것을 방지하기 위해서 UUID객체를 이용하여
				// 저장할 파일명을 만든다.
				String saveFileName = UUID.randomUUID().toString() + "_" + fileName;
				
				// 이 값을 VO에 저장 파일명으로 저장한다.
				fvo.setSave_file_name(saveFileName);
				
				// part.getSize() -> Upload된 파일의 크기를 반환한다. (단위 : byte)
				
				// 파일 크기를 구해서 KB단위로 변환해서 VO에 저장한다.
				fvo.setFile_size((long)Math.ceil(part.getSize() / 1024.0));
				
				// 수신 받은 파일 내용을 저장한다.
				try {
					// part.write() 메서드 -> Upload된 파일을 저장하는 메서드
					part.write(uploadPath + File.separator + saveFileName); // 파일 저장하기...
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				fileList.add(fvo);
			} // if문 끝
		} // for문 끝 
		
		// 반복문이 끝나면 List에 저장된 파일 정보를 DB에 insert한다.
		IFileinfoService service = FileinfoServiceImpl.getService();
		
		for (FileinfoVO fileinfoVO : fileList) {
			service.insertFileinfo(fileinfoVO);
		}
		
		// 저장이 완료된 후에는 파일 목록을 보여주는 페이지로 이동한다.
		response.sendRedirect(request.getContextPath() + "/fileList.do");
		
		
	} // doPost() 메서드 끝...
	//----------------------------------------------------------------------------
	/*
	 * - Part 구조
	 *   1. 파일이 아닌 일반 데이터일 경우
	 *   --------------------2348934893249824983259873532 -> Part를 구분하는 구분선
	 *   content-disposition : form-data; name="username" -> 파라미터명
	 *   				-> 빈줄
	 *   Honggildong	-> 파라미터 값
	 *   
	 *   2. 파일일 경우
	 *   --------------------2348934893249824983259873532 -> Part를 구분하는 구분선
	 *	 content-disposition : form-data; name="upFile1"; filename="test1.txt" -> 파일 정보
	 *	 content-type : text/plain;		-> 파일의 종류
	 *   				-> 빈줄
	 *	 abcd1234안녕	-> 파일의 내용
	 *
	 */
	
	// Part구조 안에서 파일명을 찾는 메서드
	private String extractFileName(Part part) {
		String fileName = "";	// 반환할 파일명이 저장될 변수
		String headerValue = part.getHeader("content-disposition"); // 헤더의 '키값'을 이용하여 값을 구한다.
		
		String[] items = headerValue.split(";");
		for (String item : items) {
			if (item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		
		return fileName;
	}
}

















