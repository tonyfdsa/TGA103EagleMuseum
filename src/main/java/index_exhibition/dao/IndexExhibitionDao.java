package index_exhibition.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import index_exhibition.vo.IndexExhibitionVo;

public class IndexExhibitionDao {
    private String URL = "jdbc:mysql://localhost:3306/eagleMuseum_schema";
    private String USER = "root";
    private String PASSWORD = "qweasd8426";
    
    public Long insertExhibition(IndexExhibitionVo vo) {
        String sql = "INSERT INTO index_exhibition(exhibition_name, exhibition_article, exhibition_startdate, exhibition_enddate, exhibition_image) VALUES(?,?,?,?,?)";
        Long id = 0L;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, vo.getExhibitionName());
            ps.setString(2, vo.getExhibitionArticle());
            ps.setTimestamp(3, Timestamp.valueOf(vo.getExhibitionStartDate()));
            ps.setTimestamp(4, Timestamp.valueOf(vo.getExhibitionEndDate()));
            ps.setBytes(5, vo.getExhibitionImage());
            int rowCount = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
              id = rs.getLong(1);
            }
            System.out.println(rowCount + " row(s) inserted!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public IndexExhibitionVo getLatestExhibition() throws Exception{
        String sql = "SELECT * FROM eagleMuseum_schema.index_exhibition WHERE ID = (SELECT MAX(ID) FROM eagleMuseum_schema.index_exhibition);";
        IndexExhibitionVo exhibition = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY)) {
            /*
             * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
             * resources小括號內，參看ResultSet說明
             */
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("exhibition_name");
                String article = rs.getString("exhibition_article");
                LocalDateTime startDate = rs.getTimestamp("exhibition_startdate").toLocalDateTime();
                LocalDateTime endDate = rs.getTimestamp("exhibition_enddate").toLocalDateTime();
                byte[] img = rs.getBytes("exhibition_image");
                // TODO get image
                exhibition = new IndexExhibitionVo(id, name, article, startDate, endDate, img);
            }
        }
        return exhibition;
    }
    
    public List<IndexExhibitionVo> getExhibitions() {
        String sql = "SELECT * FROM eagleMuseum_schema.index_exhibition "
                   + "WHERE ID = (SELECT MAX(ID) FROM eagleMuseum_schema.index_exhibition);";
        List<IndexExhibitionVo> exhibitions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY)) {
            /*
             * 當Statement關閉，ResultSet也會自動關閉， 可以不需要將ResultSet宣告置入try with
             * resources小括號內，參看ResultSet說明
             */
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                String article = rs.getString(3);
                LocalDateTime startDate = rs.getTimestamp(4).toLocalDateTime();
                LocalDateTime endDate = rs.getTimestamp(5).toLocalDateTime();
                byte[] img = rs.getBytes(6);
                // TODO get image
                IndexExhibitionVo vo = new IndexExhibitionVo(id, name, article, startDate, endDate, img);
                exhibitions.add(vo);
            }
            /* 前面不斷rs.next()已經讓指標指到最後一筆的下一列，
             * 如果result set設定成「ResultSet.TYPE_FORWARD_ONLY」，
             * 再執行rs.last()，指標會往回走，
             * 導致「Operation not allowed for a result set of type ResultSet.TYPE_FORWARD_ONLY.」執行錯誤，
             * 所以要設定成「ResultSet.TYPE_SCROLL_INSENSITIVE」
             */
            rs.last();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exhibitions;
    }
    
    public void updateExhibition(IndexExhibitionVo update) {
        String sql = "UPDATE eagleMuseum_schema.index_exhibition set exhibition_name=?, exhibition_article=?, exhibition_startdate=?, exhibition_enddate=? where id=?;";
    
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, update.getExhibitionName());
            ps.setString(2, update.getExhibitionArticle());
            ps.setTimestamp(3, Timestamp.valueOf(update.getExhibitionStartDate()));
            ps.setTimestamp(4, Timestamp.valueOf(update.getExhibitionEndDate()));
            ps.setLong(5, update.getExhibitionID());
            int rowCount = ps.executeUpdate();
            System.out.println(rowCount + " row(s) updated!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
        IndexExhibitionDao dao = new IndexExhibitionDao();
        System.out.println(dao.getLatestExhibition());
        
        List<IndexExhibitionVo> exhibitions = dao.getExhibitions();
        for (IndexExhibitionVo e: exhibitions) {
            System.out.println(e);
            System.out.println();
        }
        IndexExhibitionVo insert = new IndexExhibitionVo();
        insert.setExhibitionName("十三行文化展");
        insert.setExhibitionArticle("北台灣考古學");
        insert.setExhibitionStartDate(LocalDateTime.now());
        insert.setExhibitionEndDate(LocalDateTime.of(2022, 11, 1, 0, 0));
        
        Long id = dao.insertExhibition(insert);
        System.out.println("New id = " + id);
        
        IndexExhibitionVo update = new IndexExhibitionVo();
        update.setExhibitionID(1L);
        update.setExhibitionName("白堊紀恐龍展");
        update.setExhibitionArticle("美國考古學");
        update.setExhibitionStartDate(LocalDateTime.now());
        update.setExhibitionEndDate(LocalDateTime.of(2022, 11, 1, 0, 0));
        dao.updateExhibition(update);
        System.out.println("Update finish");

    }
    
//    // 查詢展覽名稱
//    public static final String GET_BY_NAME = "SELECT * FROM eaglemuseum_schema.index_productList where exhibitionName like ?";
//
//    // 查詢展覽日期
//    public static final String GET_BY_DATE = "SELECT * FROM index_exhibition where date(exhibitionEndDate) between ? and ?;";
//
//    // 更新狀態
//    public static final String UpdateStatus = "UPDATE inedex_exhibition set exhibitionStatus=? where exhibitionID=?";
//
//    // 商品圖片上傳
//    public static final String InsertExhibitionImg = "INSERT INTO inedex_exhibitionImg(exhibitionID, exhibitionImgID, lastUpdateTime) VALUES(?,?,NOW())";
//            
//    //上傳圖片取得
//    public static final String GetImgByID = "select * from inedex_exhibitionImg where exhibitionID=?";
//            
//    //刪除圖片
//    public static final String ProdDeImg = "delete from inedex_exhibitionImg where exhibitionID= ?";
//            
//    //取得所有圖片
//    public static final String GetAllImg = "select * from inedex_exhibitionImg ";
}