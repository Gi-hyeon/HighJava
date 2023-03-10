package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

public class FileIOTest03 {
	public static void main(String[] args) {
		// 문자 기반 스트림을 이용하여 파일 내용 읽어와서 출력하기
		FileReader fr = null;
		
		try {
			// 스트림 객체 생성
			fr = new FileReader("d:/d_other/test.txt");
			
			int c;
			
			while ((c=fr.read())!= -1) {
				System.out.print((char)c); // 그냥 출력하면 정수가 출력 -> (char)
				
			}
			
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(fr != null){
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
