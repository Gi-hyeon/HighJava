package tea;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

public class BaseBallTestMethod {
	public static void main(String[] args) {
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		List<Integer> sList = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		while (set.size() < 3) {
			set.add(random.nextInt(9) + 1);
		}
		List<Integer> list = new ArrayList<>(set);
		
		System.out.println(list);
		
		int count = 0;
		int strike = 0;
		int ball = 0;
		
		while (true) {
			sList.clear();
			strike = 0;
			ball = 0;
			System.out.print("숫자입력> ");
			for (int i = 0; i < 3; i++) {
				sList.add(scanner.nextInt());
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (sList.get(i) == list.get(j) && i == j) {
						strike++;
					} else if (sList.get(i) == list.get(j)) {
						ball++;
					}
				}
			}
			System.out.printf("%dS, %dB\n", strike, ball);
			count++;
			
			if(strike == 3) {
				System.out.println("축하합니다...");
				System.out.printf("당신은 %d번만에 맞췄습니다...", count);
				break;
			}
		}
	}
}
