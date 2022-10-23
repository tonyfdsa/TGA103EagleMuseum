package ticket.vo;


public class TicketVOo {
	public TicketVOo() {}

	private Integer ticketID;
	private Integer memberID;
	private Integer exhibitionID;
	private String ticketDate;
	private Integer aldultTicket;
	private Integer stuTicket;
	private Integer oldTicket;
	
	
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

	private Integer phyTicket;
	private Integer ticketTotal;
	private Integer ticketStatus;
	private String buyTime;
	private String lastUpdateTime;

}
