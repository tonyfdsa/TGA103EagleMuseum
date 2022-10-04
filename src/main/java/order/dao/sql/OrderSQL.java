package order.dao.sql;

public class OrderSQL {
	//查詢訂單By狀態
	public static final String orderGetByStatus ="select * from `order` where orderStatus=?"; 
	
	//更新狀態By OrderID
	public static final String updateStatus ="update `order` set orderStatus= ? where orderID = ?";

}
