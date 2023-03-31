package kr.or.ddit.basic.json;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;
import kr.or.ddit.vo.LprodVO;

public class LprodDaoImpl implements ILprodDao {
	private static LprodDaoImpl dao;
	
	public LprodDaoImpl() {};
	
	public static LprodDaoImpl getDao() {
		if (dao == null) {
			dao = new LprodDaoImpl();
		}
		
		return dao;
	}

	@Override
	public List<LprodVO> getLprodList() {
		// TODO Auto-generated method stub
		SqlSession session = null;
		
		List<LprodVO> list = null;
		
		try {
			session = MybatisSqlSessionFactory.getSqlSession();
			
			list = session.selectList("lprod.getAllLprod");
			
		} finally {
			// TODO: handle finally clause
			session.commit();
			session.close();
		}
		
		return list;
	}
	
}
