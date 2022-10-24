package order.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "orderstatus")
public class OrderTagVO {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer orderStatusID;
@Column
private String orderStatus;
@Column
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
