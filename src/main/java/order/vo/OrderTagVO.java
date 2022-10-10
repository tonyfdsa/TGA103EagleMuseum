package order.vo;

import java.sql.Date;

public class OrderTagVO {
private Integer orderStatusID;
private String orderStatus;
private Date lastUpdateTime;
public Integer getOrderStatusID() {
	return orderStatusID;
}
public void setOrderStatusID(Integer orderStatusID) {
	this.orderStatusID = orderStatusID;
}
public String getOrderStatus() {
	return orderStatus;
}
public void setOrderStatus(String orderStatus) {
	this.orderStatus = orderStatus;
}
public Date getLastUpdateTime() {
	return lastUpdateTime;
}
public void setLastUpdateTime(Date lastUpdateTime) {
	this.lastUpdateTime = lastUpdateTime;
}
}
