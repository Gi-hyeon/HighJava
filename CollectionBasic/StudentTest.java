package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점 등수를 멤버로 갖는
 * 		 Student 클래스를 만든다.
 * 		 이 클래스의 생성자에는 학번, 이름, 국어점수, 영어점수, 수학점수만 인수로 받아서
 * 		 초기화 처리를 한다.
 * 
 * 		 이 Student 객체는 List에 저장하여 관리한다.
 * 
 * 		 List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
 * 		 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준도
 * 		 구현하여 정렬된 결과를 출력하시오. (클래스명 : SortByTotal)
 * 
 * 		 (등수는 List에 전체 데이터가 추가된 후에 구해서 저장한다.)
 * 	
 */

public class StudentTest {
	//등수를 구하는 메서드
	public void setRank(List<Student> list) {
		for (Student student : list) {		// 등수를 구할 기준 데이터를 구하기 위한 반복문
			int rank = 1;		// 처음에는 1등으로 초기화 해놓고 시작한다.
			
			for (Student student2 : list) {		// 비교 대상을 나타내는 반복문
				// 기준값보다 큰 값을 만나면 rank 변수값을 증가시킨다.
				if(student.getTotal() < student2.getTotal()) {
					rank++;
				}
			}
			
			// 구해진 등수를 Student 객체의 rank 변수에 저장한다.
			student.setRank(rank);
		}
	}
	
	public static void main(String[] args) {
		StudentTest test = new StudentTest();
		List<Student> list = new ArrayList<>();
		
		list.add(new Student(2, "존기현", 90, 70, 50));
		list.add(new Student(1, "구기현", 60, 70, 80));
		list.add(new Student(4, "김기현", 40, 70, 90));
		list.add(new Student(7, "박기현", 90, 70, 80));
		list.add(new Student(5, "훈기현", 90, 75, 75));
		list.add(new Student(3, "고기현", 90, 80, 70));
		
		test.setRank(list);
		
		System.out.println("학번의 오름차순 정렬 후...");
		Collections.sort(list);
		for (Student student : list) {
			System.out.println(student);
		}
		
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("총점 내림차순 정렬 후...");
		Collections.sort(list, new SortByTotal());
		for (Student student : list) {
			System.out.println(student);
		}
		
	}
}

class Student implements Comparable<Student>{
	private int num;
	private String name; 
	private int kscore;
	private int eScore;
	private int mScore;
	private int total;
	private int rank;
	
	public Student(int num, String name, int kscore, int eScore, int mScore) {
		super();
		this.num = num;
		this.name = name;
		this.kscore = kscore;
		this.eScore = eScore;
		this.mScore = mScore;
		this.total = kscore + eScore + mScore;
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
	public int getScore() {
		return kscore;
	}
	public void setScore(int kscore) {
		this.kscore = kscore;
	}
	public int geteScore() {
		return eScore;
	}
	public void seteScore(int eScore) {
		this.eScore = eScore;
	}
	public int getmScore() {
		return mScore;
	}
	public void setmScore(int mScore) {
		this.mScore = mScore;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return String.format("Student [num=%s, name=%s, kscore=%s, eScore=%s, mScore=%s, total=%s, rank=%s]", num, name,
				kscore, eScore, mScore, total, rank);
	}

	// 학번의 오름차순으로 정렬하기
	@Override
	public int compareTo(Student o) {
		return Integer.compare(this.getNum(), o.getNum());
	}

	//-------------------------------------------
//	@Override
//	public int compareTo(Student o) {
//		return (o.getNum() - this.getNum()) * -1;
//	}	
}


// 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스
class SortByTotal implements Comparator<Student>{
	@Override
	public int compare(Student o1, Student o2) {
		if(o2.getTotal() == o1.getTotal()) {
			return o1.getName().compareTo(o2.getName());
		}
		return Integer.compare(o1.getTotal(), o2.getTotal()) * -1;
	}
	
//	------------------------------------------------------------
//class SortByTotal implements Comparator<Student>{
//	@Override
//	public int compare(Student o1, Student o2) {
//		if(o2.getTotal() - o1.getTotal() == 0) {
//			return o1.getName().compareTo(o2.getName());
//		}
//		return (o2.getTotal() - o1.getTotal());
//	}
}
