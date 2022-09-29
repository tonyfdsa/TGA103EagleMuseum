package exhibition.service.impl;

import exhibition.common.Result;
import exhibition.dao.impl.ExhibitionDAOIm;
import exhibition.service.inft.ExhibitionServiceIn;

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

}
