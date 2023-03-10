package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
 * Vector, Hashtable 등의 예전부터 존재하던 Collection 객체들은
 * 내부에 동기화 처리가 되어있다.
 * 
 * 그런데 최근에 새로 구성된 Collection들은 동기화 처리가 되어있지 않다.
 * 그래서 동기화가 필요한 프로그램에서 이런 Collection 객체들은 사용하려면
 * 동기화 처리를 한 후에 사용해야 한다.
 */

public class ThreadTest17 {
	private static Vector<Integer> vec = new Vector<>(); // 동기화 처리가 되어있어 결과가 잘나옴
	
	// 동기화 처리가 되어 있지 않은 List
	private static List<Integer> list1 = new ArrayList<>();
	
	// 동기화 처리 한 List -> map, set 동일
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());
	
	
	public static void main(String[] args) {
		// 익명 구현체로 쓰레드 구현
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10000; i++) {
//					vec.add(i);
//					list1.add(i);
					list2.add(i);
				}
			}
		};
		// --------------------------------------
		Thread[] thArr = new Thread[] {
			new Thread(r),	new Thread(r), new Thread(r), new Thread(r), new Thread(r)
		};
		
		for (Thread thread : thArr) {
			thread.start();
		}
		
		for (Thread thread : thArr) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
		 
//		System.out.println("vec의 개수 : " + vec.size());
//		System.out.println("list1의 개수 : " + list1.size());
		System.out.println("list2의 개수 : " + list2.size());
	}
}
