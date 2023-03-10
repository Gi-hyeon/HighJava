package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {
	public static void main(String[] args) throws IOException {
		// URLConnection -> 애플리케이션과 URL간의 통신 연결을 위한 클래스
		//				 -> URL 객체를 통해서 구할 수 있다.
		
		// 특정 서버의 정보와 파일 내용을 가져와 출력하는 예제
		URL url = new URL("https://www.ddit.or.kr/index.php");
		
		// URLConnection 객체 구하기
		URLConnection conn = url.openConnection();
		
		// Header 정보 가져오기                       ㄱ 전체 정보 한번에 가져오기
		Map<String, List<String>> headerMap =conn.getHeaderFields();
		
		// Header 정보 출력해보기
		for (String headerKey : headerMap.keySet()) {
			
			System.out.println(headerKey + "+" + headerMap.get(headerKey));
		}
		System.out.println("------------------------------------------------------------");
		
		// 해당 문서의 내용 가져오기 (index.php 문서의 내용 가져오기)
		
		// 방법1 -> URLConnection 객체를 이용하는 방법
		
		// 파일의 내용을 읽어오기 위한 Stream 객체 생성 (URLConnection 객체를 통해서 구할 수 있다.)
		InputStream in = conn.getInputStream();
		// 바이트 기반 스트림을 -> 문자열 기반 스트림으로 바꿔주기 위해 Reader 필요
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		// 버퍼리더를 사용하면 한줄씩 읽어올 수 있다.
		BufferedReader br = new BufferedReader(isr);
		
		// 자료를 읽어와 출력하기
		while (true) {
			String str = br.readLine(); // 한 줄씩 읽어오기
			if (str == null) {
				break;
			}
			System.out.println(str);
		}
		
		br.close(); // 스트림 닫기
		
		
		
		// 방법2 -> URL 객체의 openStream() 메서드를 이용하는 방법
		InputStream in2 = url.openStream(); // URL 객체를 이용하여 스트림 객체를 구한다.
		
		// 바이트 기반 스트림을 -> 문자열 기반 스트림으로 바꿔주기 위해 Reader 필요
		InputStreamReader isr2 = new InputStreamReader(in2, "UTF-8");
		// 버퍼리더를 사용하면 한줄씩 읽어올 수 있다.
		BufferedReader br2 = new BufferedReader(isr2);
		
		// 자료를 읽어와 출력하기
		while (true) {
			String str = br2.readLine(); // 한 줄씩 읽어오기
			if (str == null) {
				break;
			}
			System.out.println(str);
		}
		
		br2.close(); // 스트림 닫기
		
	}
}
