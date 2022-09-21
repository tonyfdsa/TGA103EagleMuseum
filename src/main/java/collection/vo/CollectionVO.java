package collection.vo;
import java.sql.Timestamp;

import core.pojo.Core;

public class CollectionVO extends Core{
	private static final long serialVersionUID = 1L;
	private Integer collectionID;
	private String collectionTitle;
	private String collectionEar;	
	private String collectionText;
	private String collectionMaterial;
	private Timestamp lastUpdateTime;
	private Boolean collectionStatus;
	
	public CollectionVO() {
	}
	
	
	public CollectionVO(Integer collectionID, String collectionTitle, String collectionEar, String collectionText, String collectionMaterial, Timestamp lastUpdateTime, Boolean collectionStatus) {
		this.collectionID = collectionID;
		this.collectionTitle = collectionTitle;
		this.collectionEar = collectionEar;
		this.collectionText = collectionText;
		this.collectionMaterial = collectionMaterial;
		this.lastUpdateTime = lastUpdateTime;
		this.collectionStatus = collectionStatus;
	}
	
	public Integer getCollectionID() {
		return collectionID;
	}
	public void setCollectionID(Integer collectionID) {
		this.collectionID = collectionID;
	}
	public String getCollectionText() {
		return collectionText;
	}
	public void setCollectionText(String collectionText) {
		this.collectionText = collectionText;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Boolean getCollectionStatus() {
		return collectionStatus;
	}
	public void setCollectionStatus(Boolean collectionStatus) {
		this.collectionStatus = collectionStatus;
	}
	public String getCollectionMaterial() {
		return collectionMaterial;
	}
	public void setCollectionMaterial(String collectionMaterial) {
		this.collectionMaterial = collectionMaterial;
	}


	public String getCollectionTitle() {
		return collectionTitle;
	}


	public void setCollectionTitle(String collectionTitle) {
		this.collectionTitle = collectionTitle;
	}


	public String getCollectionEar() {
		return collectionEar;
	}


	public void setCollectionEar(String collectionEar) {
		this.collectionEar = collectionEar;
	}
}
	
	