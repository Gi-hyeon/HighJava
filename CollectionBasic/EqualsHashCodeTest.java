package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EqualsHashCodeTest {
	public static void main(String[] args) {
		Person person1 = new Person();
		person1.setNum(1);
		person1.setName("홍길동");
		
		Person person2 = new Person();
//		person2.setNum(2);
//		person2.setName("일지매");
		person2.setNum(1);
		person2.setName("홍길동");
		
		//참조값이 같다는 뜻은 같은 위치에 있느난 뜻이다.
		Person person3 = person1;
		
		System.out.println(person1 == person2);
		//같은 이름을 가진 객체지만, 해당 객체가 생성된 주소가 다르기 때문에 False
		System.out.println(person1.equals(person2));
		System.out.println("------------------------------------");
		
		Set<Person> testSet = new HashSet<>();
		testSet.add(person1);
		testSet.add(person2);
		
		// set은 중복을 허용하지 않는데 person1, person2는 왜 2개가 나올까? HashCode, Equals를 가지고 비교를 해야함
		// 참조값을 기준으로 HashCode를 생성한다. ㄱ 다른 이유 -> 같게 하려면 HashCode도 같게 만들어주면 된다.
		System.out.println("Set의 갯수 : " + testSet.size());
		System.out.println("------------------------------------");
		System.out.println("p1 : " + person1.hashCode());
		System.out.println("p2 : " + person2.hashCode());
		System.out.println("p3 : " + person3.hashCode());
	}
}

class Person{
	private int num;
	private String name;
	
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
	
	@Override
	public int hashCode() {
		return Objects.hash(name, num);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(name, other.name) && num == other.num;
	}
	
//	@Override
//	public boolean equals(Object obj) {
//		if(this == obj) { //참조값이 같으면 ...
//			return true;
//		}
//		if(obj == null) { //상대방이 없으면 ...
//			return false;
//		}
//		if (getClass() != obj.getClass()) { //클래스 정보가 다르면... -> 클래스 자체가 다르다는 뜻
//			return false;
//
//		}
//		Person that = (Person) obj;
////		ㄴ 무조건 고정 -------------------------------
//		
////		ㄱ 비교할 변수가 많아지면 식이 많아지거나 if문이 많아진다.
//		if(this.name == null && that.name != null) { //나는 널, 상대방은 널이 아니다 -> 다르다
//			return false;
//		}
//		
//		if(this.num == that.num && this.name == that.name) { // 두개 같은 것이니깐 참조값이 같다.
//			return true;
//		}
//		
//		if (this.num == that.num && this.name.equals(that.name)) {
//			return true;
//		}
//		
//		return false; //이 조건에 만족하지 않는 값들은 전부 false
		
	
}