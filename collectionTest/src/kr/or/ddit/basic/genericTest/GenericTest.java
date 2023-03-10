package kr.or.ddit.basic.genericTest;

// 제네릭을 사용하지 않는 class
class NonGeneric{
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}

/*
 * 제네릭 클래스 만드는 방법
 * 형식 
 * class 클래스명<제네릭타입문자>{
 * 		제네릭타입문자 변수명;		// 변수 선언에 제네릭을 사용할 경우
 * 		...
 * 		
 * 		제네릭타입문자 메서드명{		// 반환값이 있는 메서드에 제네릭 사용하는 경우
 * 			...
 * 			return 반환값;
 * 		}
 * 
 * 		// 메서드의 매개변수에 제네릭을 사용하는 경우
 * 		void 메서드명(제네릭타입문자 변수명1, ...){
 * 			...
 * 		}
 * }
 * 
 * -- 제네릭 타입 글자 --
 * T -> type, E -> Element(자료구조에 들어가는 데이터), K -> Key, V -> Value, N -> Number
 */

class MyGeneric<T>{
	private T value;		//변수 선언

	public T getValue() {		//반환값 타입
		return value;
	}

	public void setValue(T value) {		// 매개변수
		this.value = value;
	}
}

public class GenericTest {
	public static void main(String[] args) {
		NonGeneric ng1 = new NonGeneric();
		ng1.setValue("가나다라");
		
		NonGeneric ng2 = new NonGeneric();
		ng2.setValue(100);
		
		// String 타입은 직접 저장이 불가능하다.
		String sTemp = (String) ng1.getValue(); 
		int iTemp = (int) ng2.getValue();
		
		System.out.println("문자열 반환값 : " + sTemp);
		System.out.println("정수형 반환값 : " + iTemp);
		
		// 문법상 오류 발생은 안하지만 실행하면 ClassCastException 오류 발생
//		String sTemp2 = (String) ng2.getValue(); 
		
		System.out.println("-------------------------------");
		MyGeneric<String> mg1 = new MyGeneric<>();
		mg1.setValue("안녕하세요.");
		
		MyGeneric<Integer> mg2 = new MyGeneric<>();
		mg2.setValue(500);
		
		sTemp = mg1.getValue();
		iTemp = mg2.getValue();
		
		// 제네릭 타입을 쓰게되면 데이터 타입이 뭔지 이미 결정이 나있기 때문에 오류가 발생한다.
//		String sTemp3 = mg2.getValue();
		System.out.println("제네릭 문자열 반환값 : " + sTemp);
		System.out.println("제네릭 정수형 반환값 : " + iTemp);
	}
}
