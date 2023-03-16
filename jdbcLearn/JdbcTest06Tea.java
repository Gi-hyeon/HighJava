package learn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
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
			case 5:
				updateMember2();
				break;
			case 6:
				updateMember3();
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
	
	private void updateMember3() {
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
		
		// key값 : 수정할 컬럼명, value값 : 수정할 데이터 값
		// 수정할 데이터 값이 있을 때만 Map에 추가된다.
		Map<String,String> dataMap = new HashMap<>();
		
		System.out.print("새로운 비밀번호 > ");
		String newPass = scanner.nextLine().trim(); //""
		if (!"".equals(newPass)) {
			dataMap.put("mem_pass", newPass);
		}
		
		System.out.print("새로운 회원이름 > ");
		String newName = scanner.nextLine().trim();
		if (!"".equals(newName)) {
			dataMap.put("mem_name", newName);
		}
		
		System.out.print("새로운 전화번호 > ");
		String newTel = scanner.nextLine().trim();
		if (!"".equals(newTel)) {
			dataMap.put("mem_tel", newTel);
		}
		
		System.out.print("새로운 회원주소 > ");
		String newAddr = scanner.nextLine().trim();
		if (!"".equals(newAddr)) {
			dataMap.put("mem_addr", newAddr);
		}
		
		try {
			String temp = ""; // SQL문의 set 이후에 수정할 컬럼 설정하는 부분이 저장될 변수
			// temp 합쳐서 처리하기
			/*int num = 0;
				for (String fieldName : dataMap.keySet()) {
				if (!"".equals(temp)) {
					temp += ", ";
				}
				temp += fieldName + " = '" + dataMap.get(fieldName) + "'";
			}
			
			conn = DBUtil.getConnection();
			
			String sql = "update MYMEMBER set " + temp + " where MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);*/
			
			for (String fieldName : dataMap.keySet()) {
				if (!"".equals(temp)) {
					temp += ", ";
				}
				temp += fieldName + " = ?";
			}
			
			// temp ? 붙여서 처리하기
			String sql = "update MYMEMBER set " + temp + " where MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			int num = 1;
			
			for (String fieldName : dataMap.keySet()) {
				pstmt.setString(num++, dataMap.get(fieldName));
			}
			pstmt.setString(num, id);
			
			
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

	// 회원 정보를 수정하는 메서드 -> 입력한 항목만 수정하기
//	private void updateMember3() {
//		// TODO Auto-generated method stub
//		System.out.println();
//		System.out.println("수정할 회원 정보를 입력하세요...");
//		System.out.print("회원ID > ");
//		String id = scanner.nextLine();
//		String pass = "";
//		String name = "";
//		String tel = "";
//		String addr = "";
//		
//		int count = getMemberCount(id);
//		if (count == 0) {
//			System.out.println(id + "는(은) 없는 회원ID 입니다...");
//			System.out.println("수정 작업을 마칩니다...");
//			return;
//		}
//		
//		try {
//			conn = DBUtil.getConnection();
//			
//			String sql = "select * from mymember where MEM_ID = ?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				pass = rs.getString("MEM_PASS");
//				name = rs.getString("MEM_NAME");
//				tel = rs.getString("MEM_TEL");
//				addr = rs.getString("MEM_ADDR");
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			disConnect();
//		}
//		
//		System.out.print("비밀번호 > ");
//		String newPass = scanner.nextLine();
//		if (newPass.length() == 0) {newPass = pass;}
//		
//		System.out.print("회원이름 > ");
//		String newName = scanner.nextLine();
//		if (newName.length() == 0) {newName = name;}
//		
//		System.out.print("전화번호 > ");
//		String newTel = scanner.nextLine();
//		if (newTel.length() == 0) {newTel = tel;}
//		
//		System.out.print("회원주소 > ");
//		String newAddr = scanner.nextLine();
//		if (newAddr.length() == 0) {newAddr = addr;}
//		
//		try {
//			conn = DBUtil.getConnection();
//			String sql = "update MYMEMBER set MEM_PASS = ?, MEM_NAME=?, MEM_TEL=?, MEM_ADDR=? where MEM_ID = ?";
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, newPass);
//			pstmt.setString(2, newName);
//			pstmt.setString(3, newTel);
//			pstmt.setString(4, newAddr);
//			pstmt.setString(5, id);
//			
//			int update = pstmt.executeUpdate();
//			
//			if (update > 0) {
//				System.out.println(id + " 회원 정보 수정 완료!!!");
//			} else {
//				System.out.println(id + " 회원 정보 수정 실패...");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			disConnect();
//		}
//		
//	}
	
	// 회원 정보를 수정하는 메서드 -> 원하는 항목을 선택해서 수정하기
	private void updateMember2() {
		// TODO Auto-generated method stub
		String updateData = "";
		int update = 0;
		String updateFieldTitle = "";
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID > ");
		String id = scanner.nextLine();
		
		int count = getMemberCount(id);
		if (count == 0) {
			System.out.println(id + "는(은) 없는 회원ID 입니다...");
			System.out.println("수정 작업을 마칩니다...");
			return;
		} 
		
		int select;
		String updateField = "";
		do {
			System.out.println();
			System.out.println("수정할 정보를 선택하세요...");
			System.out.print("1. 비밀번호\n2. 이름\n3. 전화번호\n4. 주소\n");
			System.out.print("입력 > ");
			select = Integer.parseInt(scanner.nextLine());
			
			switch (select) {
			case 1: updateField = "MEM_PASS"; updateFieldTitle = "비밀번호"; break;
			case 2: updateField = "MEM_NAME"; updateFieldTitle = "이름"; break;
			case 3: updateField = "MEM_TEL"; updateFieldTitle = "전화번호"; break;
			case 4: updateField = "MEM_ADDR"; updateFieldTitle = "주소"; break;
			default:
				break;
			}
			
		} while (select < 1 || select > 4);
		
		System.out.println();
		System.out.print("새로운 " + updateFieldTitle + " 입력 > ");
		updateData = scanner.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
//			System.out.print("수정할 자료 입력 > ");
//			change = scanner.nextLine();
//			
//			switch (select) {
//			case 1: sql = "update MYMEMBER set MEM_PASS = ? where MEM_ID = ?"; break;
//			case 2: sql = "update MYMEMBER set MEM_NAME = ? where MEM_ID = ?"; break;
//			case 3: sql = "update MYMEMBER set MEM_TEL = ? where MEM_ID = ?"; break;
//			case 4: sql = "update MYMEMBER set MEM_ADDR = ? where MEM_ADDR = ?"; break;
//			default:
//				System.out.println("옳바른 번호를 입력하세요...");
//				break;
//			}
			
			String sql =  "update MYMEMBER set " + updateField + " = ? where MEM_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updateData);
			pstmt.setString(2, id);
			
			update = pstmt.executeUpdate();
			
			if (update > 0) {
				System.out.println(id + updateFieldTitle + " 수정 완료!!!");
			} else {
				System.out.println(id + " 회원 정보 수정 실패...");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disConnect();
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
		System.out.println("3. 자 료 수 정(전체항목수정)");
		System.out.println("4. 전 체 자 료 출 력");
		System.out.println("5. 자 료 수 정2(수정항목선택)");
		System.out.println("6. 자 료 수 정3(입력항목만수정)");
		System.out.println("0. 작 업 끝.");
		System.out.println("------------------------------");
		System.out.print("작업 선택 > ");
		return Integer.parseInt(scanner.nextLine());
	}
	
}

	
	