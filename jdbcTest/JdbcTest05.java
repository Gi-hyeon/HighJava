package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * LPROD테이블에 새로운 데이터 추가하기
 * LPROD_GU와 LPROD_NM은 직접 입력받아서 처리하고,
 * LPROD_ID는 현재와 LPROD_ID중에서 제일 큰 값보다 1 크게 한다.
 * 
 * 입력받은 LPROD_GU가 이미 등록되어 있으면 다시 입력받아서 처리한다.
 * 
 */

public class JdbcTest05 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		Scanner scanner = new Scanner(System.in);
		int count = 0;
		int max = 0;
		
		while (true) {
			System.out.print("코드 입력 > ");
			String lprodGu = scanner.nextLine();
			
			System.out.print("제품이름 입력 > ");
			String lprodNm = scanner.nextLine();
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "GIHYUN";
				String password = "java";
				conn = DriverManager.getConnection(url, user, password);
				
				StringBuilder builder = new StringBuilder();
				builder.append(" SELECT");
				builder.append("     COUNT(*)");
				builder.append(" FROM");
				builder.append("     lprod");
				builder.append(" WHERE");
				builder.append("     lprod_gu = ?");
				String sql = builder.toString();
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, lprodGu);
				
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					count = rs.getInt("COUNT(*)");
					System.out.println(count);
				}			
				
				
				sql = "select max(lprod_id)+1 from lprod";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					max = rs.getInt("MAX(LPROD_ID)+1");
					System.out.println(max);
				}
				
				if(count > 0) {
					System.out.println("코드가 중복입니다.");
				}else {
					sql = "INSERT INTO LPROD VALUES(?, ?, ?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, max);
					pstmt.setString(2, lprodGu);
					pstmt.setString(3, lprodNm);
					
					pstmt.executeUpdate();
					System.out.println("등록 성공");
					break;
				}
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
