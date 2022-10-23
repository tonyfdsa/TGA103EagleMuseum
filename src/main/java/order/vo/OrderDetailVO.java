package order.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetails")
public class OrderDetailVO {
	@Id
	@Column
	private Integer orderDetailsID;
	@Column
	private Integer productID;
	@Column
	private Integer orderID;
	@Column
	private Integer prodQuantity;
	@Column
	private Integer prodPrice;
	@Column
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
