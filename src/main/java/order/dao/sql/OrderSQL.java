package order.dao.sql;

public class OrderSQL {
	//查詢訂單By狀態
	public static final String orderGetByStatus ="select * from `order` where orderStatus=?"; 
	
	//更新狀態By OrderID
	public static final String updateStatus ="update `order` set orderStatus= ? where orderID = ?";
	
	//新增訂單 
	public static final String insertOrder =
			"insert into `order`(memberID, orderAmount, orderStatus, deliveryAddress, freight, memo, createTime) "
			+ "values(? , ? , \"1\", ? , \"60\", \"無\", Now())";
	
	public static final String insertOrderDetail =
				"insert into `orderdetails`(productID,orderID,prodQuantity) "
		
						+ "values(? , ? , ?)";
	//取得商品BYID
	public static final String prodStGetByID =
			"select  prodInStock, sellQuantity from productlist where productID= ? ";
	
//	更新產品庫存和銷售數量
	public static final String prodStUpdate =
			"UPDATE productlist set prodInStock=? , sellQuantity=? where productID = ?";
	
	//取得所有訂單
	public static final String orderGetAll =
			"select * from `order` ";
	
	//取得所有訂單狀態標籤
	public static final String orderTagGetAll =
			"select * from `orderstatus`" ;
}
