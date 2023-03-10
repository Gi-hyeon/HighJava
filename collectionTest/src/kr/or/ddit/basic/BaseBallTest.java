package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/*
 * 문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 * 		 (컴퓨터의 숫자는 난수를 이용하여 구한다. (1~9사이의 값 3개))
 * 		 (스트라이크는 S, 볼은 B로 나타낸다.)
 * 
 * 예시)
 * 		컴퓨터 난수 -> 9, 5, 7
 * 
 *  실행예시)
 *  	숫자입력 > 3 5 6  //값도 같고 위치도 같으면 스트라이크
 *  	3 5 6 -> 1S 0B
 *  	숫자입력 > 7 8 9  // 위치만 다르고 2게 맞음 볼
 *  	7 8 9 -> 0S 2B  
 *  	숫자입력 > 9 7 5
 *  	9 7 5 -> 1S 2B
 *  	숫자입력 > 9 5 7  // 3S가 나오면 결과 나옴
 *  	9 5 7 -> 3S 0B   
 *  
 *  	축하합니다...
 *  	당신은 4번만에 맞췄습니다...
 */

public class BaseBallTest {
	private ArrayList<Integer> numList;		// 난수가 저장될 리스트
	private ArrayList<Integer> userList;	// 사용자가 입력한 값이 저장될 리스트
	
	private int strike;
	private int ball;
	
	Scanner scanner = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		new BaseBallTest().gameStart();
	}
	
	public void gameStart() {
		System.out.println("********************************");
		System.out.println("	숫 자 야 구 게 임 시 작 	");
		System.out.println("********************************");
		System.out.println();
		System.out.println("1 ~ 9 사이의 서로 다른 숫자 3개를 입력하세요...");
		// 난수를 만드는 메서드 호출
		createNum();
		
		int count = 0;		// 정답 카운트용
		// 만들어진 난수 확인하기 -> 필요없는 작업, 
//		System.out.println("만들어진 난수" + numList);
		
		do {
			count++;
			
			inputData();	// 입력용 메서드 호출
			
			ballCount();
			
		} while (strike != 3);
		
		System.out.println("정답입니다.");
		System.out.println("당신은" + count + "회 만에 맞췄습니다...");
	}
	
	// 1~9 사이의 서로 다른 난수 3개를 만들어서 리스트에 저장하는 메서드
	// (Set객체 이용)
	
	public void createNum() {
		Random random = new Random();
		Set<Integer> set = new HashSet<>();
		
		// 1~9 사이의 난수 3개 만들기
		while (set.size() < 3) {
			set.add(random.nextInt(9) + 1);
		}
		
		// 만들어진 난수를 List에 저장한다.
		numList = new ArrayList<>(set);
		
		// List의 데이터들을 섞어준다.
		Collections.shuffle(numList);
	} // createNum()메서드 끝...
	
	// 사용자로부터 3개의 정수를 입력 받아 List에 추가하는 메서드
	// (단, 입력한 값들은 중복되지 않아야 한다.)
	public void inputData() {
		int num1, num2, num3;		// 입력한 값이 저장될 변수 선언
		
		do {
			System.out.print("숫자입력 >> ");
			num1 = scanner.nextInt();
			num2 = scanner.nextInt();
			num3 = scanner.nextInt();
			if (num1 == num2 || num1 == num3 || num2 == num3) { // 변수가 많아지면 -> 반복문 
				System.out.println("중복되는 값이 입력되었습니다. 다시 입력하세요...");
			}
		} while(num1 == num2 || num1 == num3 || num2 == num3);
		userList = new ArrayList<>();
		userList.add(num1);
		userList.add(num2);
		userList.add(num3);
	} //inputDate() 메서드 끝...
	
	public void ballCount() {
		strike = 0;
		ball = 0;		// 개수 초기화
		for (int i = 0; i < userList.size(); i++) {
			for (int j = 0; j < userList.size(); j++) {
				if(numList.get(i) == userList.get(j)) {  //값이 같은지 검사
					if (i == j) {
						strike++;
					}else {
						ball++;
					}
				}
			}
		}  //구해진 결과를 출력한다.
		System.out.println(userList.get(0) + ", " + userList.get(1) + ", " + userList.get(2) + 
							"->" + strike + "S " + ball + "B");
	} // ballCount()메서드 끝...
}
