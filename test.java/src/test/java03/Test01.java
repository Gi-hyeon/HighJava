package test.java03;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class Test01 {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		{
			Properties properties = System.getProperties();
			Set<Object> keySet = properties.keySet();
			// 기본으로 UTF-8 설정했다는 걸 확인할 수 있다.
			for (Object o : keySet) {
				Object object = properties.get(o);
				System.out.println(o + " : " + object);
			}
		}
		
		String data1 = "한글";
		String data2 = "12abc";
		
		// 한글 바이트 수
		
		{
			// UTF-8
			byte[] bytes2 = data1.getBytes();
			
			byte[] bytes = data1.getBytes("utf-8");
			System.out.println("utf-8  바이트 수 : " + bytes.length);
			String string = new String(bytes);
			System.out.println("바이트 문자열 변환 : " + string);
		}
		
		{
			// EUC-KR
			byte[] bytes = data1.getBytes("EUC-KR");
			System.out.println("euc-kr  바이트 수 : " +bytes.length);
			// String string = new String(bytes);
			String string = new String(bytes, "utf-8");
			System.out.println("바이트 문자열 변환1 : " + string);
			String string2 = new String(bytes, "euc-kr");
			System.out.println("바이트 문자열 변환2 : " + string2);
		}
	}
}
