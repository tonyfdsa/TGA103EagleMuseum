package index_exhibition.vo;

import java.time.LocalDateTime;

public class IndexExhibitionVo {
	private Long exhibitionID;
	private String exhibitionName;
	private String exhibitionArticle;
	private LocalDateTime exhibitionStartDate;
	private LocalDateTime exhibitionEndDate;
	private byte[] exhibitionImage;
	
	public IndexExhibitionVo() {
		super();
	}

	public IndexExhibitionVo(Long exhibitionID, String exhibitionName, String exhibitionArticle,
			LocalDateTime exhibitionStartDate, LocalDateTime exhibitionEndDate, byte[] exhibitionImage) {
		super();
		this.exhibitionID = exhibitionID;
		this.exhibitionName = exhibitionName;
		this.exhibitionArticle = exhibitionArticle;
		this.exhibitionStartDate = exhibitionStartDate;
		this.exhibitionEndDate = exhibitionEndDate;
		this.exhibitionImage = exhibitionImage;
	}
	
	@Override
	public String toString() {
		return "IndexExhibitionVo [exhibitionID=" + exhibitionID + ", exhibitionName=" + exhibitionName
				+ ", exhibitionArticle=" + exhibitionArticle + ", exhibitionStartDate=" + exhibitionStartDate
				+ ", exhibitionEndDate=" + exhibitionEndDate + "]";
	}

	public Long getExhibitionID() {
		return exhibitionID;
	}
	public void setExhibitionID(Long exhibitionID) {
		this.exhibitionID = exhibitionID;
	}
	public String getExhibitionName() {
		return exhibitionName;
	}
	public void setExhibitionName(String exhibitionName) {
		this.exhibitionName = exhibitionName;
	}
	public String getExhibitionArticle() {
		return exhibitionArticle;
	}
	public void setExhibitionArticle(String exhibitionArticle) {
		this.exhibitionArticle = exhibitionArticle;
	}
	public LocalDateTime getExhibitionStartDate() {
		return exhibitionStartDate;
	}
	public void setExhibitionStartDate(LocalDateTime exhibitionStartDate) {
		this.exhibitionStartDate = exhibitionStartDate;
	}
	public LocalDateTime getExhibitionEndDate() {
		return exhibitionEndDate;
	}
	public void setExhibitionEndDate(LocalDateTime exhibitionEndDate) {
		this.exhibitionEndDate = exhibitionEndDate;
	}
	public byte[] getExhibitionImage() {
		return exhibitionImage;
	}
	public void setExhibitionImage(byte[] exhibitionImage) {
		this.exhibitionImage = exhibitionImage;
	}

}
