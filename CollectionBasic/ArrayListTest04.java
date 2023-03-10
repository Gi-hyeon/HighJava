package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 *  문제) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중 별명의 길이가 
 *       제일 긴 별명을 출력하시오.
 *       (단, 각 별명의 길이가 같을 수 있다.) 여러개가 나올 수 있음       
 */
public class ArrayListTest04 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int max = Integer.MIN_VALUE;

		List<String> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			System.out.print("사람" + (i + 1) + "> ");
			list.add(scanner.nextLine());
		}
		
		for (int i = 0; i < list.size(); i++) {
			if(max <= list.get(i).length()) {
				max = list.get(i).length();
			}
		}
		
		System.out.println("가장 길 별명을 가진 사람들");
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).length() == max) {
				System.out.println(list.get(i));
			}
		}
		scanner.close();
	}
}
