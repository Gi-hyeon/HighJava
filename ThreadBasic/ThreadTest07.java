package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터는 가위 바위 보는 난수를 이용해서 구하고
 * 사용자위 가위 바위 보는 showInputDialog() 메서드를 이용하여 입력 받는다.
 * 
 * 입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초 안에 입력이 없으면 게임에 진 것으로 처리한다.
 * 
 * 5초 안에 입력이 완료되면 승패를 구해서 출력한다.
 * 
 * 결과 예시)
 * 1) 5초안에 입력하지 못했을 경우
 * 	  --- 결 과 ---
 * 	  시간 초과로 당신이 졌습니다.
 * 2) 5초 안에 입력했을 경우
 * 	  --- 결 과 ---
 * 	  컴퓨터 : 가위
 * 	  사용자 : 바위
 *    결  과 : 당신이 이겼습니다.
 */

public class ThreadTest07 {
	public static void main(String[] args) {
		new game().start();
		new CountDoun1().start();
	}
}

class game extends Thread{
	public static boolean inputcheck;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random random = new Random();
		String gameArr[] = { "가위", "바위", "보" };
		String computer = gameArr[random.nextInt(3)];
//		System.out.println(computer);
		
		String human = JOptionPane.showInputDialog("사용자 입력 : ");
		inputcheck = true;
		System.out.println("--- 결 과 ---");
		System.out.println("컴퓨터 : " + computer);
		System.out.println("사용자 : " + human);
		if (human.equals(computer)) {
			System.out.println("비겼습니다.");
		} else if (human.equals("가위")&&computer.equals("보")||human.equals("바위")&&computer.equals("가위")||human.equals("보")&&computer.equals("바위")) {
			System.out.println("당신이 이겼습니다.");
		} else {
			System.out.println("당신이 졌습니다.");
		}
	}
}

class CountDoun1 extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 5;  i >= 0; i--) {
			if (game.inputcheck == true) {
				return;
			}
			System.out.println(i);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
		System.out.println("시간 초과로 당신이 졌습니다.");
		System.exit(1);
	}
}
