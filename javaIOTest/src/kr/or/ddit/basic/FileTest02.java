package kr.or.ddit.basic;

import java.io.File;

public class FileTest02 {
	public static void main(String[] args) {
		File f1 = new File("d:/d_other/test.txt");
		System.out.println(f1.getName() + "의 크기 : " + f1.length() + "byte(s)");
		System.out.println("path : " + f1.getPath());
		System.out.println("absolutePath : " + f1.getAbsolutePath());
		System.out.println();
		
		// 현재 위치 나타내기
		File f2 = new File("."); //.찍어도 안찍어도 상관 없음
//		File f2 = new File("");
		System.out.println("path : " + f2.getPath());
		System.out.println("absolutePath : " + f2.getAbsolutePath());
		System.out.println();
		
		if (f1.isFile()) {
			System.out.println(f1.getName() + "은 파일입니다.");
		} else if (f1.isDirectory()) {
			System.out.println(f1.getName() + "은 디렉토리입니다.");
		} else {
			System.out.println(f1.getName() + "은 뭘까요???");
		}
		
		System.out.println();
		if (f2.isFile()) { // .은 디렉토리로 잘 인식하고, 공백을 입력했을 때 ?
			System.out.println(f2.getName() + "은 파일입니다.");
		} else if (f2.isDirectory()) {
			System.out.println(f2.getName() + "은 디렉토리입니다.");
		} else {
			System.out.println(f2.getName() + "은 뭘까요???");
		}
		System.out.println();
		
		File f3 = new File("d:/d_other/sample.exe"); // 현재 존재하지 않는 파일 지정
		if (f3.isFile()) { // 결정되지 않은 데이터이기 때문에 마지막이 실행된다.
			System.out.println(f3.getName() + "은 파일입니다.");
		} else if (f3.isDirectory()) {
			System.out.println(f3.getName() + "은 디렉토리입니다.");
		} else {
			System.out.println(f3.getName() + "은 뭘까요???");
		}
		System.out.println();
		
		
	}
}
