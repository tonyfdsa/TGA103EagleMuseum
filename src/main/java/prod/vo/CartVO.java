package prod.vo;

public class CartVO {
	private Integer productID;
	private String prodName;
	public Integer getProdCount() {
		return prodCount;
	}
	public void setProdCount(Integer prodCount) {
		this.prodCount = prodCount;
	}
	private Integer prodPrice;
	private Integer prodCount;
	
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Integer getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}

}
