package index_exhibition_dto;

import java.time.LocalDateTime;

public class IndexExhibitionDto {
	private Long exhibitionID;
	private String exhibitionName;
	private String exhibitionArticle;
	private String exhibitionStartDate;
	private String exhibitionEndDate;
	private String exhibitionImageBase64;
	
	@Override
	public String toString() {
		return "IndexExhibitionDto [exhibitionID=" + exhibitionID + ", exhibitionName=" + exhibitionName
				+ ", exhibitionArticle=" + exhibitionArticle + ", exhibitionStartDate=" + exhibitionStartDate
				+ ", exhibitionEndDate=" + exhibitionEndDate + ", exhibitionImageBase64=" + exhibitionImageBase64 + "]";
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
	public String getExhibitionStartDate() {
		return exhibitionStartDate;
	}
	public void setExhibitionStartDate(String exhibitionStartDate) {
		this.exhibitionStartDate = exhibitionStartDate;
	}
	public String getExhibitionEndDate() {
		return exhibitionEndDate;
	}
	public void setExhibitionEndDate(String exhibitionEndDate) {
		this.exhibitionEndDate = exhibitionEndDate;
	}
	public String getExhibitionImageBase64() {
		return exhibitionImageBase64;
	}
	public void setExhibitionImageBase64(String exhibitionImageBase64) {
		this.exhibitionImageBase64 = exhibitionImageBase64;
	}
	
}
