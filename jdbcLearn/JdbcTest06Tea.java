package learn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.basic.util.DBUtil;

/*
 * 회원을 관리하는 프로그램을 작성하시오.
 * (MYMEMBER 테이블 이용)
 * 
 * 아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
 * 메뉴 예시)
 * ------------------------------
 * 1. 자료 추가		-> insert(C)
 * 2. 자료 삭제		-> delete(D)
 * 3. 자료 수정		-> update(U)
 * 4. 전체 자료 출력 -> select(R)
 * 0. 작업 끝
 * ------------------------------
 * 
 * 조건1)
 * 1) 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다).
 * 2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
 * 3) 자료 수정에서 '회원ID'는 변경되지 않는다.
 */

public class JdbcTest06Tea {
	Scanner scanner = new Scanner(System.in);
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static void main(String[] args) {
		new JdbcTest06Tea().startMember();
	}
	
	// 자원을 반납하는 메서드
	private void disConnect() {
		if (rs!=null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		if (pstmt!=null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		if (conn!=null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
	}
	
	// 작업을 시작하는 메서드
	private void startMember() {
		// TODO Auto-generated method stub
		while (true) {
			int selectNum = displayMenu();
			
			switch (selectNum) {
			case 1:
				insertMember();
				break;
			case 2:
				deleteMember();
				break;
			case 3:
				updateMember();
				break;
			case 4:
				displayAllMember();
				break;
			case 0:
				System.out.println("종료...");
				return;
			default :
				System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력하세요.");
				break;
			}
		}
	}
	
	private void updateMember() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.println("회원ID > ");
		String id = scanner.nextLine();
		
		int count = getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		} 
		
		System.out.print("비밀번호 > ");
		String pass = scanner.nextLine();
		
		System.out.print("회원이름 > ");
		String name = scanner.nextLine();
		
		System.out.print("전화번호 > ");
		String tel = scanner.nextLine();
		
		System.out.print("회원주소 > ");
		String addr = scanner.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update MYMEMBER set MEM_PASS = ?, MEM_NAME=?, MEM_TEL=?, MEM_ADDR=? where MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, id);
			
			int update = pstmt.executeUpdate();
			
			if (update > 0) {
				System.out.println(id + " 회원 정보 수정 완료!!!");
			} else {
				System.out.println(id + " 회원 정보 수정 실패...");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void deleteMember() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		System.out.println("회원ID > ");
		String id = scanner.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from MYMEMBER where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int update = pstmt.executeUpdate();
			
			if (update > 0) {
				System.out.println("회원ID가 " + id + "인 회원 정보 삭제 성공!!!");
			} else {
				System.out.println(id + " 회원은 없는 회원이거나 삭제 작업에 실패했습니다.");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	// 회원 정보를 출력하는 메서드
	private void displayAllMember() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("--------------------------------------------------------------");
		System.out.println("  ID     비밀번호         이름         전화번호           주소");
		
		try {
			conn = DBUtil.getConnection();
				
			String sql = "select * from mymember";
				
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while (rs.next()) {
				cnt++;
				System.out.printf("%5s", rs.getString("MEM_ID"));
				System.out.printf("%10s", rs.getString("MEM_PASS"));
				System.out.printf("%13s", rs.getString("MEM_NAME"));
				System.out.printf("%20s", rs.getString("MEM_TEL"));
				System.out.printf("%9s", rs.getString("MEM_ADDR"));
				System.out.println();
			}
			
			if (cnt==0) {
				System.out.println();
				System.out.println("  등록된 정보가 하나도 없습니다...");
				System.out.println();
			}
			
			System.out.println("--------------------------------------------------------------");
			
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			disConnect();
		}
	}

	// 회원 정보를 추가(insert)하는 메서드
	private void insertMember() {
		// TODO Auto-generated method stub
		String id = null;
		int count = 0;
		
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");
		
		// 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다).
		do {
			System.out.print("회원ID > ");
			id = scanner.nextLine();
			count = getMemberCount(id);
			
			if (count > 0) {
				System.out.println(id + "은(는) 이미 등록된 회원 ID입니다.");
				System.out.println("다른 회원ID를 입력하세요...");
			}
			
		} while (count == 1);
		
		System.out.print("비밀번호 > ");
		String pass = scanner.nextLine();
		
		System.out.print("회원이름 > ");
		String name = scanner.nextLine();
		
		System.out.print("전화번호 > ");
		String tel = scanner.nextLine();
		
		System.out.print("회원주소 > ");
		String addr = scanner.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember values(?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);
			
			int update = pstmt.executeUpdate();
			
			if (update > 0) {
				System.out.println(id + " 회원 정보 추가 완료!!!");
			} else {
				System.out.println(id + " 회원 정보 추가 실패...");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		
	}
	
	// 회원ID를 매개변수로 받아서 해당 회원ID의 개수를 반환하는 메서드
	private int getMemberCount(String memId) {
		int count = 0; // 반환값이 저장될 변수 선언
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return count;
	}

	// 메뉴를 출력하고 선택한 작업번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println("------------------------------");
		System.out.println("1. 자 료 추 가");
		System.out.println("2. 자 료 삭 제");
		System.out.println("3. 자 료 수 정 ");
		System.out.println("4. 전 체 자 료 출 력");
		System.out.println("0. 작 업 끝.");
		System.out.println("------------------------------");
		System.out.print("작업 선택 > ");
		return Integer.parseInt(scanner.nextLine());
	}
	
}

	
	