package prod.vo;

public class CartVO {
	private Integer productID;
	private String prodName;
	private Integer prodPrice;
	private Integer prodCount;
	private String prodImg;
	private Integer size;
	
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getProdCount() {
		return prodCount;
	}
	public void setProdCount(Integer prodCount) {
		this.prodCount = prodCount;
	}
	

	public String getProdImg() {
		return prodImg;
	}
	public void setProdImg(String prodImg) {
		this.prodImg = prodImg;
	}
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
