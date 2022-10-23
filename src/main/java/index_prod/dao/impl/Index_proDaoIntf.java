package index_prod.dao.impl;

import java.sql.SQLException;
import java.util.List;

import index_exhibition.vo.Index_productImgVo;
import index_exhibition.vo.Index_productListVo;

public interface Index_proDaoIntf {

	//查詢商品
	public List<Index_productListVo> getAll() throws Exception;
	
	//根據商品名稱查詢
	public List<Index_productListVo> getByName(String productName) throws SQLException;

	//更新商品狀態
	public Integer updateStatus(Index_productListVo Index_productListVo) throws Exception;
	
	//查詢商品by編號
	public List<Index_productListVo> prodGetByID(Integer productID) throws Exception;

	//圖片商品上傳
	public Integer insertProdImg(byte[] img, Integer id) throws Exception;

	//商品圖片取得
	public List<Index_productImgVo> prodGetImg(Integer prodID) throws Exception;

	//刪除圖片
	public int prodDeImg(Integer prodID) throws Exception;

	//取得所有圖片
	public List<Index_productImgVo> prodImgGetAll() throws Exception;

	//文字上傳資料庫
	public List<Index_productListVo> insertProdDescription(String Description) throws Exception;

}