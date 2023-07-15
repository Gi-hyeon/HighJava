package test.java03;

import java.io.File;
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

public class Test02 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		// 이 파일 경로 test.java03.Test02
		// D:\A_TeachingMaterial\webpro\test.java\src\test\java03\Test02.java
		
		// 현재 경로 찍기
		System.out.println(new File("").getAbsolutePath());
		
		File file = new File("src\\test\\java03\\Test02.java");
		System.out.println(file.exists());
		System.out.println(file.isFile());
		System.out.println(file.length());
		System.out.println(file.getName());
		System.out.println(file.getAbsolutePath());		// 절대 경로
		
		
		
		
	}
}
