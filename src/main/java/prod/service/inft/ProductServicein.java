package prod.service.inft;

import prod.common.Result;
import prod.vo.productVO;

public interface ProductServicein {
	
	public Result getAll() ;
	
	public Result getByName(String prodName);
	
	public Result insert(productVO productVO);
	
	public Result update(productVO productVO);
	
	public Result updateStatus(productVO productVO);
	
	public Result insertTag(String prodType);
	
	public Result tagGetAll() ;
	
	public Result prodGetByID(int prodID) ;
	
	public Result insertProdImg(String img, Integer id);
	
	public Result prodGetImg(Integer id);
	
	public Result prodUpdate(productVO ProductVO);
	
	public Result prodDeImg(Integer id);
	
}
