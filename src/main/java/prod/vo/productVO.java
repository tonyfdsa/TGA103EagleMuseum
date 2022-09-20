package prod.vo;
import java.sql.Date;

public class productVO {
	private Integer productID;
	private String prodName;   
	private Integer prodTypeID; 
	private Integer prodPrice; 
	private Integer discountID; 
	private String prodDescription; 
	private Integer prodStatus;
	private Date creatTime; 
	private Date launchTime; 
	private Integer sellQuantity; 
	private Integer prodInStock; 
	private Integer bestSeller; 
	private Date lastUpdateTime;
	
	
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
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public Date getLaunchTime() {
		return launchTime;
	}
	public void setLaunchTime(Date launchTime) {
		this.launchTime = launchTime;
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
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

		
	


}
