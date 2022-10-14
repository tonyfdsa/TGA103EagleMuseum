package index_prod.dao.sql;

public class Index_productSQL {
	    // 全部查詢
		public static final String GET_ALL = "SELECT * FROM index_productList";

		// 商品名稱查詢
		public static final String GET_BY_Name = "SELECT * FROM eaglemuseum_schema.index_productList where prodName like ?";

		// 商品ID名稱查詢
		public static final String GET_BY_ID = "SELECT * FROM index_productList where productID = ?";
		
		// 更新狀態
		public static final String UpdateStatus = "UPDATE index_productList set prodStatus=? where productID=?";

		// 商品圖片上傳
		public static final String InsertProdImg = "INSERT INTO index_productImg(productID, productImg, lastUpdateTime) VALUES(?,?,NOW())";
		
		//上傳圖片取得
		public static final String GetImgByID = "select * from index_productImg where productID=?";
		
		//刪除圖片
		public static final String ProdDeImg = "delete from index_productImg where productID= ?";
		
		//取得所有圖片
		public static final String GetAllImg = "select * from index_productImg ";
		
		//新增文字進資料庫
		public static final String InsertWord = "INSERT INTO index_productList( prodName, prodDescription, prodStatus, bestSeller) VALUES( ?, ?, ?, ?)";
	

}

