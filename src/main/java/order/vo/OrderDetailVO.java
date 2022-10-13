package order.vo;

public class OrderDetailVO {
	private Integer orderDetailsID;
	private Integer productID;
	private Integer orderID;
	private Integer prodQuantity;
	private Integer prodPrice;
	private String prodName;
	public Integer getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Integer getOrderDetailsID() {
		return orderDetailsID;
	}
	public void setOrderDetailsID(Integer orderDetailsID) {
		this.orderDetailsID = orderDetailsID;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	public Integer getProdQuantity() {
		return prodQuantity;
	}
	public void setProdQuantity(Integer prodQuantity) {
		this.prodQuantity = prodQuantity;
	}
	
	

}
