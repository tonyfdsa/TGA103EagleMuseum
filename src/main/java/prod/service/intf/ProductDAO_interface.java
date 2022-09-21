package prod.service.intf;

import java.sql.SQLException;
import java.util.List;

import prod.vo.productVO;

public interface ProductDAO_interface {
	
	//新增商品
	public void insert(productVO productVO) throws Exception;
	
  //更新商品資訊
    public void update(productVO productVO) throws Exception;
    
  //查詢商品
    public List<productVO> getAll() throws Exception;
    
  //根據商品名稱查詢
    public List<productVO> getByName(String productName) throws SQLException;
    
  //根據商品編號搜尋
    public List<productVO> getByProductID(String productID) throws SQLException;
}