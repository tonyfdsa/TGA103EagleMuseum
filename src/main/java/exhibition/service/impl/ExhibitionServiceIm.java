package exhibition.service.impl;

import java.util.Base64;
import java.util.List;

import contact.vo.QuesContent;
import exhibition.common.Result;
import exhibition.dao.impl.ExhibitionDAOIm;
import exhibition.service.inft.ExhibitionServiceIn;
import exhibition.vo.ExhibitionVO;
import exhibition.vo.ExhibitionVOo;

public class ExhibitionServiceIm implements ExhibitionServiceIn {

	private ExhibitionDAOIm DAO;
	// RESTFUL
	private Result R;

	public ExhibitionServiceIm() {
		DAO = new ExhibitionDAOIm();
		R = new Result();
	}

	@Override
	public Result getAll() {
		try {
			return R.success(DAO.getAll());
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	@Override
	public Result getByName(String exhibitionName) {
		try {
			System.out.println(R.success(DAO.getByName(exhibitionName)));
			return R.success(DAO.getByName(exhibitionName));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	@Override
	public Result getById(Integer id) {
		return null;
	}
	
	@Override
	public Result getByDate(String exhibitionStartDate, String exhibitionEndDate) {
		try {
			System.out.println(R.success(DAO.getByDate(exhibitionStartDate, exhibitionEndDate)));
			return R.success(DAO.getByDate(exhibitionStartDate, exhibitionEndDate));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	
	@Override
	public Result insert(ExhibitionVOo vo) {
		try {
			return R.success(DAO.insert(vo));
//			return R.success(DAO.updateImg(Base64.getDecoder().decode(img), id));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	

}
