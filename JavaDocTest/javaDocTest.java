package kr.or.ddit.basic;
// javaDoc 파일 만들기 예제

/*
 * @author 작성자 -> 작성자 이름 수정 가능하다.
 * @versin 작성 가능
 * <p></p>안에 설명 적음
 */


/**
 * 
 * @author PC-08
 * @version 1.0
 * 
 * <p>
 * 파일명 : JavaDocTest.java<br>
 * 설  명 : JavaDoc문서 작성을 위한 연습용 interface<br><br>
 * 
 * 수정 이력<br>
 * --------------------------------<br>
 * 수정일자 : 2023-01-17<br>
 * 작 성 자 : 홍길삼<br>
 * 수정내용 : 최초 작성 <br>
 * --------------------------------<br>
 * </p>
 * 
 */

public interface javaDocTest {
	/**
	 * 메소드명 : methodTest<br>
	 *  설 명   : 반환값이 없는 메서드<br>
	 *  
	 * @param a 첫 번째 매개변수 (정수형)
	 * @param b 두 번째 매개변수 (정수형)
	 */
	public void methodTest(int a, int b);
	
	/**
	 * 메서드명 : methodAdd <br>
	 *  설 명   : 반환값이 있는 메서드<br>
	 *  
	 * @param x 첫 번째 정수형 매개변수
	 * @param y 두 번째 정수형 매개변수
	 * @return 처리된 결과가 정수형으로 반환된다.
	 */
	public int methodAdd(int x, int y);
	
	/**
	 * 메서드명 : methodSub <br>
	 *  설 명   : 매개변수가 없는 메서드<br>
	 *  
	 * @return 정수형으로 반환한다.
	 */
	public int methodSub();
}
