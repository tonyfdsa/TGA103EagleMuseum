package exhibition.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exhibition")
public class ExhibitionVOo {
//	EXHIBITION_ID, EXHIBITION_TYPE, EXHIBITION_NAME, EXHIBITION_START_DATE, 
//	EXHIBITION_END_DATE, EXHIBITION_ARTICLE, EXHIBITION_STATUS, LAST_UPDATE_TIME
	public ExhibitionVOo() {

	}

	public ExhibitionVOo(Integer exhibitionID, Integer exhibitionType, String exhibitionName,
			String exhibitionStartDate, String exhibitionEndDate, String exhibitionArticle, Integer exhibitionStatus,
			String lastUpdateTime, String exhibitionImg, Integer valueAldult, Integer valueStu, Integer valueOld,
			Integer valuePhy) {
		super();
		this.exhibitionID = exhibitionID;
		this.exhibitionType = exhibitionType;
		this.exhibitionName = exhibitionName;
		this.exhibitionStartDate = exhibitionStartDate;
		this.exhibitionEndDate = exhibitionEndDate;
		this.exhibitionArticle = exhibitionArticle;
		this.exhibitionStatus = exhibitionStatus;
		this.lastUpdateTime = lastUpdateTime;
		this.exhibitionImg = exhibitionImg;
		this.valueAldult = valueAldult;
		this.valueStu = valueStu;
		this.valueOld = valueOld;
		this.valuePhy = valuePhy;
	}

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

	public String getExhibitionImg() {
		return exhibitionImg;
	}

	public void setExhibitionImg(String exhibitionImg) {
		this.exhibitionImg = exhibitionImg;
	}

	public Integer getValueAldult() {
		return valueAldult;
	}

	public void setValueAldult(Integer valueAldult) {
		this.valueAldult = valueAldult;
	}

	public Integer getValueStu() {
		return valueStu;
	}

	public void setValueStu(Integer valueStu) {
		this.valueStu = valueStu;
	}

	public Integer getValueOld() {
		return valueOld;
	}

	public void setValueOld(Integer valueOld) {
		this.valueOld = valueOld;
	}

	public Integer getValuePhy() {
		return valuePhy;
	}

	public void setValuePhy(Integer valuePhy) {
		this.valuePhy = valuePhy;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer exhibitionID;
	@Column
	private Integer exhibitionType;
	@Column
	private String exhibitionName;
	@Column
	private String exhibitionStartDate;
	@Column
	private String exhibitionEndDate;
	@Column
	private String exhibitionArticle;
	@Column
	private Integer exhibitionStatus;
	@Column
	private String lastUpdateTime;
	@Column
	private String exhibitionImg;
	@Column
	private Integer valueAldult;
	@Column
	private Integer valueStu;
	@Column
	private Integer valueOld;
	@Column
	private Integer valuePhy;

}
