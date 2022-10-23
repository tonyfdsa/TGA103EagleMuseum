package prod.service.impl;



import java.util.Base64;
import java.util.List;

import prod.common.Global;
import prod.common.Result;
import prod.dao.impl.ProductDAO;
import prod.service.inft.ProductServicein;
import prod.vo.CartVO;
import prod.vo.ProdImgVO;
import prod.vo.productVO;

public class ProductServicelm implements ProductServicein {

	private ProductDAO DAO;
	private Result R;
	
	public ProductServicelm() {
		DAO = new ProductDAO();
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
	public Result update(productVO productVO) {
		try {
			return R.success(DAO.update(productVO));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	@Override
	public Result insert(productVO productVO) {
		try {
			String  ProdName = productVO.getProdName();
			int	ProdTypeID = productVO.getProdTypeID();
			int ProdPrice = productVO.getProdPrice();
			String  ProdDescription = productVO.getProdDescription();
			int  ProdInStock = productVO.getProdInStock();
//			
			System.out.println(ProdName);
			System.out.println(ProdTypeID);

			
			if (ProdName != null && ProdTypeID != 0 && ProdInStock != 0 && ProdName != null && ProdDescription != null) {
				return R.success(DAO.insert(productVO));
			}else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	@Override
	public Result updateStatus(productVO productVO) {
		try {
			return R.success(DAO.updateStatus(productVO));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	@Override
	public Result insertTag(String prodType) {
		try {
			return R.success(DAO.insertTag(prodType));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	@Override
	public Result tagGetAll() {
		try {
			return R.success(DAO.prodTagGetAll());
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
	public Result prodUpdate(productVO ProductVO) {
		try {
			return R.success(DAO.prodUpdate(ProductVO));
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
	public List<ProdImgVO> getBase64(List<ProdImgVO> list) {

		for (ProdImgVO vo : list) {
			var img = vo.getProductgetimg();
			if (img != null) {
				vo.setProductimg(Global.BASE64 + Base64.getEncoder().encodeToString(img));
				vo.setProductgetimg(null);
			}
		}

		return list;
	}

	@Override
	public CartVO cartGetProd(Integer prodID) {
		try {
			return DAO.cartgetProd(prodID);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Result prodGetListed() {
		try {
			return R.success(DAO.prodListed());
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}


}
