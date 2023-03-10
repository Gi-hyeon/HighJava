package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 문제) 'd:/d_other'폴더에 있는 '펭귄.jpg'파일을
 * 		 'd:/d_other/연습용' 폴더에 '복사본_펭귄.jpg'파일로 복사하는 프로그램을 작성하시오
 * 
 */

public class FileCopy {
	public static void main(String[] args) {
		File file = new File("D:/D_Other/펭귄.jpg");
		File outFile = new File("D:/D_Other/연습용/복사본_펭귄.jpg");
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		
		if (!file.exists()) { // 원본 파일이 존재하지 않으면...
			System.out.println("복사할 원본 파일 " + file.getName() + "이(가) 없습니다.");
			System.out.println("복사 작업을 중지합니다.");
			return;
		}
		// Byte기반, String기반 스트림을 선택할 지 정해야함
		try {
			// 원본 파일을 처리할 스트림 객체 생성
			fis = new FileInputStream(file);
			bin = new BufferedInputStream(fis);
			
			// 대상 파일을 처리할 스트림 객체 생성
			fos = new FileOutputStream(outFile);
			bout = new BufferedOutputStream(fos);
			
			System.out.println("복사 시작...");
			
			int c; // 읽어올 데이터를 저장할 변수
			
//			while ((c = fis.read()) != -1) {
//				fos.write(c);
//			}
//			fos.flush(); 
			
			// 버퍼로 하게되면 속도가 굉장히 빨라진다?
			// 데이터의 양이 많거나, 네트워크 입출력할 때 버퍼기능이 필요하다.
			while ((c = bin.read()) != -1) {
				bout.write(c);
			}
			bout.flush(); 
			
			System.out.println("복사 완료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bin != null) {
				try { bin.close(); } catch (IOException e) { e.printStackTrace(); };
			}
			if (bout != null) {
				try { bin.close(); } catch (IOException e) { e.printStackTrace(); };
			}
//			try {
//				fis.close();
//				fos.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}
