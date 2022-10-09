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
			
}
