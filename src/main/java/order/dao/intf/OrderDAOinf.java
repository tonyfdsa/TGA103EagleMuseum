package order.dao.intf;

import java.util.List;

import order.vo.orderVO;
import prod.vo.productVO;

public interface OrderDAOinf {
	
//根據訂單狀態查詢商品
public List<orderVO> statusget(Integer orderStatus) throws Exception;

//更新訂單狀態
public Integer statupdate(Integer orderID ,Integer status) throws Exception;
}
