package exhibition.service.impl;

import java.util.Base64;
import java.util.List;

import exhibition.common.Result;
import exhibition.dao.impl.ExhibitionDAOIm;
import exhibition.service.inft.ExhibitionServiceIn;
import exhibition.vo.ExhibitionVO;
import exhibition.vo.ExhibitionVOo;
import prod.common.Global;

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
			return R.success(getBase64(DAO.getAll()));
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
	public Result getById(Integer exhibitionID) {
		try {
			System.out.println(R.success(getBase64(DAO.getById(exhibitionID))));
			return R.success(getBase64(DAO.getById(exhibitionID)));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
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
	@Override
	public Result updateImg(String img, Integer id) {

		try {
			return R.success(DAO.updateImg(Base64.getDecoder().decode(img), id));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	
	@Override
	public Result delete(Integer id) {
		try {
			return R.success(DAO.delete(id));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	
	public List<ExhibitionVO> getBase64(List<ExhibitionVO> list) {

		for (ExhibitionVO vo : list) {
			var img = vo.getExhibitionImg();
			if (img != null) {
				vo.setExhibitionImgBase64(Global.BASE64 + Base64.getEncoder().encodeToString(img));
				vo.setExhibitionImg(null);
			}
		}

		return list;
	}
	

}
