package ticket.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ticket")
public class TicketVO {
	public TicketVO() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer ticketID;
	@Column
	private Integer memberID;
	@Column
	private Integer exhibitionID;
	@Column
	private String ticketDate;
	@Column
	private Integer aldultTicket;
	@Column
	private Integer stuTicket;
	@Column
	private Integer oldTicket;
	@Column
	private Integer phyTicket;
	@Column
	private Integer ticketTotal;
	@Column
	private Integer ticketStatus;
	@Column
	private String buyTime;
	@Column
	private String lastUpdateTime;
	
	
	public Integer getTicketID() {
		return ticketID;
	}
	public void setTicketID(Integer ticketID) {
		this.ticketID = ticketID;
	}
	public Integer getMemberID() {
		return memberID;
	}
	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}
	public Integer getExhibitionID() {
		return exhibitionID;
	}
	public void setExhibitionID(Integer exhibitionID) {
		this.exhibitionID = exhibitionID;
	}
	public String getTicketDate() {
		return ticketDate;
	}
	public void setTicketDate(String ticketDate) {
		this.ticketDate = ticketDate;
	}
	public Integer getAldultTicket() {
		return aldultTicket;
	}
	public void setAldultTicket(Integer aldultTicket) {
		this.aldultTicket = aldultTicket;
	}
	public Integer getStuTicket() {
		return stuTicket;
	}
	public void setStuTicket(Integer stuTicket) {
		this.stuTicket = stuTicket;
	}
	public Integer getOldTicket() {
		return oldTicket;
	}
	public void setOldTicket(Integer oldTicket) {
		this.oldTicket = oldTicket;
	}
	public Integer getPhyTicket() {
		return phyTicket;
	}
	public void setPhyTicket(Integer phyTicket) {
		this.phyTicket = phyTicket;
	}
	public Integer getTicketTotal() {
		return ticketTotal;
	}
	public void setTicketTotal(Integer ticketTotal) {
		this.ticketTotal = ticketTotal;
	}
	public Integer getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(Integer ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	
}
