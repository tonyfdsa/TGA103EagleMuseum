package prod.vo;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "productlist")
public class productVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productID;
	@Column
	private String prodName;
	@Column
	private Integer prodTypeID;
	@Column
	private Integer prodPrice; 
	@Column
	private Integer discountID;
	@Column
	private String prodDescription; 
	@Column(insertable = false)
	private Integer prodStatus; 
	@Column(insertable = false)
	private Integer sellQuantity; 
	@Column
	private Integer prodInStock; 
	@Column(insertable = false)
	private Integer bestSeller; 
	@Column(insertable = false)
	private Timestamp lastUpdateTime;
	@Transient
	private String prodImg;
	
	

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
	public Integer getProdTypeID() {
		return prodTypeID;
	}
	public void setProdTypeID(Integer prodTypeID) {
		this.prodTypeID = prodTypeID;
	}
	public Integer getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(Integer prodPrice) {
		this.prodPrice = prodPrice;
	}
	public Integer getDiscountID() {
		return discountID;
	}
	public void setDiscountID(Integer discountID) {
		this.discountID = discountID;
	}
	public String getProdDescription() {
		return prodDescription;
	}
	public void setProdDescription(String prodDescription) {
		this.prodDescription = prodDescription;
	}
	public Integer getProdStatus() {
		return prodStatus;
	}
	public void setProdStatus(Integer prodStatus) {
		this.prodStatus = prodStatus;
	}


	public Integer getSellQuantity() {
		return sellQuantity;
	}
	public void setSellQuantity(Integer sellQuantity) {
		this.sellQuantity = sellQuantity;
	}
	public Integer getProdInStock() {
		return prodInStock;
	}
	public void setProdInStock(Integer prodInStock) {
		this.prodInStock = prodInStock;
	}
	public Integer getBestSeller() {
		return bestSeller;
	}
	public void setBestSeller(Integer bestSeller) {
		this.bestSeller = bestSeller;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	


}
