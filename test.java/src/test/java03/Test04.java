package test.java03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * 현재 작성하고 있는 파일을 읽어서 화면에 출력
 * - FileInputStream
 * - FileReader -> (o)
 *  
 *  char[] ch = new char[5];
 *  
 *  int read = filReader.read(ch)
 *  ch : 12345 --> 5
 *  ch : 67891 --> 5
 *  ch : 23456 --> 5
 *  
 *  
 * 
 */
public class Test04 {
	public static void main(String[] args) throws Exception {
		
		String path = "src/test/java03/Test02.java";
		
		/*
		 * 읽기 절차 : 읽기 객체 생성 -> 읽기 작업 -> 종료 작업
		 */
		FileReader fileReader = new FileReader(new File(path));
		
		char[] ch = new char[1024];
		
		while (true) {
			// 배열이 없으면 하나씩 읽어온다, 배열이 있으면 뭉텅이를 전부 다 긁어옴
			
			// read: 읽힌 글자의 수
			// ch : 담은 데이터
			int read = fileReader.read(ch);	// char[] 읽어ㅗㅇ기
			if (read == -1) {break; }
			
			String str = new String(ch, 0, read);	// ch 배열 중 0부터 시작하여 read 숫자길이만큼 읽기
			System.out.println(str);
		}
		
		
	}
}

























