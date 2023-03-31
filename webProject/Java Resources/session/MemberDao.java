package kr.or.ddit.basic.session;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDao {
	private static MemberDao dao;
	
	private MemberDao() {};
	
	public static MemberDao getInstance() {
		if (dao == null) {
			dao = new MemberDao();
		}
		return dao;
	}
	
	public MemberVO getMember(MemberVO memVo) {
		SqlSession session = null;
		
		MemberVO loginMemberVo = null; // 반환값이 저장될 변수
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			loginMemberVo = session.selectOne("member.getMember", memVo);
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		
		return loginMemberVo;
	}
}
