package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.board.vo.MybatisSqlSessionFactory;
import util.DBUtil;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	// 1번
	private static JdbcBoardDaoImpl dao;
	
	// 2번
	private JdbcBoardDaoImpl() { }
	
	// 3번
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null) dao = new JdbcBoardDaoImpl();
		return dao;
	}
	

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("board.insertBoard", boardVo);
		} finally {
			session.commit();
			session.close();
		}
			
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.delete("board.deleteBoard", boardNo);
		} finally {
			// TODO: handle finally clause
			session.commit();
			session.close();
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		SqlSession session = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("board.updateBoard", boardVo);
		} finally {
			session.commit();
			session.close();
		}
		
		return cnt;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		SqlSession session = null;
		JdbcBoardVO boardVo = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			boardVo = session.selectOne("board.getBoard", boardNo);
		} finally {
			// TODO: handle finally clause
			session.commit();
			session.close();
		}
		
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		SqlSession session = null;
		List<JdbcBoardVO> list = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			list = session.selectList("board.getAllBoardList");
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		
		return list;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		SqlSession session = null;
		List<JdbcBoardVO> list = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			list = session.selectList("board.getSearchBoardList", title);
		} finally {
			// TODO: handle finally clause
			session.close();
		}
		
		return list;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.update("board.setCountIncrement", boardNo);
		} finally {
			// TODO: handle finally clause
			session.commit();
			session.close();
		}
		
		return cnt;
	}

}
