package prod.service.inft;

import prod.common.Result;
import prod.vo.productVO;

public interface ProductServicein {
	
	public Result getAll() throws Exception;
	
	public Result getByName(String prodName);
	
	public Result insert(productVO productVO);
	
	public Result update(productVO productVO);
	
	public Result updateStatus(productVO productVO);
}
