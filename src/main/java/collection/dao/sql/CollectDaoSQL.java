package collection.dao.sql;

public class CollectDaoSQL {
	public static final String INSERT_STMT = "INSERT INTO collection (collectionTitle, collectionEar, collectionText, collectionMaterial, collectionStatus) VALUES (?, ?, ?, ?, ?)";
	public static final String GET_ALL = "SELECT collectionID, collectionTitle, collectionEar, collectionText, collectionMaterial, collectionStatus, lastUpdateTime FROM collection order by collectionID and collectionStatus desc";
	public static final String GET_NAME = "SELECT collectionID, collectionTitle, collectionEar, collectionText, collectionMaterial, collectionStatus, lastUpdateTime FROM collection where collectionTitle like '%?%'";
	public static final String GET_MATERIAL = "SELECT collectionID, collectionTitle, collectionEar, collectionText, collectionMaterial, collectionStatus, lastUpdateTime FROM collection where collectionMaterial like '%?%'";
	public static final String GET_MATERIAL_AND_NAME = "SELECT collectionID, collectionTitle, collectionEar, collectionText, collectionMaterial, collectionStatus, lastUpdateTime FROM collection where collectionMaterial like '%?%' and collectionTitle like '%?%'";
	public static final String GET_EAR = "SELECT collectionID, collectionTitle, collectionEar, collectionText, collectionMaterial, collectionStatus, lastUpdateTime FROM collection where collectionEar like '%?%'";
	public static final String GET_EAR_NAME = "SELECT collectionID, collectionTitle, collectionEar, collectionText, collectionMaterial, collectionStatus, lastUpdateTime FROM collection where collectionEar like '%?%' and collectionTitle like '%?%'";
	public static final String GET_EAR_MATERIAL = "SELECT collectionID, collectionTitle, collectionEar, collectionText, collectionMaterial, collectionStatus, lastUpdateTime FROM collection where collectionEar like '%?%' and collectionMaterial like '%?%'";
	public static final String GET_EAR_MATERIAL_NAME = "SELECT collectionID, collectionTitle, collectionEar, collectionText, collectionMaterial, collectionStatus, lastUpdateTime FROM collection where collectionEar like '%?%' and collectionTitle like '%?%' and collectionMaterial like '%?%'";
	public static final String DELETE = "DELETE FROM collection where collectionID = ?";
	public static final String UPDATE = "UPDATE collection set collectionTitle=? ,collectionEar=? ,collectionText=?, collectionStatus=?, collectionMaterial=?";
}