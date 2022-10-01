package order.service.impl;

import order.dao.impl.OrderDAOimpl;
import order.service.inft.OrderServiceinft;
import prod.common.Result;
import prod.dao.impl.ProductDAO;

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

	

}
