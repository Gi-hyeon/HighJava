package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 문제) 5명의 사람 이름을 입력 받아 ArrayList에 저장한 후에
 * 		 이 ArrayList에 저장된 이름들 중에 '김'씨 성의 이름을 찾아 모두 출력하시오.
 * 		 (단, 입력은 Scanner객체를 이용한다.)
 */

public class ArrayListTest02 {
	public static void main(String[] args) {
		int o = 413%1000/100;
		System.out.println(o);
		
		Scanner scanner = new Scanner(System.in);
		
		List<String> list = new ArrayList<>();
		for(int i=0; i<5; i++) {
			System.out.print("사람" + (i+1) + ">");
			list.add(scanner.nextLine());
		}		
		    
		System.out.println("김씨 성을 가진 사람들...");
//		for (int i = 0; i < list.size(); i++) {
//			String str = list.get(i);
//			if(str.contains("김")) {
//				System.out.println(list.get(i));
//			}
//		}
		
		for (int i = 0; i < list.size(); i++) {
			char at = list.get(i).charAt(0);
			if(at == '김') {
				System.out.println(list.get(i));
			}
		}
		
//		for (int i = 0; i < list.size(); i++) {
//			String string = list.get(i).substring(0, 1);
//			if(string.equals("김")) {
//				System.out.println(list.get(i));
//			}
//		}
		
//		for (int i = 0; i < list.size(); i++) {
//			if(list.get(i).indexOf("김") == 0) {
//				System.out.println(list.get(i));
//			}
//		}
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).startsWith("김")) {
				System.out.println(list.get(i));
			}
		}
		
		scanner.close();
	}
}
