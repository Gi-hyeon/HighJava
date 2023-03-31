package kr.or.ddit.basic.json;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisSqlSessionFactory;
import kr.or.ddit.vo.LprodVO;

public class LprodServiceImpl implements ILprodService {
	private ILprodDao dao;
	private static LprodServiceImpl Service;
	
	public LprodServiceImpl() {
		dao = LprodDaoImpl.getDao();
	};
	
	public static LprodServiceImpl getService() {
		if (Service == null) 
			Service = new LprodServiceImpl();
		
		return Service;
	}

	@Override
	public List<LprodVO> getLprodList() {
		// TODO Auto-generated method stub
		return dao.getLprodList();
	}
	
}
