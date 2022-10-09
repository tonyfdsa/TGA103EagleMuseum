package order.service.inft;


import java.util.List;

import prod.common.Result;
import prod.vo.CartVO;

public interface OrderServiceinft {
	//取得BY狀態
	public Result getbyStatus(Integer Status);
	
	//更改狀態
	public Result updateStatus(Integer orderID, Integer status);
	
	//結帳
	public Result insertOrder(Integer amountPrice, String deliveryAddress,List<CartVO> map);
}
