package order.service.impl;


import java.util.List;

import order.dao.impl.OrderDAOimpl;
import order.service.inft.OrderServiceinft;
import order.vo.OrderVO;
import prod.common.Result;
import prod.vo.CartVO;
import prod.vo.productVO;

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
	public Result insertOrder(OrderVO VO ,List<CartVO> valueList) {
		//假設已經登入的member為1
		
		//未登入失敗
		if(VO.getMemberId() == null) {
			return R.fail("未登入");
		}
		//檢查購物車商品
		if(valueList == null) {
			return R.fail("購物車內無商品");
		}
		
		
		//檢查每一條明細的商品數量與庫存數量
		for(int i = 0 ; i < valueList.size() ; i++) {
			
			CartVO List = valueList.get(i);
			Integer amount;
			try {
				amount = DAO.prodStGetbyID(List.getProductID()).getProdInStock();
			} catch (Exception e) {
				e.printStackTrace();
				return R.fail(e.toString());
			}
			
			if( (amount- List.getProdCount() < 0)) {
				return R.fail("商品" + List.getProdName() + "庫存不足，請從購物車刪除該商品後重新嘗試購買");
			}
		}
		
			
			try {
				//產生訂單
				Integer orderID	= DAO.insertOrder(VO.getMemberId() , VO.getDeliveryAddress(), VO.getOrderAmount());
				for(int i = 0 ; i < valueList.size() ; i++) {;
					//每筆商品都產生一條明細
					CartVO List = valueList.get(i);
					productVO PVO = DAO.prodStGetbyID(List.getProductID());
					DAO.insertOrderDetail(List, orderID);
					//庫存減少
					DAO.prodStUpdate(PVO , List.getProdCount(), List.getProductID());
					
				}
				return R.success("訂單產生成功");
				
			} catch (Exception e) {
				e.printStackTrace();
				return R.fail(e.toString());
	


	}

	}
	@Override
	public Result orderGetAll() {
		try {
			return R.success(DAO.orderGetAll());
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	@Override
	public Result orderTagGetAll() {
		try {
			return R.success(DAO.orderTagGetAll());
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	@Override
	public Result getOrderDetailByID(Integer prodID) {
		try {
			return R.success(DAO.getOrderDetailByID(prodID));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}
	@Override
	public Result getByMem(Integer memID) {
		try {
			return R.success(DAO.getByMem(memID));
		} catch (Exception e) {
			e.printStackTrace();
			return R.fail(e.toString());
		}
	}

}
