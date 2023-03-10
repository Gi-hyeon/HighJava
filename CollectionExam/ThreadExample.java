package kr.or.ddit.basicenumTest;

import java.util.ArrayList;
import java.util.List;

/*
 * extends Thread 
 * implements Runnable
 * 둘다 public void run()을 가지고 있다.
 */

//class exdwqf implements Runnable{
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}

public class ThreadExample extends Thread{
	int seq;
	
	public ThreadExample(int seq) {
		super();
		this.seq = seq;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public void run() {
		System.out.println(this.seq + " thread. start.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		System.out.println(this.seq + " thread end.");
	}
	
	public static void main(String[] args) {
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Thread t = new ThreadExample(i);
			t.start();
			threads.add(t);
		}
		
		for (int i = 0; i < threads.size(); i++) {
			Thread t = threads.get(i);
			try {
				t.join();
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println("main end.");
	}
}
