package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {
	
	// bankinfo 테이블에 계좌번호 정보를 추가하는 예제
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "GIHYUN";
			String password = "java";
			conn = DriverManager.getConnection(url, user, password);
			
			System.out.println("계좌번호 정보 추가하기...");
			System.out.print("계좌번호 입력 >");
			String bankNo = scan.nextLine();
			
			System.out.print("은행명 입력 >");
			String bankName = scan.nextLine();
			
			System.out.print("예금주명 입력 >");
			String userName = scan.nextLine();
			
			// Statement 객체를 이용하여 입력하기
			//String sql = "insert into BANKINFO values ('" + bankNo + "', '" + bankName + "', '" + userName + "', sysdate)";
			
			//System.out.println(sql);
			
//			stmt = conn.createStatement();
//			
//			// select문을 실행할 때는 executeQuery() 메서드를 사용하고,
//			// select문이 아닌 문장(insert, update, delete)을 
//			// 실행할 때는 executeUpdate() 메서드를 사용한다.
//			
//			// executeUpdate() 메서드의 반환값 -> 작업에 성공한 레코드 수 반환함
//			
//			int cnt = stmt.executeUpdate(sql);
//			
//			System.out.println("반환값 : " + cnt);
			
//			-----------------------prepareStatement 객체 이용 
			
			// SQL문을 작성할 떄 SQL문에서 데이터가 들어갈 자리를 물음표(?)로 표시하여 작성한다.
			
			
			StringBuilder builder = new StringBuilder();
			builder.append("INSERT INTO bankinfo VALUES ( ");
			builder.append("    ?, ");
			builder.append("    ?, ");
			builder.append("    ?, ");
			builder.append("    SYSDATE ");
			builder.append(") ");
			String sql = builder.toString();
			
			// prepareStatement 객체 생성 -> 사용할 SQL 문을 인수값으로 넘겨준다.
			pstmt = conn.prepareStatement(sql);
			
			// SQL문의 물음표(?)자리에 들어갈 데이터를 셋팅한다.
			// 형식)pstmt.set자료형이름(물음표번호, 셋팅할 데이터)
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, userName);
			
			
			// 데이터의 셋팅이 완료되면 실행한다.
			// select문일 경우 -> executeQuery() 메서드 이용
			// select문이 아닐 경우 -> executeUpdate() 메서드 이용
			int cnt = pstmt.executeUpdate();
			System.out.println("반환값 : " + cnt);
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {try {stmt.close();} catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (conn != null) {try {conn.close();} catch (SQLException e) {}}
		}

		
	}
}
