package kr.or.ddit.basic.mvc.vo;

/*
 * DB테이블에 있는 컬럼을 기준으로 데이터를 객체화할 클래스
 * 
 * DB테이블의 '컬럼명'이 이 클래스의 '멤버변수'가 된다.
 * DB테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 한다.
 */

public class MemberVO {
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_tel;
	private String mem_addr;
	
	// VO 클래스에서 별도의 생성자를 만들 때에는
	// 기본 생성자도 반드시 같이 만들어 준다 -> MyBatis는 기본 생성자 주로 작업을 한다. 지금은 상관 없
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_tel() {
		return mem_tel;
	}
	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	
	@Override
	public String toString() {
		return String.format("MemberVO [mem_id=%s, mem_pass=%s, mem_name=%s, mem_tel=%s, mem_addr=%s]", mem_id,
				mem_pass, mem_name, mem_tel, mem_addr);
	}
	
}

/*
 * MVC(Model, View, Controller)패턴
 * - MVC패턴의 비지니스 로직에 사용되는 클래스들...
 *   VO, DTO -> 데이터를 저장하는 역할만 하는 클래스
 *   		 -> DB테이블에서 1개의 레코드를 저장할 클래스를 말한다.
 *   		 -> (VO : Value Object, DTO : Data Transfer Object)
 *   DAO -> SQL 문을 DB 서버로 보내서 결과를 얻어오는 역할을 수행하는 클래스
 *   		(DAO : Data Access Object)
 *   Service -> 일을 수행하는 중간 관리자와 같은 역할을 수행하는 클래스
 *   		 -> 서비스는 일이 있으면 그 일에 필요한 DAO를 호출해서 일츨 처리한 후
 *   		    처리 결과를 Controller에게 전달한다.
 *   Controller -> 비즈니스 로직의 시작 부분으로 사용자의 요청이 오면 그 요청에
 *   			   맞는 Service에게 일을 시키고 Service가 보내온 처리 결과를 
 *   			   화면 등에 반영시키는 역할을 수행한다. (View에 전달한다. - MVC2)
 *   
 *   Controller --> Service --> DAO --> DB서버
 *   		    <--  		<--   	<--	
 *   		각 단계에서 서로 전달되는 데이터는 VO 또는 Collection 객체를 이용한다.
 *   
 *   1. VO 설계 -> controller -> dao -> service
 *   2. VO설계 -> DAO -> Service -> Controller 순서... 이게 가장 편하다.
 */