package test.java03;

import java.io.UnsupportedEncodingException;

public class Test01 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String data1 = "한글";
		String data2 = "12abc";
		
		// 한글 바이트 수
		
		{
			// UTF-8
			byte[] bytes = data1.getBytes("utf-8");
			System.out.println("utf-8  바이트 수 : " + bytes.length);
			String string = new String(bytes);
			System.out.println("바이트 문자열 변환 : " + string);
		}
		
		{
			// EUC-KR
			byte[] bytes = data1.getBytes("EUC-KR");
			System.out.println("euc-kr  바이트 수 : " +bytes.length);
			String string = new String(bytes);
			System.out.println("바이트 문자열 변환1 : " + string);
			String string2 = new String(bytes, "euc-kr");
			System.out.println("바이트 문자열 변환2 : " + string2);
		}
	}
}
