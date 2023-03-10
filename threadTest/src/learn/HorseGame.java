package learn;

import java.util.Arrays;
import java.util.Random;

public class HorseGame {
	
	public static void main(String[] args) {
		Horse1[] horseArr = new Horse1[] {
			new Horse1("01번말"), new Horse1("02번말"),
			new Horse1("03번말"), new Horse1("04번말"),
			new Horse1("05번말"), new Horse1("06번말"),
			new Horse1("07번말"), new Horse1("08번말"),
			new Horse1("09번말"), new Horse1("10번말")
		};
		for (Horse1 horse1 : horseArr) {
			horse1.start();
		}
		
//		new GameState(horseArr).start(); // 말들의 현재 위치를 출력하는 쓰레드 시작
		GameState gameState = new GameState(horseArr);
		gameState.start();
		
		for (Horse1 horse1 : horseArr) {
			try {
				horse1.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
		
		try {
			gameState.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		System.out.println();
		System.out.println("경기 끝...");
		System.out.println();
		
		Arrays.sort(horseArr);
		
		for (Horse1 horse1 : horseArr) {
			System.out.println(horse1);
		}
		
	}
	
}

class Horse1 extends Thread implements Comparable<Horse1>{  //쓰레드 getter setter 충돌
	public static int currentRank = 0;  //말의 등수를 구할 때 사용할 변수
	private String horseName;  // 말이름
	private int rank;  // 등수
	private int location;  // 현재 위치
	
	public Horse1(String horseName) {
		this.horseName = horseName;
	}
	
	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return String.format("경주마 %s는(은) %d등 입니다.", horseName, rank);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random random = new Random();
		for (int i = 1; i <= 50; i++) {
			this.location = i;  // 말의 현재위치 저장
			try {
				Thread.sleep(random.nextInt(300));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
		currentRank++;
		this.rank = currentRank;
	}
	
	// 한 마리의 말의 경주가 끝나면 현재의 등수를 구해서 저장한다. 

	@Override
	public int compareTo(Horse1 o) {
//		if(this.rank > o.rank) {
//			return 1;
//		} else {
//			return -1;
//		}
		return Integer.compare(this.rank, o.getRank());
	}
}

class GameState extends Thread{
	private Horse1[] horseArr; // 경제우 참가한 말들의 정보가 저장될 배열 변수 선언
	
	// 생성자
	public GameState(Horse1[] horseArr) {  // <- 생성자에서 받음
		super();
		this.horseArr = horseArr;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			// 모든 말의 경주가 끝났는지 여부 검사
			if(Horse1.currentRank == horseArr.length) {
				break;
			}
			
			for (int i = 1; i < 10; i++) {
				System.out.println();
			}
			
			for (int i = 0; i < horseArr.length; i++) {
				System.out.print(horseArr[i].getHorseName() + " : ");
				for (int j = 1; j <= 50; j++) {
					if (horseArr[i].getLocation() == j) {  // 말의 현재위치 찾기
						System.out.print("<");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
	}
}