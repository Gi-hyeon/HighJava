package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {
	public static void main(String[] args) {
		// 객체생성
//		 Vector v1 = new Vector(); -> 처음에 이렇게 작성했음
		Vector<Object> v1 = new Vector<>();
		
		System.out.println("처음 크기 : " + v1.size());
		 
		// 데이터 추가하기 : add(추가할 데이터)
		// 반환값 : 추가성공(true), 추가실패(false)
		v1.add("aaaa");
		v1.add(new Integer(111)); //Boxing
		v1.add(123);  //Auto Boxing, 오토 언박싱이 지원된다.
		v1.add('a');
		v1.add(true);
		boolean r = v1.add(3.14);
	
		System.out.println("현재 크기 : " + v1.size());
		System.out.println("반환값 : " + r);
		
		// 데이터 추가하기2 : addElement(추가할 데이터) -> 예전부터 지원했던 명령어라 안없앨뿐이지 사용안함
		// -> 이전 버전의 프로그램과의 호환성을 위해 남아 있는 메서드
		v1.addElement("FFFFFFFF");
		
		System.out.println("v1 -> " + v1);  //.toString() 생략해도 가능
		
		
		// 데이터 추가하기3 : add(index, 데이터)
		// -> 'index'번째에 '데이터'를 끼워 넣는다.
		// -> 'index'는 0부터 시작한다.
		// -> 반환값이 없다.
		v1.add(1, "KKK");
		
		System.out.println("v1 -> " + v1);
		
		// 데이터 꺼내오기 : get(index)
		// -> 'index'번째 데이터를 꺼내서 반환한다.
		System.out.println("0번째 데이터 : " + v1.get(0));
		int iTemp = (int) v1.get(2); //조상에 넣으려고 하기 때문에 넣을 수 없다.
		System.out.println("2번째 데이터 : " + iTemp);
		
		// 데이터 수정하기 : set(index, 새로운데이터)
		// -> 'index'번째 데이터를 '새로운 데이터'로 변경한다.
		// -> 반환값 : '원래의 데이터'
		String sTemp = (String) v1.set(0, "zzzZ"); 
		// ㄴ벡터는 현재 오브젝트로형으로 변환되서 저장되기 때문에 변수에 꼭 저장할 때 형변환 해줘야함
		System.out.println("v1 -> " + v1.toString());
		System.out.println("원래의 데이터 : " + sTemp); //원래의 데이터에는 aaaa가 출력
		
		// 데이터 삭제하기1 : remove(index)
		// -> 'index'번째 데이터를 삭제한다.
		// -> 반환값 : 삭제된 데이터
		v1.remove(0);
		System.out.println("삭제 후 v1 -> " + v1.toString());
		
		sTemp = (String) v1.remove(0);
		System.out.println("삭제 후 v1 -> " + v1);
		System.out.println("삭제된 데이터 : " + sTemp);

		// 데이터 삭제하기2 : remove(삭제할 데이터)
		// -> '삭제할데이터'를 찾아서 삭제한다.
		// -> '삭제할데이터'가 여러개이면 이 중에 제일 첫번째 자료가 삭제된다.
		// -> 반환값 : 삭제성공(true), 삭제실패(false)
		// ->*삭제할 데이터가 '정수형'이거나 'char형'일 경우에는 반드시 객체로 변환해서 사용해야 한다.
		v1.remove("CCC");
		System.out.println("CCC 삭제 후 v1 -> " + v1);

		// v1.remove(123); //123번째를 지워라라고 인식해서 오류
		// v1.remove(new Integer(123)); //방법1 -> 1.9버전부터는 사용 불가
		v1.remove(Integer.valueOf(123)); // 방법2 -> 권장 
		System.out.println("123 삭제 후 v1 -> " + v1);
		
		// v1.remove('a'); // 데이터가 많을경우 97가 지워진다, 아스키 97로 인식해서 위에랑 똑같은 증상이 일어난다. 
		v1.remove(new Character('a')); 
		System.out.println("a 삭제 후 v1 -> " + v1);
		
		v1.remove(true); 
		System.out.println("true 삭제 후 v1 -> " + v1);
		
		v1.remove(3.14); 
		System.out.println("3.14 삭제 후 v1 -> " + v1);
		
		// 전체 데이터 삭제하기 : clear();
		v1.clear();
		System.out.println("clear 삭제 후 v1 -> " + v1);
		
		// ---------------------------------------------
		/*
		 * 제네릭 타입(Generic Type) -> 클래스 내부에서 사용할 데이터 타입을 객체를 생성할 때
		 * 외부에서 지정해 주는 기법으로 객체를 선언할 때 < >괄호 안에 그 객체의 내부에서
		 * 사용할 데이터의 타입을 정해주는 것을 말한다.
		 * 
		 * - 이런식으로 선언하게 되면 그 데이터 타입 이외의 다른 종류의 데이터를 저장할 수 없다.
		 * - 이때 제네릭으로 선언 될 수 있는 데이터 타입은 클래스형 이어야 한다.
		 * 	 그래서 int는 Integer, boolean은 Boolean, Char는 Character 등으로 대체해서
		 *   사용해야 한다.
		 * - 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다.
		 */
		Vector<Integer> v2 = new Vector<>(); // int형 자료만 저장할 수 있는 벡터, <타입>생략해도 됌
		Vector<String> v3 = new Vector<>(); // String형만 저장할 수 있는 벡터
		
		v3.add("안녕하세요.");
//		v3.add(100); -> 오류 : 다른 종류의 데이터를 저장할 수 없다.
		
		String sTemp2 = v3.get(0); //형변환 없이 사용, 처음 만들 때 스트링으로 선언했기 때문에 오류발생 안함
		
		Vector<Vector> vv = new Vector<>();
		Vector<Vector<Vector>> vvv = new Vector<>(); //사용하지는 않지만 벡터안에 벡터가 가능하다
		
		System.out.println("---------------------------------------------");
		// ---------------------------------------------
		v3.clear();
		System.out.println("v3의 size -> " + v3.size());
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("v3 -> " + v3);
		System.out.println("v4 -> " + v4);
		
		
		// 데이터 삭제하기3 : removeAll() -> removeAll(Collection 객체), clear는 데이터를 전체 지우기 
		// -> 벡터의 데이터 중에서 'Collection객체'가 가지고 있는 모든 데이터를 삭제한다.
		// -> 반환값 : 성공(true), 실패(false)
		
		v3.removeAll(v4); //v3의 데이터들 중에서 v4가 가지고 있는 데이터들을 모두 삭제한다.
		System.out.println("v3 -> " + v3);
		System.out.println("---------------------------------------------");
		
		// 벡터의 데이터를 순서대로 모두 가져와 사용하고 싶으면 반복문을 사용하면 된다.
		// (주로 for문 사용)
		v3.clear();
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		
		for(int i=0; i<v3.size(); i++) {
			System.out.println(i + "번째 자료 : " + v3.get(i));
		}
		
		System.out.println("---------------------------------------------");
		// 향상된 for문
		for (String str : v3) {
			System.out.println(str);
		}
		
	}
}
