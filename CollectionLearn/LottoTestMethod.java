package tea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class LottoTestMethod {
	private Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		new LottoTestMethod().lottoStart();
	}
	
	public void lottoStart() {
		while (true) {
			int selectNum = displayMenu();
			switch (selectNum) {
			case 1:
				buyLotto();
				break;
			case 2:
				System.out.println("감사합니다.");
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다.");
				System.out.println("1 또는 2를 입력하세요...");
				break;
			}
		}
	}
	

	// 메뉴를 출력하고 사용자가 입력한 값을 반환하는 메서드
	private int displayMenu() {
		System.out.println("==========================");
		System.out.println("Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴선택 >");
		
		return Integer.parseInt(scanner.nextLine());
	}
	
	// 로또 판매를 처리하는 메서드
	private void buyLotto() {
		// 로또 번호 만들기
		Set<Integer> set = new HashSet<>();
		Random random = new Random();
		
		System.out.println();
		System.out.println("Lotto 구입 시작");
		System.out.println();
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		int money = Integer.parseInt(scanner.nextLine());
		
		if(money>1000 && money<=100000) {
			System.out.printf("받은 금액은 %d원이고 거스름돈은 %d원입니다.\n", money, money%1000);
			for (int i = 0; i < money/1000; i++) {
				for (int j = 0; j < 6 ; j++) {
					set.add(random.nextInt(45) + 1);
				}
				
				List<Integer> list = new ArrayList<>(set);
				Collections.sort(list);
				
				System.out.printf("로또번호%d : ", i+1);
				for (Integer integer : list) {
					System.out.printf("%d ", integer);
				}
				System.out.println();
				
				set.clear();
			}
		} else if (money < 1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
		} else {
			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
		}
	}
}
