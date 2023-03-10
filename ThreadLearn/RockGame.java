package learn;

import java.util.Random;

import javax.swing.JOptionPane;

public class RockGame {
	public static boolean inputCheck;
	
	public static void main(String[] args) {
		GameTimer gameTimer = new GameTimer();
		
		//난수를 이용하여 컴퓨터의 가위 바위 보 정하기
		String[] data = { "가위", "바위", "보" };
		Random random = new Random();
		String computer = data[random.nextInt(3)]; // 컴퓨터의 가위 바위 보를 정한다.
		
		gameTimer.start(); // 카운트 다운 시작.
		// 사용자의 가위 바위 보 입력 받기.
//		String human = JOptionPane.showInputDialog("가위 바위 보를 입력하세요...");
		String human = null;
		
		do {
			human = JOptionPane.showInputDialog("가위 바위 보를 입력하세요...");
//		} while (!("가위".equals(human) || "바위".equals(human) || "보".equals(human)));
		} while (!"가위".equals(human) && !"바위".equals(human) && !"보".equals(human));
		
		inputCheck = true;
		
		// 결과를 판정하여 출력하기
		String result = "";
//		if (human.equals(computer)) {
//			result = "비겼ㅅㅂ니다...";
//		} else if (human.equals("가위")&&computer.equals("보")||human.equals("바위")&&computer.equals("가위")||human.equals("보")&&computer.equals("바위")) {
//			result = "당신이 이겼습니다...";			
//		} else {
//			result = "당신이 졌습니다.";
//		}
		
		switch (human + computer) {
		case "가위가위":
		case "바위바위":
		case "보보": result = "비겼습니다"; break;
		case "가위보":
		case "바위가위":
		case "보바위": result = "당신이 이겼습니다."; break;
		default : result = "당신이 졌습니다.";
		}
		
		System.out.println("--- 결 과 --- ");
		System.out.println("컴퓨터 : " + computer);
		System.out.println("사용자 : " + human);
		System.out.println("결  과 : " + result);
	}
}

class GameTimer extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("카운트 다운 시작...");
		for (int i = 5; i >= 0; i--) {
			if (RockGame.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		} // for문 끝
		System.out.println("--- 결 과 ---");
		System.out.println("시간이 초과되어 당신이 졌습니다...");
		System.exit(1);
	}
}
