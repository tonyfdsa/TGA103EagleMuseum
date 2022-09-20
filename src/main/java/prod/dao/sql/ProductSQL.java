package prod.dao.sql;

public class ProductSQL {
	//全部查詢
	public static final String GET_ALL = "SELECT * FROM productlist";
	
	//商品名稱查詢
	public static final String GET_BY_Name = "SELECT * FROM eaglemuseum_schema.productlist where prodName like ?";
	
	//商品名稱查詢
	public static final String GET_BY_ID = "SELECT * FROM productlist where prodName = ?";

	//新增商品
	public static final String Insert  =
		"INSERT INTO productlist(prodName, ProdTypeID, prodprice, prodDescription, prodInStock, creatTime) VALUES(?, ?, ?, ?, ?,NOW())";
	
//	productID, prodName, prodTypeID, prodPrice, discountID, prodDescription, prodStatus, creatTime, launchTime, sellQuantity, prodInStock, bestSeller, lastUpdateTime
	//更新商品
	public static final String Update  =
			"UPDATE productlist set prodName=? , prodTypeID=? , "
			+ "discountID=? , prodDescription=? , prodStatus=? , sellQuantity=? , prodInStock=? ,"
			+ " bestSeller=? , lastUpdateTime=NOW() where prodTypeID=? where prodTypeID=? " ;
	
	
	public static final String UpdateStatus  =
			"UPDATE productlist set prodStatus=? where productID=?";

}
