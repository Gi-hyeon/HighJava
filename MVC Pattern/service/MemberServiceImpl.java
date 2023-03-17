package kr.or.ddit.basic.mvc.service;

import java.util.List;

import kr.or.ddit.basic.mvc.dao.IMemberDao;
import kr.or.ddit.basic.mvc.dao.MemberDaoImpl;
import kr.or.ddit.basic.mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	// 일을 시킬 DAO 객체 변수 선언
	private IMemberDao dao;
	
	// 생성자
	public MemberServiceImpl() {
		dao = new MemberDaoImpl(); // DAO 객체 생성
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		// int num = dao.insertMember(memVo); return num 대신 간단하게 return 함
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> getAllMember() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMemberCount(String memId) {
		// TODO Auto-generated method stub
		return dao.getMemberCount(memId);
	}
	
}
