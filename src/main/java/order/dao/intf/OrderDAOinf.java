package order.dao.intf;

import java.util.List;

import order.vo.OrderTagVO;
import order.vo.OrderVO;
import prod.vo.CartVO;
import prod.vo.productVO;

public interface OrderDAOinf {
	
//根據訂單狀態查詢商品
public List<OrderVO> statusget(Integer orderStatus) throws Exception;

//更新訂單狀態
public Integer statupdate(Integer orderID ,Integer status) throws Exception;

//新增訂單(要輸入總金額和會員)
public Integer insertOrder(Integer member, String deliveryAddress , Integer amount) throws Exception;


//取得商品的庫存BYID
public productVO prodStGetbyID(Integer ID)throws Exception;

//新增訂單明細(要輸入購物車商品VO)
public Integer insertOrderDetail(CartVO vo, Integer orderID) throws Exception;

//更新庫存
public Integer prodStUpdate(productVO vo, Integer buy, Integer prodID) throws Exception;

//取得所有訂單資訊
public List<OrderVO> orderGetAll() throws Exception;

//取得所有訂單狀態
public List<OrderTagVO> orderTagGetAll() throws Exception;

}
