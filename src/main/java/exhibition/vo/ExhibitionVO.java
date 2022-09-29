package exhibition.vo;

public class ExhibitionVO {
//	EXHIBITION_ID, EXHIBITION_TYPE, EXHIBITION_NAME, EXHIBITION_START_DATE, 
//	EXHIBITION_END_DATE, EXHIBITION_ARTICLE, EXHIBITION_STATUS, LAST_UPDATE_TIME
	
	private Integer exhibitionId;
	private Integer exhibitionType;
	private String exhibitionName;
	private String exhibitionStartDate;
	private String exhibitionEndDate;
	private String exhibitionArticle;
	private Integer exhibitionStatus;
	private String lastUpdateTime;
	
	
	public Integer getExhibitionId() {
		return exhibitionId;
	}
	public void setExhibitionId(Integer exhibitionId) {
		this.exhibitionId = exhibitionId;
	}
	public Integer getExhibitionType() {
		return exhibitionType;
	}
	public void setExhibitionType(Integer exhibitionType) {
		this.exhibitionType = exhibitionType;
	}
	public String getExhibitionName() {
		return exhibitionName;
	}
	public void setExhibitionName(String exhibitionName) {
		this.exhibitionName = exhibitionName;
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
	public String getExhibitionArticle() {
		return exhibitionArticle;
	}
	public void setExhibitionArticle(String exhibitionArticle) {
		this.exhibitionArticle = exhibitionArticle;
	}
	public Integer getExhibitionStatus() {
		return exhibitionStatus;
	}
	public void setExhibitionStatus(Integer exhibitionStatus) {
		this.exhibitionStatus = exhibitionStatus;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
