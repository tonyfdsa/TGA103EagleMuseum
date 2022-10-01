package order.vo;

import java.sql.Date;

public class orderVO {
private Integer order;
private Integer orderID;
private Integer memberId;
private Integer orderAmount;
private Integer orderStatus;
private String deliveryAddress;
private Integer freight;
private String memo;
public Integer getOrder() {
	return order;
}
public void setOrder(Integer order) {
	this.order = order;
}
public Integer getOrderID() {
	return orderID;
}
public void setOrderID(Integer orderID) {
	this.orderID = orderID;
}
public Integer getOrderAmount() {
	return orderAmount;
}
public void setOrderAmount(Integer orderAmount) {
	this.orderAmount = orderAmount;
}
public Integer getOrderStatus() {
	return orderStatus;
}
public void setOrderStatus(Integer orderStatus) {
	this.orderStatus = orderStatus;
}
public String getDeliveryAddress() {
	return deliveryAddress;
}
public void setDeliveryAddress(String deliveryAddress) {
	this.deliveryAddress = deliveryAddress;
}
public Integer getFreight() {
	return freight;
}
public void setFreight(Integer freight) {
	this.freight = freight;
}
public String getMemo() {
	return memo;
}
public void setMemo(String memo) {
	this.memo = memo;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}
public Date getLastUpdateTime() {
	return lastUpdateTime;
}
public void setLastUpdateTime(Date lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
}
public Integer getMemberId() {
	return memberId;
}
public void setMemberId(Integer memberId) {
	this.memberId = memberId;
}
private Date createTime;
private Date lastUpdateTime;


}
