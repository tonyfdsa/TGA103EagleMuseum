package order.service.inft;

import prod.common.Result;

public interface OrderServiceinft {
	//取得BY狀態
	public Result getbyStatus(Integer Status);
	
	public Result updateStatus(Integer orderID, Integer status);
}
