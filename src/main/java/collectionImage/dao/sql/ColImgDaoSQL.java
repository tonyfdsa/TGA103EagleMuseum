package collectionImage.dao.sql;

public class ColImgDaoSQL {
	public static final String INSERT_STMT = "INSERT INTO collectionImage (collectionID, imageName) VALUES (?, ?)";
	public static final String DELETE = "DELETE FROM collectionImage where imageID = ?";
	public static final String UPDATE = "UPDATE collectionImage set imageName=?";

}
