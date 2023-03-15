package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) 사용자로부터 Lprod_id값을 입력 받아
//		 입력한 값보다 Lprod_id가 큰 자료들을 출력하시오.
public class JdbcTest02 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("LPROD_ID > ");
		int a = Integer.parseInt(scanner.nextLine());
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "GIHYUN", "java");
			
			String sql = "select * from lprod where lprod_id > " + a;
			//System.out.println(sql);
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("=== SQL문 처리 결과 ===");
			
			while (rs.next()) {
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString("LPROD_GU"));
				System.out.println("LPROD_NM : " + rs.getString("LPROD_NM"));
				System.out.println("---------------------------------------");
			}
					
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs!=null) {try {rs.close();} catch (SQLException e) {}}
			if (stmt!=null) {try {stmt.close();} catch (SQLException e) {}}
			if (conn!=null) {try {conn.close();} catch (SQLException e) {}}
		}
	}
}
