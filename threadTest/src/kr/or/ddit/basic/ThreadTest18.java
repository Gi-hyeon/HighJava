package kr.or.ddit.basic;

/*
 * wait(), notify() 메서드를 이용한 두 쓰레드가 번갈아 한번씩 실행되는 예제
 * wait(), notify(), notifyAll() 메서드는 동기화 영역에서만 사용 가능하다.
 */

public class ThreadTest18 {
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
	}
}

// 공통으로 사용할 객체
class WorkObject{
	public synchronized void testMethodA() {
		System.out.println("testMethodA()메서드 실행중...");
		notify();
		
		try {
			wait(); //둘 다 처음에 같이 wait를 작성하게 된다면 waiting pool에 들어가게 된다.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public synchronized void testMethodB() {
		System.out.println("testMethodB()메서드 실행중...");
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}
}

// WorkObject의 testMethodA() 메서드만 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			workObj.testMethodA();
		}
		// 마지막에 waiting pool에 있는 쓰레드 깨워주기.
		// 동기화되서 실행되는 공통의 객체 WorkObject의 waiting pool이다.
		synchronized (workObj) {			
			workObj.notify();
		}
	}
}

// WorkObject의 testMethodB() 메서드만 호출하는 쓰레드
// waiting pool이 마지막에 남아있어서 종료가 되지 않는다. -> 두 곳에다가 조치를 해줘야함
class ThreadB extends Thread{
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			workObj.testMethodB();
		}
		// 마지막에 waiting pool에 있는 쓰레드 깨워주기.
//		workObj.notify();
		
		synchronized (workObj) {			
			workObj.notify();
		}
	}
}