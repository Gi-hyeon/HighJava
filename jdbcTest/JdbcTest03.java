package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * 문제) Lprod_id 값을 2개를 입력 받아서 
 * 두 값 중 작은값부터 큰 값 사이의 자료들을 출력하시오
 */

public class JdbcTest03 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("작은 값 > ");
		int a = Integer.parseInt(scanner.nextLine());
		
		System.out.print("큰 값 > ");
		int b = Integer.parseInt(scanner.nextLine());
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "GIHYUN";
			String password = "java";
			
			conn = DriverManager.getConnection(url, user, password);
			
			//String sql = "select * from lprod where lprod_id between " + a + " and " + b ;
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT ");
			builder.append("    * ");
			builder.append("FROM ");
			builder.append("    lprod ");
			builder.append("WHERE ");
			builder.append("    lprod_id BETWEEN ? AND ?");
			String sql = builder.toString();
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, a);
			stmt.setInt(2, b);
			
			//stmt = conn.createStatement();
			
			//rs = stmt.executeQuery(sql);
			
			rs = stmt.executeQuery();
			
			
			System.out.println("=== SQL문 처리 결과 === ");
			while (rs.next()) {
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString("LPROD_GU"));
				System.out.println("LPROD_NM : " + rs.getString("LPROD_NM"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs != null) { try {rs.close();} catch(SQLException e){} }
			if(stmt != null) { try {stmt.close();} catch(SQLException e){} }
			if(conn != null) { try {conn.close();} catch(SQLException e){} }
		}
	}
}
