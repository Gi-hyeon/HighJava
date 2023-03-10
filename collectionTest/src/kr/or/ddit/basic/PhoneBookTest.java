package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
 * 문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone 클래스를 만들고
 * 		 Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
 * 		 - Map의 구조 : key값은 입력한 '이름'으로 사용하고
 * 		 				value값은 Phone클래스의 인스턴스로 한다.
 * 		 예) HashMap<String, Phone> 변수명;
 * 	
 * 		 - 아래의 메뉴를 구현한다.
 * 		 1. 전화번호 등록
 * 		 2. 전화번호 수정
 * 		 3. 전화번호 삭제
 * 		 4. 전화번호 검색
 * 		 5. 전화번호 전체 출력
 * 		 0. 프로그램 종료
 * 		 -------------------------------
 * 		 - 삭제, 검색 기능은 '이름'을 입력 받아 처리한다.
 * 		 
 * 		 실행예시)
 * 		 -------------------------------
 * 		 1. 전화번호 등록
 * 		 2. 전화번호 수정
 * 		 3. 전화번호 삭제
 * 		 4. 전화번호 검색
 * 		 5. 전화번호 전체 출력
 * 		 0. 프로그램 종료
 * 		 -------------------------------
 * 		 번호입력 >> 1
 * 
 * 		 새롭게 등록할 전화번호 정보를 입력하세요.
 * 		 이 름 >> 홍길동
 * 		 전 화 번 호 >> 010-1111-1111
 * 		 주 소 >> 대전시 중구 오류동
 * 		 
 * 		 '홍길동'씨의 전화번호 정보가 등록되었습니다.
 * 
 * 		 -------------------------------
 * 		 1. 전화번호 등록
 * 		 2. 전화번호 수정
 * 		 3. 전화번호 삭제
 * 		 4. 전화번호 검색
 * 		 5. 전화번호 전체 출력
 * 		 0. 프로그램 종료
 * 		 -------------------------------
 * 		 번호입력 >> 1
 * 
 * 		 새롭게 등록할 전화번호 정보를 입력하세요.
 * 		 이 름 >> 홍길동
 * 
 * 		 '홍길동'은 이미 등록된 사람입니다.
 * 		 
 * 		 -------------------------------
 * 		 1. 전화번호 등록
 * 		 2. 전화번호 수정
 * 		 3. 전화번호 삭제
 * 		 4. 전화번호 검색
 * 		 5. 전화번호 전체 출력
 * 		 0. 프로그램 종료
 * 		 -------------------------------
 * 		 번호입력 >> 5
 * 		
 * 		 -------------------------------------------------
 * 		 번호	 이름	  전화번호	           주소
 * 		 -------------------------------------------------
 * 		  1	    홍길동	 010-1111-1111	대전시 중구 오류동	
 * 		  ~~
 * 		  ~~
 * 		 -------------------------------------------------
 * 		 출력 완료...	
 * 
 * 		 -------------------------------
 * 		 1. 전화번호 등록
 * 		 2. 전화번호 수정
 * 		 3. 전화번호 삭제
 * 		 4. 전화번호 검색
 * 		 5. 전화번호 전체 출력
 * 		 0. 프로그램 종료
 * 		 -------------------------------
 * 		 번호입력 >> 0
 * 
 * 		 프로그램을 종료합니다...
 */

public class PhoneBookTest {
	Scanner scanner = new Scanner(System.in);
	Map<String, Object> map = new HashMap<>();	
	
	public static void main(String[] args) {
		new PhoneBookTest().PhoneBookStart();
	}
	
	public void PhoneBookStart() {
		while (true) {
			int selectNum = disPlay();
			switch (selectNum) {
			case 1:
				signUp();
				break;
			case 2:
				edit();
				break;
			case 3:
				delete();
				break;
			case 4:
				search();
				break;
			case 5:
				print();
				break;
			case 0:
				System.out.println("감사합니다...");
				return;
			default:
				System.out.println("올바른 번호를 입력하세요...");
				break;
			}
		}
	}
	


	public int disPlay() {
		System.out.println("--------------------------------------");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("--------------------------------------");
		return Integer.parseInt(scanner.nextLine());
	}
	
	private void signUp() {
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 > ");
		String name = scanner.nextLine();
		System.out.print("전 화 번 호 > ");
		String tel = scanner.nextLine();
		System.out.print("주 소 > ");
		String addr = scanner.nextLine();
		
		if(!(map.containsKey(name))) {
			map.put(name, new Phone(tel, addr));
			System.out.printf("'%s'씨의 전화번호 정보가 등록되었습니다.\n", name);
		} else {
			System.out.printf("'%s'은 이미 등록된 사람입니다.\n", name);
		}
	}
	
	private void edit() {
		System.out.print("수정할 사람의 이름을 입력하세요 > ");
		String name = scanner.nextLine();
		if (!(map.containsKey(name))) {
			System.out.printf("'%s'씨는 존재하지 않는 정보입니다.\n", name);
		} else {
			System.out.print("전 화 번 호 > ");
			String tel = scanner.nextLine();
			System.out.print("주 소 > ");
			String addr = scanner.nextLine();
			map.put(name, new Phone(tel, addr));
			System.out.println("정상적으로 수정되었습니다.");
		}
	}
	
	private void delete() {
		System.out.print("삭제할 사람의 이름을 입력하세요 > ");
		String name = scanner.nextLine();
		if (!(map.containsKey(name))) {
			System.out.printf("'%s'씨는 존재하지 않는 정보입니다.\n", name);
		} else {
			map.remove(name);
			System.out.printf("'%s'씨의 전화번호 정보가 정상적으로 삭제되었습니다.\n", name);	
		}
	}
	
	private void search() {
		System.out.print("검색할 사람의 이릅을 입력하세요 > ");
		String name = scanner.nextLine();
		if (!(map.containsKey(name))) {
			System.out.printf("'%s'씨는 존재하지 않는 정보입니다.\n", name);
		} else {
			System.out.println("--------------------------------------");
			System.out.println("이름         전화번호        	주소 ");
			System.out.println("--------------------------------------");
			System.out.printf("%s       %s", name, map.get(name));
			System.out.println("출력완료...");
		}
	}
	
	private void print() {
		Set<String> keySet = map.keySet();
		int count = 0;
		System.out.println("-----------------------------------------");
		System.out.println("번호  이름         전화번호          주소 ");
		System.out.println("-----------------------------------------");
		for (String key : keySet) {
			Object value = map.get(key);
			System.out.printf(" %d    %s      %s", ++count, key, value);
		}
		System.out.println("출력완료...");
	}
}

class Phone{
	private String tel;
	private String addr;
	
	public Phone(String tel, String addr) {
		super();
		this.tel = tel;
		this.addr = addr;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return String.format("%s      %s      \n", tel, addr);
	}
	
	
}
