package test.java02;

import test.java01.ParamMap;

public class Test01 {
	public static void main(String[] args) {
		ParamMap init = ParamMap.init();
		init.put("key1", "value1");
//		init.put(123, 456);
		
		String val = init.getString("key1");
		
		StringBuffer sb = new StringBuffer();
		sb.append("?????????????????????????????????????????????????????????????");
		init.put("key3", sb);
		
		StringBuffer stringBuffer = init.get("key3", StringBuffer.class);
		System.out.println(stringBuffer);
		
		ParamMap p1 = ParamMap.init();
		p1.put("test1", "test2");
		init.put("key4", p1);
		ParamMap p11 = init.get("key4", ParamMap.class);
		System.out.println(p11);
		
	
	}
}
