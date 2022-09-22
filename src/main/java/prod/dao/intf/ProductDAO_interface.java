package prod.dao.intf;

import java.sql.SQLException;
import java.util.List;

import prod.vo.ProdTypeVO;
import prod.vo.productVO;

public interface ProductDAO_interface {
	
	//新增商品
	public productVO insert(productVO productVO) throws Exception;
	
  //更新商品資訊
    public productVO update(productVO productVO) throws Exception;
    
  //查詢商品
    public List<productVO> getAll() throws Exception;
    
  //根據商品名稱查詢
    public List<productVO> getByName(String productName) throws SQLException;
       
  //更新商品狀態
    public Integer updateStatus(productVO productVO) throws Exception;
    
  //新增商品標籤    
    public Integer insertTag(String prodType) throws Exception;
    
  //查詢商品標籤
    public List<ProdTypeVO> prodTagGetAll() throws Exception;
    
  //查詢商品by編號  
	List<productVO> prodGetByID(Integer productID) throws Exception;
}