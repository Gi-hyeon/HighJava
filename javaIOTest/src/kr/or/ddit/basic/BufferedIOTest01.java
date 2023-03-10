package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {
	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered 스트림을 사용한다.
		FileOutputStream fout = null;
		BufferedOutputStream bout = null;
		
		try {
			fout = new FileOutputStream("d:/d_other/bufferTest.txt");
			
			// 버퍼의 크기가 5인 버퍼스트림 객체 생성
			bout = new BufferedOutputStream(fout, 5);
			
			for (char ch = '1'; ch <= '9'; ch++) {
				bout.write(ch);
			}
			// 출력 작업이 끝나면 출력 버퍼에 남아있는 데이터를 강제로 모두 출력시켜줘야한다.
//			bout.flush(); 
			
			System.out.println("작업 끝...");
			
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			// 보조 스트림을 닫으면 보조 스트림에서 사용한 기반이 되는 스트림도 같이 닫힌다.
//			if (fout != null) {try {fout.close();} catch (IOException e) {e.printStackTrace();}
//			}
			
			// close안에 flush 기능이 있어서 flush()를 안적어도 다 출력하지만,
			// 안전때문에 flush 붙여주는 게 좋다.
			if (bout != null) {try {bout.close();} catch (IOException e) {e.printStackTrace();}
			}
		}
	}
}
