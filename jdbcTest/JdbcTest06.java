package kr.or.ddit.basic;

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

public class JdbcTest06 {
	Scanner scanner = new Scanner(System.in);
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public static void main(String[] args) {
		new JdbcTest06().start();
	}
	
	private void start() {
		while (true) {
			int selectNum = display();
			
			switch (selectNum) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				allSelect();
				break;
			case 0:
				System.out.println("종료...");
				return;
			default :
				System.out.println(" ");
				break;
			}
		}
	}

	private void insert() {
		// TODO Auto-generated method stub
		int count = 0;
		String memId;
		try {
			conn = DBUtil.getConnection();
			
			do {
				System.out.print("회원 아이디(mem_id) 입력 > ");
				memId = scanner.nextLine();
				
				String sql = "select count(*) cnt from mymember where mem_id = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memId);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					count = rs.getInt("cnt");
					//System.out.println(count);
				}
				if (count == 1) {
					System.out.println("회원 아이디(mem_id)" + memId + "는(은) 중복입니다." );
					System.out.println("다른 아이디 다시 입력하세요...");
				}
			} while (count == 1);
			
			System.out.print("회원 비밀번호(mem_pass) 입력 > ");
			String memPass = scanner.nextLine();
			System.out.print("회원 이름(mem_name) 입력 > ");
			String memName = scanner.nextLine();
			System.out.print("회원 전화번호(mem_tel) 입력 > ");
			String memTel = scanner.nextLine();
			System.out.print("회원 주소(mem_addr) 입력 > ");
			String memAddr = scanner.nextLine();
			
			String sql2 = "insert into mymember values(?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memName);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAddr);
			
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("등록 성공!!!");
			} else {
				System.out.println("등록 실패...");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null) {try {conn.close();} catch (SQLException e) {}}
		}
	}
	
	private void delete() {
		// TODO Auto-generated method stub
		try {
			System.out.print("삭제할 회원 아이디(mem_id) 입력 > ");
			String memId = scanner.nextLine();
			
			conn = DBUtil.getConnection();
			
			String sql = "delete from MYMEMBER where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int update = pstmt.executeUpdate();
			
			if (update > 0) {
				System.out.println("정상적으로 삭제되었습니다.");
			} else {
				System.out.println("삭제 실패... ID를 다시 확인해주세요.");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null) {try {conn.close();} catch (SQLException e) {}}
		}
	}
	
	private void update() {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			
			System.out.print("수정할 회원 아이디(mem_id) 입력 > ");
			String memId = scanner.nextLine();
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("cnt");
				//System.out.println(count);
				}
			
			if(count == 0) {
				System.out.println("올바르지 않은 회원 아이디입니다.");
				return;
			}
			
			System.out.print("회원 비밀번호(mem_pass) 입력 > ");
			String memPass = scanner.nextLine();
			System.out.print("회원 이름(mem_name) 입력 > ");
			String memName = scanner.nextLine();
			System.out.print("회원 전화번호(mem_tel) 입력 > ");
			String memTel = scanner.nextLine();
			System.out.print("회원 주소(mem_addr) 입력 > ");
			String memAddr = scanner.nextLine();
			
			String sql2 = "update MYMEMBER set MEM_PASS = ?, MEM_NAME=?, MEM_TEL=?, MEM_ADDR=? where MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memPass);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			pstmt.setString(5, memId);
			
			int update = pstmt.executeUpdate();
			
			if (update > 0) {
				System.out.println("정상적으로 수정되었습니다.");
			} else {
				System.out.println("수정 실패...");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null) {try {conn.close();} catch (SQLException e) {}}
		}
	}
	
	private void allSelect() {
		// TODO Auto-generated method stub
		try {
			conn = DBUtil.getConnection();
				
			String sql = "select * from mymember";
				
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			System.out.println("MEM_ID\t MEM_PASS\t MEM_NAME\t MEM_TEL\t MEM_ADDR");
			while (rs.next()) {
				System.out.printf("%5s", rs.getString("MEM_ID"));
				System.out.printf("%10s", rs.getString("MEM_PASS"));
				System.out.printf("%13s", rs.getString("MEM_NAME"));
				System.out.printf("%20s", rs.getString("MEM_TEL"));
				System.out.printf("%9s", rs.getString("MEM_ADDR"));
				System.out.println();
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null) {try {conn.close();} catch (SQLException e) {}}
		}
	}

	private int display() {
		System.out.println("------------------------------");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("0. 작업 끝");
		System.out.println("------------------------------");
		return Integer.parseInt(scanner.nextLine());
	}
}
