package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class LottoTest {
	public static void main(String[] args) {
		LottoTest lottoTest = new LottoTest();
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		Set<Integer> set = new HashSet<>();
		
		while (true) {
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 :");
			int num = Integer.parseInt(scanner.nextLine());
			switch (num) {
			case 1:
				lottoTest.Lotto(set, scanner, random);
				continue;
			case 2:
				System.out.println("감사합니다");
				break;
			default:
				continue;
			}
			break;
		}
		scanner.close();
	}
	
	
	public void Lotto(Set<Integer> set, Scanner scanner, Random random) {
		System.out.println("Lotto 구입 시작");
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		int money = Integer.parseInt(scanner.nextLine());
		
		if(money>1000 && money<=100000) {
			System.out.printf("받은 금액은 %d원이고 거스름돈은 %d원입니다.\n", money, money%1000);
			for (int i = 0; i < money/1000; i++) {
				for (int j = 0; j < 6 ; j++) {
					set.add(random.nextInt(45) + 1);
				}
				System.out.printf("로또번호%d : ", i+1);
				for (Integer integer : set) {
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
