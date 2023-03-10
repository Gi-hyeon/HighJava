package kr.or.ddit.basic;

public class ThreadTest02 {
	public static void main(String[] args) {// <- 멀티쓰레드를 사용할 때 메인메서드도 하나의 쓰레드이다.
		// 멀티 쓰레드 프로그램
		
		// Thread를 사용하는 방법
		
		// 방법1
		// Thread클래스를 상속한 class를 작성한 후 이 class의 인스턴스를 생성한 후
		// 이 인스턴의 start() 메서드를 호출해서 실행한다.
		Thread th1 = new MyThread1();
		th1.start();
//		th1.run(); // run이랑 메서드를 사용하면 *가 다 찍히고 멀티쓰레드가 실행된다.
		
		// 방법2-1
		// Runnable 인터페이스를 구현한 class를 작성한 후 이 class의 인스턴스를 생성한다.
		// 그리고 이 인스턴스를 Thread객체를 생성할 때 생성자의 인수값으로 넣어서 생성한다.
		// 이 때 생성된 Thread 객체의 start() 메서드를 호출해서 실행한다.
		MyRunner1 r1 = new MyRunner1();
		Thread th2 = new Thread(r1);
		th2.start();
		
		// 방법2-2
		// Runnable 인터페이스를 익명 구현체로 작성하여 처리하기
		Runnable r2 = new Runnable() {
			
			@Override
			public void run() {
				for (int i = 1; i <= 200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
//						e.printStackTrace();
					}
				}
			}
		};
		//---------------------------------------
		Thread th3 = new Thread(r2);
		th3.start();
 	}
}

// 방법1 -> Thread를 상속한 class 작성하기
class MyThread1 extends Thread{
	@Override
	public void run() {
		// 이 run() 메서드는 쓰레드가 처리할 내용을 기술하는 곳이다.
		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(시간)메서드 -> 주어진 시간동안 잠시 멈춘다.
				// 				시간은 밀리세컨드 단위를 사용한다.(즉, 1000은 1초를 의미한다.) 
				Thread.sleep(100);
			} catch (InterruptedException e) {
//				e.printStackTrace();
			}
		}
	}
}

// 방법2 -> Runnable 인터페이스를 구현한 class 작성하기
class MyRunner1 implements Runnable{
	@Override
	public void run() {
		// 쓰레드가 처리할 내용을 이 run()메서드를 기술한다.
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
//				e.printStackTrace();
			}
		}
	}
}