package exhibition.vo;

public class ExhibitionVO {
//	EXHIBITION_ID, EXHIBITION_TYPE, EXHIBITION_NAME, EXHIBITION_START_DATE, 
//	EXHIBITION_END_DATE, EXHIBITION_ARTICLE, EXHIBITION_STATUS, LAST_UPDATE_TIME
	
	private Integer exhibitionID;
	private Integer exhibitionType;
	private String exhibitionName;
	private String exhibitionStartDate;
	private String exhibitionEndDate;
	private String exhibitionArticle;
	private Integer exhibitionStatus;
	private String lastUpdateTime;
	private Integer locationId;
	private byte[] exhibitionImg;
	private String exhibitionImgBase64;
	public Integer getExhibitionID() {
		return exhibitionID;
	}
	public void setExhibitionID(Integer exhibitionID) {
		this.exhibitionID = exhibitionID;
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
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public byte[] getExhibitionImg() {
		return exhibitionImg;
	}
	public void setExhibitionImg(byte[] exhibitionImg) {
		this.exhibitionImg = exhibitionImg;
	}
	public String getExhibitionImgBase64() {
		return exhibitionImgBase64;
	}
	public void setExhibitionImgBase64(String exhibitionImgBase64) {
		this.exhibitionImgBase64 = exhibitionImgBase64;
	}
	

}
