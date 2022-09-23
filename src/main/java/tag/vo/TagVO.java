package tag.vo;
import java.sql.Timestamp;

import core.pojo.Core;

public class TagVO extends Core{
	private static final long serialVersionUID = 1L;
	private Integer tagID;
	private String tag;
	private Timestamp lastUpdateTime;
	
	public TagVO() {
	}
	
	public TagVO(Integer tagID, String tag, Timestamp lastUpdateTime) {
		this.tagID = tagID;
		this.tag = tag;
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getTagID() {
		return tagID;
	}

	public void setTagID(Integer tagID) {
		this.tagID = tagID;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	

}
	
	