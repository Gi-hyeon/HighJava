package kr.or.ddit.basic.fileupload.service;

import java.util.List;

import kr.or.ddit.basic.fileupload.dao.FileinfoDaoImpl;
import kr.or.ddit.basic.fileupload.dao.IFileinfoDao;
import kr.or.ddit.vo.FileinfoVO;

public class FileinfoServiceImpl implements IFileinfoService {
	private IFileinfoDao dao;
	private static FileinfoServiceImpl service;
	
	private FileinfoServiceImpl() {
		dao = FileinfoDaoImpl.getDao();
	}
	
	public static FileinfoServiceImpl getService() {
		if (service == null) {
			service = new FileinfoServiceImpl();
		}
		return service;
	}

	@Override
	public int insertFileinfo(FileinfoVO fileVo) {
		// TODO Auto-generated method stub
		return dao.insertFileinfo(fileVo);
	}

	@Override
	public List<FileinfoVO> getAllFileinfo() {
		// TODO Auto-generated method stub
		return dao.getAllFileinfo();
	}

	@Override
	public FileinfoVO getFileinfo(int fileNo) {
		// TODO Auto-generated method stub
		return dao.getFileinfo(fileNo);
	}
}
