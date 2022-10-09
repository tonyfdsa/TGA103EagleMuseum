package order.service.impl;


import java.util.List;
import order.dao.impl.OrderDAOimpl;
import order.service.inft.OrderServiceinft;
import prod.common.Result;
import prod.vo.CartVO;

public class OrderServiceImpl implements OrderServiceinft{
	private OrderDAOimpl DAO;
	private Result R;
	
	public OrderServiceImpl() {
		DAO = new OrderDAOimpl();
		R = new Result();
	}
	@Override
	public Result getbyStatus(Integer Status) {
		try {
			return R.success(DAO.statusget(Status));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	@Override
	public Result updateStatus(Integer orderID, Integer status) {
		try {
			return R.success(DAO.statupdate(orderID, status));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	@Override
	public Result insertOrder(Integer amountPrice, String deliveryAddress ,List<CartVO> valueList) {
		//假設已經登入的member為1
		Integer mem = 1;
		//未登入失敗
		if(mem == null) {
			return R.fail("未登入");
		}
		//檢查購物車商品
		if(valueList == null) {
			return R.fail("購物車內無商品");
		}
		
		
		
			
			try {
				//產生訂單
				Integer orderID	= DAO.insertOrder(mem,  deliveryAddress,amountPrice);
				for(int i = 0 ; i < valueList.size() ; i++) {
					//每筆商品都產生一條明細
					CartVO List = valueList.get(i);
					DAO.insertOrderDetail(List, orderID);
				}
				return R.success("訂單產生成功");
				
			} catch (Exception e) {
				e.printStackTrace();
				return R.fail(e.toString());
	


	}

	}

}
