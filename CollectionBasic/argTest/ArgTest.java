package kr.or.ddit.basic.argTest;

public class ArgTest {
	
	// 메서드 만들기
	public int sumArr(int[] data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	public void myMethod(int a) {
		
	}
	
	// 가변형 인수 -> 메서드의 인수 개수가 호출할 때마다 다를 때 사용한다.
	// - 가변형 인수는 메서드 안에서는 배열로 처리된다.
	// - 가변형 인수는 한가지 자료형만 사용할 수 있다.
	
	//가변형 인수용 메서드 만들기
	public int sumArg(int... data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum;
	}
	
	// - 가변형 인수와 일반적인 인수를 같이 사용할 경우에는 가변형 인수를 제일 뒤쪽에 배치해야 한다.
	// - 컴퓨터는 가변형 인수가 앞에 나오면 전체적인 데이터를 가변형 인수로 생각한다.
	//	  ㄴ 뒤에 데이터를 못가져오는 경우가 있고, 논리적인 방법으로 코딩으로 해결할 수 있긴하다.	
	public String sumArg2(String name, int... data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return name + "씨 점수 : " + sum;
	}
	
	
	public static void main(String[] args) {
		ArgTest test = new ArgTest();
		
//		int[] nums = {100, 200, 300};
		
//		int[] nums;
//		nums = new int[] {100, 200, 300};
		
		int[] nums = new int[] {100, 200, 300};
		
		System.out.println(test.sumArr(nums));
		System.out.println(test.sumArr(new int[] {1,2,3,4,5}));		//1, 2, 3, 4, 5
		
		int b = 100;
		test.myMethod(b);
		test.myMethod(200);
		System.out.println("-------------------------------");
		System.out.println(test.sumArg(100, 200, 300));
		System.out.println(test.sumArg(1, 2, 3, 4,      5));
		System.out.println(test.sumArg2("홍길동", 90, 29, 30, 47, 99, 24));
		
	}
}
