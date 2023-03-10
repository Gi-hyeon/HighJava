package kr.or.ddit.basic;

// yield()메서드 연습용 예제

public class ThreadTest10 {

	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번 쓰레드");
		YieldThread th2 = new YieldThread("2번 쓰레드");
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		System.out.println("111111111111111111111111111111111111111111111111111111111111111111");
		th1.work = false;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		System.out.println("222222222222222222222222222222222222222222222222222222222222222222");
		th1.work = true;
		
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		System.out.println("333333333333333333333333333333333333333333333333333333333333333333");
		th1.stop = true;
		th2.stop = true;
	}
}

// yield() 연습용 쓰레디
// 아무것도 안할 때 양보할 수...?
class YieldThread extends Thread{
	public boolean stop = false;
	public boolean work = true;
	
	public YieldThread(String name) {
		super(name); // 쓰레드 이름 설정
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!stop) { // stop이 true가되면 반복문이 종료된다.
			if (work) {
				System.out.println(getName() + " 작업 중...");
			} else {
				System.out.println(getName() + " 양보...");
				Thread.yield();
			}
		}
	}
}