package kr.or.ddit.basic;

public class ThreadTest08 {
	public static void main(String[] args) {
//		new AutoSaveThread().start();
		AutoSaveThread auto = new AutoSaveThread();
		
		// 데몬 쓰레드로 설정하기 -> 반드시 start() 메서드를 호출하기 전에 설정해야 한다.
		
		auto.setDaemon(true);
		auto.start();
		try {
			for (int i = 1; i <= 20; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			} 
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("main 쓰레드 종료...");
	}
}

// 자동 저장하는 쓰레드( 3초에 한 번씩 자동 저장하는 쓰레드 )
class AutoSaveThread extends Thread{
	// 작업 내용을 저장하는 메서드
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
			save();
		}
	}
}