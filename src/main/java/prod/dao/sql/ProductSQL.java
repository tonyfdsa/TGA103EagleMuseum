package prod.dao.sql;

public class ProductSQL {
	// 全部查詢
	public static final String GET_ALL = "SELECT * FROM productlist";

	// 商品名稱查詢
	public static final String GET_BY_Name = "SELECT * FROM eaglemuseum_schema.productlist where prodName like ?";

	// 商品ID名稱查詢
	public static final String GET_BY_ID = "SELECT * FROM productlist where productID = ?";

	// 新增商品
	public static final String Insert = "INSERT INTO productlist(prodName, ProdTypeID, prodprice, prodDescription, prodInStock, creatTime) VALUES(?, ?, ?, ?, ?,NOW())";

//	productID, prodName, prodTypeID, prodPrice, discountID, prodDescription, prodStatus, creatTime, launchTime, sellQuantity, prodInStock, bestSeller, lastUpdateTime
	// 更新商品
	public static final String Update = "UPDATE productlist set prodName=? , prodTypeID=? , "
			+ "discountID=? , prodDescription=? , prodStatus=? , sellQuantity=? , prodInStock=? ,"
			+ " bestSeller=? , lastUpdateTime=NOW() where prodTypeID=? where prodTypeID=? ";

	// 更新狀態
	public static final String UpdateStatus = "UPDATE productlist set prodStatus=? where productID=?";

	// 新增標籤
	public static final String InsertTag = "INSERT INTO producttype(prodType, lastUpdateTime) VALUES(?,NOW())";

	// 商品標籤GET_ALL
	public static final String TagGET_ALL = "SELECT * FROM producttype";
	
	// 商品圖片上傳
	public static final String InsertProdImg = "INSERT INTO productimg(productID, productimg, lastUpdateTime) VALUES(?,?,NOW())";
	
	//上傳圖片取得
	public static final String GetImgByID = "select * from productimg where productID=?";
	
	//商品更新
	public static final String UpdateProd = "update productlist set prodName=? , prodTypeID=?, prodPrice=?, prodDescription=?, prodStatus=?, prodInStock=?, lastUpdateTime= NOW() where productID=?";
	
	//刪除圖片
	public static final String ProdDeImg = "delete from productimg where productID= ?";
	
	//取得所有圖片
	public static final String GetAllImg = "select * from productimg ";
}
