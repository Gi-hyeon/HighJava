package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * 10마리의 말들이 경주하는 프로그램 작성하기
 * 
 * 말을 Horse라는 이름의 쓰레드 클래스로 작성하는데
 * 이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
 * 그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준을 갖고 있다.
 * (Comparable 인터페이스 구현)
 * 
 * 경기 구간은 1 ~ 50구간으로 되어 있다.
 * 경기가 끝나면 등수 순으로 출력한다.
 * 
 * 경기 중간 중간에 각 말들의 위치를 아래와 같이 출력한다.
 * 예시)
 * 말이름01 : --->---------------------------------------------
 * 말이름02 : ---------------------->--------------------------
 * 말이름03 : ->-----------------------------------------------
 * ...
 * 말이름10 : ------------------------------>------------------
 */

public class ThreadTest13 {
	public static Horse[] horse = new Horse[]{  };
	public static List<Horse> horseList;

	public static void main(String[] args) {
		horseList = new ArrayList<>();
		horseList.add(new Horse("말01"));
		horseList.add(new Horse("말02"));
		horseList.add(new Horse("말03"));
		horseList.add(new Horse("말04"));
		horseList.add(new Horse("말05"));
		horseList.add(new Horse("말06"));
		horseList.add(new Horse("말07"));
		horseList.add(new Horse("말08"));
		horseList.add(new Horse("말09"));
		horseList.add(new Horse("말10"));
		
		for (Horse horse : horseList) {
			horse.start();
		}
		
		new Location().start();
		
		for (Horse horse : horseList) {
			System.out.println(horse.name);
			try {
				horse.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}	
		
		Collections.sort(horseList);
		System.out.println("결과");
		for (Horse horse : horseList) {
			System.out.println(horse.getRank() + "등 -> " + horse.name());
			System.out.println();
		}
	}
}

class Horse extends Thread implements Comparable<Horse>{
	public static int rankp = 1;
	public int rank;
	public String name;
	public int location;

	public Horse(String name) {
		this.name = name;
	}
	
	public String name() {
		return this.name;
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
	public void run() {
		// TODO Auto-generated method stub
		Random random = new Random();
		// 1 ~ 50까지 구간의 말 위치 증가
		for (int i = 0; i <= 50; i++) {
			location++;
			try {
				Thread.sleep(random.nextInt(200));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
		if(getLocation() > 50) {
			rank = rankp++;
			System.out.println(name + "도착");
		}
	}
	
	@Override
	public int compareTo(Horse o) {
		if (this.rank > o.rank) {
			return 1;
		} else {
			return -1;
		}
	}
}

class Location extends Thread{
	public static boolean run = true; 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (Horse.rankp <=10) {
			List<Horse> list = ThreadTest13.horseList;
			for (int i = 0; i < 10; i++) {
				Horse horse = list.get(i);
				System.out.print(horse.name());
				for(int j = 1; j <=50; j++) {
					if(horse.location == j) {
						System.out.print("<");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}			
		}
		
	}
}