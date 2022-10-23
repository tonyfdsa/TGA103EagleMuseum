package index_prod.service.impl;

import java.util.Base64;
import java.util.List;

import index_exhibition.vo.Index_productImgVo;
import index_exhibition.vo.Index_productListVo;
import index_prod.dao.impl.Index_prodDao;
import prod.common.Global;
import prod.common.Result;


public class Index_prodServiceImpl implements Index_prodServie_interface  {

	private Index_prodDao DAO;
	private Result R;
	
	public Index_prodServiceImpl() {
		DAO = new Index_prodDao();
		R = new Result();
	}
	
	
	@Override
	public Result getAll()  {
		try {
			return R.success(DAO.getAll());
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	
	@Override
	public Result getByName(String prodName) {
		try {
			System.out.println(R.success(DAO.getByName(prodName)));
			return R.success(DAO.getByName(prodName));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}


	
	@Override
	public Result updateStatus(Index_productListVo Index_productListVo) {
		try {
			System.out.println(R.success(DAO.updateStatus(Index_productListVo)));
			return R.success(DAO.updateStatus(Index_productListVo));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}



	@Override
	public Result prodGetByID(int prodID) {
		try {
			return R.success(DAO.prodGetByID(prodID));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
		
	}

	@Override
	public Result insertProdImg(String img, Integer id) {
		try {
			return R.success(DAO.insertProdImg(Base64.getDecoder().decode(img), id));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	
	@Override
	public Result prodGetImg(Integer id) {
		try {
			return R.success(getBase64(DAO.prodGetImg(id)));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	


	@Override
	public Result prodDeImg(Integer id) {
		try {
			return R.success(DAO.prodDeImg(id));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	
	@Override
	public Result prodGetAllImg() {
		try {
			return R.success(getBase64(DAO.prodImgGetAll()));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}



	//工具
	@Override
	public List<Index_productImgVo> getBase64(List<Index_productImgVo> list) {

		for (Index_productImgVo vo : list) {
//			var img = vo.getProductgetimg();
//			if (img != null) {
//				vo.setProductimg(Global.BASE64 + Base64.getEncoder().encodeToString(img));
//				vo.setProductgetimg(null);
//			}
		}

		return list;
	}



	@Override
	public Result insertProdWord(String description) {
		try {
			return R.success(DAO.insertProdDescription(description));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
}

