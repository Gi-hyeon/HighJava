package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 문제) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중 별명의 길이가 
 *       제일 긴 별명을 출력하시오.
 *       (단, 각 별명의 길이는 모두 다르게 입력한다.) 가장 긴 별명 하나
 *       
 *  문제) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중 별명의 길이가 
 *       제일 긴 별명을 출력하시오.
 *       (단, 각 별명의 길이가 같을 수 있다.) 여러개가 나올 수 있음       
 */
public class ArrayListTest03 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
//		int max = 0;
//		String m = "";

		List<String> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			System.out.print("사람" + (i + 1) + "> ");
			list.add(scanner.nextLine());
		}
		
		String max = list.get(0);
				
		for (int i = 0; i < list.size(); i++) {
			if(max.length() < list.get(i).length()) {
				max = list.get(i);
			}
		}
		System.out.println("가장 긴 별명을 가진 사람 : " + max);
		
		scanner.close();
	}
}
