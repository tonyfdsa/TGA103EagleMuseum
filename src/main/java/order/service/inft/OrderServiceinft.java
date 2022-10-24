package order.service.inft;


import java.util.List;

import order.vo.OrderVO;
import prod.common.Result;
import prod.vo.CartVO;

public interface OrderServiceinft {
	//取得BY狀態
	public Result getbyStatus(Integer Status);
	
	//更改狀態
	public Result updateStatus(Integer orderID, Integer status);
	
	//結帳
	public Result insertOrder(OrderVO VO,List<CartVO> map);
	
	//取得所有訂單資訊
	public Result orderGetAll();
	
	//取得所有訂單狀態
	public Result orderTagGetAll();
	
	//根據訂單編號取得明細
	public Result getOrderDetailByID(Integer prodID);
	
	//取得訂單根據memID
	public Result getByMem(Integer memID);
	
	//取得訂單By mem和stat
	public Result OrdergetbyMemStat(OrderVO orderVO);
}
