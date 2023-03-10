package kr.or.ddit.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest03 {
	
	public static void main(String[] args) {
		 FileTest03 test = new FileTest03();
//		 File file = new File("c:/Program Files (x86)");
		 File file = new File("C:/Users/PC-08/Desktop");
		 test.dir(file);
	}
	
	// 디렉토리 정보를 매개변수로 받아서 해당 디렉토리에 있는
	// 모든 파일 및 디렉토리 목록을 출력하는 메서드
	public void dir(File d) {
		if (!d.isDirectory()) { // Directory가 아니면 이 작업이 필요가 없다.
			System.out.println("디렉토리(폴더)만 가능합니다...");
			return;
		}
		
		System.out.println("[" + d.getAbsolutePath() + "] 디렉토리 내용");
		
		// 해당 디렉토리 안에 있는 모든 파일 및 데릭토리 목록을 구한다.
//		String[] filesStr = d.list(); -> 가능하지만 String 배열이라 file 또 객체로 만들어야 한다.
		File[] files = d.listFiles();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a HH:mm");
		
		// 가져온 파일과 디렉토리 목록 개수만큼 반복 처리
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();
			String attr = ""; // 파일의 속성 (읽기, 쓰기, 히든, 디렉토리 구분)
			String size = ""; // 파일의 크기
			
			if (files[i].isDirectory()) {
				attr = "<DIR>";
			} else {
				size = files[i].length() + ""; // <- 문자열로 바꿔주기 위한것
				attr = files[i].canRead() ? "R" : "";
				attr += files[i].canWrite() ? "W" : "";
				attr += files[i].isHidden() ? "H" : ""; // 파일 숨김 속성이면 true, 아니면 false
			}
			
			// lastModified <- 가장 최근에 수정한 날짜를 알려줌
			String strDate = df.format(new Date(files[i].lastModified()));
			System.out.printf("%s %5s %12s %s\n", strDate, attr, size, fileName);
		}
		
	}
}
