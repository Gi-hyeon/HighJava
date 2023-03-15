package learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.basic.util.DBUtil;

/*
 * LPROD테이블에 새로운 데이터 추가하기
 * LPROD_GU와 LPROD_NM은 직접 입력받아서 처리하고,
 * LPROD_ID는 현재와 LPROD_ID중에서 제일 큰 값보다 1 크게 한다.
 * 
 * 입력받은 LPROD_GU가 이미 등록되어 있으면 다시 입력받아서 처리한다.
 * 
 */

public class JdbcTest05Tea {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select max(lprod_id)+1 from lprod";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int lprodId = 0;
			if(rs.next()) {
				//lprodId = rs.getInt("max(lprod_id)+1");	// alias가 없을 때
				lprodId = rs.getInt(1);
				//lprodId = rs.getInt("maxid");	// alias가 있을 때
			}
			pstmt.close();
			
			//------------------------------------------------------
			
			// 입력 받은 Lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
			String lprodGu; // 상품 분류 코드가 저장될 변수 선언
			int count = 0; // 입력한 상품 분류 코드의 개수가 저장될 변수
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 > ");
				lprodGu = scanner.nextLine();
				
				String sql2 = "SELECT COUNT(*) cnt FROM lprod WHERE lprod_gu = ?";
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, lprodGu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				if (count == 1) {
					System.out.println("상품 분류 코드(LPROD_GU)" + lprodGu + "는(은) 이미 등록된 코드입니다.");
					System.out.println("다른 코드로 다시 입력하세요...");
				}
			} while (count==1); //primary 키이기 때문에 0, 1 둘중하나만 나온다.
			pstmt.close();
			
			System.out.print("상품 분류명(LPROD_NM) 입력 > ");
			String lprodNm = scanner.nextLine();
			
			String sql3 = "INSERT INTO LPROD VALUES(?, ?, ?)";
			
			// 2번사용할 때부터 prpareStatement를 써버리면 경고를 받음 -> close 해주면됌
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, lprodId);
			pstmt.setString(2, lprodGu);
			pstmt.setString(3, lprodNm);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println("등록 성공!!!");
			} else {
				System.out.println("등록 실패~~~");
			}
					
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null) {try {conn.close();} catch (SQLException e) {}}
		}

	}
}
