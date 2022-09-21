package prod.vo;

import java.sql.Date;

public class ProdTypeVO {
	public Integer getProdTypeId() {
		return prodTypeId;
	}
	public void setProdTypeId(Integer prodTypeId) {
		this.prodTypeId = prodTypeId;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	private Integer prodTypeId;
	private String prodType;
	private Date lastUpdateTime;
}
