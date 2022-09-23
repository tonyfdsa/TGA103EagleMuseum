package tag.dao.sql;

public class TagDaoSQL {
	public static final String INSERT_STMT = "INSERT INTO tag (tag) VALUES (?)";
	public static final String GET_ALL = "SELECT tagID, tag, lastUpdateTime FROM tag order by tagID";
	public static final String GET_ONE_BY_ID = "SELECT tagID, tag, lastUpdateTime FROM tag where tagID = ?";
	public static final String GET_ONE_BY_NAME = "SELECT tagID, tag, lastUpdateTime FROM tag where tag = ?";
	public static final String UPDATE = "UPDATE tag set tag=?";
}