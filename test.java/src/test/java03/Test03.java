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
 * 
 */
public class Test03 {
	public static void main(String[] args) throws Exception {
		
		String path = "src/test/java03/Test02.java";
		
		/*
		 * 읽기 절차 : 읽기 객체 생성 -> 읽기 작업 -> 종료 작업
		 */
		FileReader fileReader = new FileReader(new File(path));
		// new FileReader(path);
		
		while (true) {
			// read : 한글자에 대한 char 데이터
			int read = fileReader.read();	// 한 글자씩 읽어오기
			
			if (read == -1) {
				break;
			}
			
//			System.out.println(read);
			System.out.print((char)read);
			
			// close를 해야 나중에 다시 접근할 수 있음 close 안하면 Lock 걸어버림
			fileReader.close();
			
		}
		
		
	}
}

























