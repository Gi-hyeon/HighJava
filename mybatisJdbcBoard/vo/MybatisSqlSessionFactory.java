package kr.or.ddit.board.vo;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisSqlSessionFactory {
	private static SqlSessionFactory sqlSessionFactory = null;
	
	static {
		Reader rd = null;  
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/mybatis/config/mybatis-config.xml");
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(rd);
		} catch (Exception e) {
			System.out.println("myBatis 초기화 실패!!!");
			e.printStackTrace();
		} finally {
			if(rd!=null) try { rd.close(); }catch(IOException e) {}
		}
	}
	
	/**
	 * SqlSessionFactory객체를 이용하여 SQL문을 처리할 SqlSession객체를 반환하는 메서드
	 * 
	 * @return SqlSession객체
	 */
	public static SqlSession getSqlSession() {
		SqlSession session = sqlSessionFactory.openSession();
		return session;
	}
	
}
