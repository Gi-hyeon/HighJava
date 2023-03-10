package kr.or.ddit.basic;

public class ThreadTest09 {
	
	public static void main(String[] args) {
		StatePrintThread th = new StatePrintThread(new TargetThread());
		th.start();
	}
}

// 쓰레드의 상태의 검사 대상이 되는 쓰레드
class TargetThread extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		double sum = 0.0;
		for (long i = 1L; i <= 2_000_000_000L; i++) {
			sum += i;
		}
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		for (long i = 1L; i <= 2_000_000_000L; i++) {
			sum += i;
		}
		
	}
}

// TargetThread의 상태를 검사해서 출력하는 쓰레드
class StatePrintThread extends Thread{
	private TargetThread target;  // target쓰레드의 정보가 필요하다.
	
	// 생성자
	public StatePrintThread(TargetThread target) {
		// TODO Auto-generated constructor stub
		this.target = target;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// 쓰레드의 상태값 구하기 -> getState() 메서드 이용 -> 
			// -> 현재 쓰레드의 상태값을 알 수 있다. -> 열거형 타입으로 나온다.
			Thread.State state = target.getState();
			System.out.println("TargetThread의 현재 상태값 : " + state);
			if (state == Thread.State.NEW) { // 쓰레드의 상태가 NEW상태이면... -> 실행안된상태
				target.start();
			}
			if (state == Thread.State.TERMINATED) { // 쓰레드의 상태가 종료상태이면...
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
	}
}
