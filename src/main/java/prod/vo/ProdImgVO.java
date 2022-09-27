package prod.vo;

import java.sql.Date;

public class ProdImgVO {
	private Integer productImgID;
	private Integer productID;
	private String productimg;
	private byte[] productgetimg;
	private Date lastUpdateTime;
	
	
	public Integer getProductImgID() {
		return productImgID;
	}
	public void setProductImgID(Integer productImgID) {
		this.productImgID = productImgID;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public String getProductimg() {
		return productimg;
	}
	public void setProductimg(String productimg) {
		this.productimg = productimg;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public byte[] getProductgetimg() {
		return productgetimg;
	}
	public void setProductgetimg(byte[] productgetimg) {
		this.productgetimg = productgetimg;
	}

}
