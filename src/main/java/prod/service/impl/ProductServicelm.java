package prod.service.impl;



import prod.common.Result;
import prod.dao.impl.ProductDAO;
import prod.service.inft.ProductServicein;
import prod.vo.productVO;

public class ProductServicelm implements ProductServicein {

	private ProductDAO DAO;
	private Result R;
	
	public ProductServicelm() {
		DAO = new ProductDAO();
		R = new Result();
	}
	
	@Override
	public Result getAll() throws Exception {
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

			if (ProdName != null && ProdTypeID != 0 && ProdInStock != 0 && ProdName != null && ProdDescription != null) {
				System.out.println(R.success(DAO.insert(productVO)));
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
			System.out.println(R.success(DAO.updateStatus(productVO)));
			return R.success(DAO.insert(productVO));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

	@Override
	public Result insertTag(String prodType) {
		try {
			System.out.println(R.success(DAO.insertTag(prodType)));
			return R.success(DAO.insertTag(prodType));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	

}
