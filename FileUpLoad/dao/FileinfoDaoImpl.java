package kr.or.ddit.basic.fileupload.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;
import kr.or.ddit.vo.FileinfoVO;

public class FileinfoDaoImpl implements IFileinfoDao {
	private static FileinfoDaoImpl dao;
	
	private FileinfoDaoImpl() { }
	
	public static FileinfoDaoImpl getDao() {
		if (dao == null) {
			dao = new FileinfoDaoImpl();
		}
		return dao;
	}
	
	@Override
	public int insertFileinfo(FileinfoVO fileVo) {
		// TODO Auto-generated method stub
		SqlSession session = null;
		int cnt = 0;		// 반환값이 저장될 변수
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			cnt = session.insert("fileinfo.insertFileinfo", fileVo);
		} finally {
			// TODO: handle finally clause
			session.commit();
			if (session != null) { session.close(); }
		}
		
		return cnt;
	}

	@Override
	public List<FileinfoVO> getAllFileinfo() {
		// TODO Auto-generated method stub
		SqlSession session = null;
		List<FileinfoVO> fileList = null; // 반환값
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			fileList = session.selectList("fileinfo.getAllFileinfo");
		} finally {
			// TODO: handle finally clause
			session.commit();
			if (session != null) { session.close(); }
		}
		
		return fileList;
	}

	@Override
	public FileinfoVO getFileinfo(int fileNo) {
		// TODO Auto-generated method stub
		SqlSession session = null;
		FileinfoVO fileVo = null; // 반환값
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			fileVo = session.selectOne("fileinfo.getFileinfo", fileNo);
		} finally {
			// TODO: handle finally clause
			session.commit();
			if (session != null) { session.close(); }
		}
		
		return fileVo;
	}

}
























