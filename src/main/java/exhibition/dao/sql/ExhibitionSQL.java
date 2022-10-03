package exhibition.dao.sql;

public class ExhibitionSQL {
	// 全部查詢
	public static final String GET_ALL = "SELECT * FROM exhibition";

	// 查詢展覽名稱
	public static final String GET_BY_NAME = "SELECT * FROM exhibition where exhibitionName like ?";

	// 查詢展覽日期
	public static final String GET_BY_DATE = "SELECT * FROM exhibition where date(exhibitionEndDate) between ? and ?;";

}
