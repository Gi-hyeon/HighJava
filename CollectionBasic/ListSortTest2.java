package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSortTest2 {
	public static void main(String[] args) {
		List<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));
		
		System.out.println("정렬전...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-----------------------------------------------");
		
		//String은 내부절렬이 있지만 <Member>는 정렬이 없기때문에 내부 정렬을 만들거나 외부 정렬기준을 만들어야함
		Collections.sort(memList);  
		System.out.println("정렬후...");
		for (Member mem : memList) {
			System.out.println(mem);
		}
		
		System.out.println("-----------------------------------------------");
		
		// 회원 번호를 기준으로 내림차순 정렬하여 출력하시오.
		// -> 외부 정렬 기준 클래스를 작성하여 처리한다. (클래스 이름 : SortNumDesc)
		System.out.println("넘버로 정렬 후...");
		Collections.sort(memList, new SortNumDesc());
		for (Member member : memList) {
			System.out.println(member);
		}
	}
}

// Member 클래스의 '회원이름'을 기준으로 오름차순이 되도록 내부 정렬 기준을 추가하기
// -> Comparable 인터페이스를 구현한다.
class Member implements Comparable<Member>{
	private int num;		// 회원번호
	private String name;	// 회원이름
	private String tel;		// 전화번호
	
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return String.format("Member [num=%s, name=%s, tel=%s]", num, name, tel);
	}


//	@Override
//	public int compareTo(Member mem) {
//		// 회원이름의 오름차순
//		// String의 기본 타입은 오름차순이기 때문이기 때문에 그대로 반환해주면 된다.
//		return this.getName().compareTo(mem.getName());
//	}
	
//	내림차순으로 정렬해보자.
	@Override
	public int compareTo(Member mem) {
		// 회원이름의 오름차순
		// String의 기본 타입은 오름차순이기 때문이기 때문에 그대로 반환해주면 된다.
		return mem.getName().compareTo(this.getName());
	}
}

// Member의 회원번호(num)의 내림차순으로 정렬하는 외부 정렬 기준 클래스
class SortNumDesc implements Comparator<Member>{
	
	@Override
	public int compare(Member o1, Member o2) {
//		if(o1.getNum() > o2.getNum()) {
//			return 1;
//		} else if (o1.getNum() < o2.getNum()) {
//			return -1;
//		} else {
//			return 0;			
//		}
	
//		정수형 같은 경우 if문을 않쓰고 하는 방법
//		여기에 들어가는 값이 양수일 때만 가능하다. 
//		Wrapper 클래스를 사용하는 경우가 더 많다.
//		return o2.getNum() - o1.getNum();
		
		// Wrapper 클래스를 이용하는 방법1
//		return new Integer(o1.getNum()).compareTo(o2.getNum()) * -1;
//		return new Integer(o1.getNum()).compareTo(o2.getNum()) * 1;
//		return new Integer(o1.getNum()).compareTo(o2.getNum()) * 0;
		
		// Wrapper 클래스를 이용하는 방법2
		return Integer.compare(o1.getNum(), o2.getNum()) * -1;
	}
	
}
