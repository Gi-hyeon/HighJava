package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {
	public static void main(String[] args) {
		// ArrayList의 기본적인 사용법은 Vector와 같다.
		
		ArrayList<Object> list1 = new ArrayList<>();
//		ArrayList list1 = new ArrayList();
		
		// add()메서드를 이용해서 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('k');
		list1.add(true);
		list1.add(123.45);
		System.out.println("list1 -> " + list1.toString());  //.toString() 생략 가능
		System.out.println("size -> " + list1.size());
		
		// get()메서드를 이용해서 데이터를 꺼내온다.
		System.out.println("1번째 자료 : " + list1.get(1));
		
		// 데이터 끼워넣기도 같다.
		list1.add(3, "zzz");
		System.out.println("list1 -> " + list1);
		
		//데이터 변경하기
		String sTemp = (String) list1.set(3, "yyy");
		System.out.println("list1 -> " + list1);
		System.out.println("sTemp -> " + sTemp); //바뀌기 전 데이터가 들어간다
		
		//삭제도 같다.
		list1.remove(3);
		System.out.println("3번째 자료 삭제 후 list1 -> " + list1);
		
		list1.remove("bbb");
		System.out.println("bbb 자료 삭제 후 list1 -> " + list1);
		
		// 제네릭을 사용할 수 있다.
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " -> " + list2.get(i));
		}
		
		for (String str : list2) {
			System.out.println(str);
		}
		System.out.println("-----------------------------------");
		// contains(비교객체) -> 리스트에 저장된 데이터 중에서 '비교객체'가 있으면 ture, 없으면 false 반환
		System.out.println("DDDD값 존재 여부 : " + list2.contains("DDDD"));
		System.out.println("ZZZZ값 존재 여부 : " + list2.contains("ZZZZ"));
		
		// indexOf(비교객체) -> 리스트에 '비교객체'가 있으면 '비교객체'가 저장된 index값을 반환하고
		// 						없으면 01을 반환한다.
		// - indexOf()는 검색 방향이 앞에서부터 뒤쪽으로 검색하고
		// - lastIndexOf()메서드는 뒤에서 앞쪽으로 검색한다.
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		System.out.println("list2 -> " + list2);
		
		
		System.out.println("DDDD의 위치값 : " + list2.indexOf("DDDD"));
		System.out.println("DDDD의 위치값 : " + list2.indexOf("ZZZZ"));
		System.out.println("DDDD의 위치값 : " + list2.lastIndexOf("DDDD"));
		System.out.println("-----------------------------------");
		
		// - toArray() -> 리스트 안의 데이터를 배열로 변환해서 반환한다.
		//			   -> 기본적으로 Object 	
		// - toArray(new 제네릭타입명[0]) -> 제네릭 타입의 배열로 변환해서 반환한다.
		
		Object[] strArr = list2.toArray();
//		String[] strArr = (String[]) list2.toArray(); //형변환 잘되는 거 같아보이지만 toArray는 무조건 오브젝트 형식
		System.out.println("배열의 개수 : " + strArr.length);
		
		for (int i = 0; i < strArr.length; i++) {
			System.out.println(i + "번째 자료 : " + strArr[i]);
		}
		
		System.out.println("-----------------------------------");
		// 제네릭 타입의 배열로 변환해서 가져오기
		String[] strArr2 = list2.toArray(new String[0]);
		for (String str : strArr2) {
			System.out.println(str);
		}
		
		
	}
}
