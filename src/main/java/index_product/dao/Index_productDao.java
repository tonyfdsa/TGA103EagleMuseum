package index_product.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import index_product.vo.Index_productVo;

public class Index_productDao {
	private String URL = "jdbc:mysql://localhost:3306/eagleMuseum_schema";
    private String USER = "root";
    private String PASSWORD = "qweasd8426";
	
    public Long insertProduct(Index_productVo vo) {
        String sql = "INSERT INTO index_product(prod_name, prod_description, prod_image,) VALUES(?,?,?)";
        Long id = 0L;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, vo.getProductName());
            ps.setString(2, vo.getProductDescription());
            ps.setBytes(3, vo.getProductImage());
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
	
    public List<Index_productVo> getProducts() {
        String sql = "SELECT * FROM eagleMuseum_schema.index_product "
                   + "WHERE ID = (SELECT MAX(ID) FROM eagleMuseum_schema.index_product);";
        List<Index_productVo> products = new ArrayList<>();
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
                String description = rs.getString(3);
                byte[] img = rs.getBytes(6);
                // TODO get image
                Index_productVo vo = new Index_productVo(id, name, description, img);
                products.add(vo);
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
        return products;
    }
    
    
    public void updateProduct(Index_productVo update) {
        String sql = "UPDATE eagleMuseum_schema.Index_product set prod_name=?, prod_description=? where id=?;";
    
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, update.getProductName());
            ps.setString(2, update.getProductDescription());
            ps.setLong(5, update.getProductID());
            int rowCount = ps.executeUpdate();
            System.out.println(rowCount + " row(s) updated!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) throws Exception {
    	
    	Index_productDao dao = new Index_productDao();
    	Index_productVo insert = new Index_productVo();
        insert.setProductName("肌力聖經");
        insert.setProductDescription("怪獸教練");
        
        Long id = dao.insertProduct(insert);
        System.out.println("New id = " + id);
        
    }   
}        
//        IndexExhibitionVo update = new IndexExhibitionVo();
//        update.setExhibitionID(1L);
//        update.setExhibitionName("白堊紀恐龍展");
//        update.setExhibitionArticle("美國考古學");
//        update.setExhibitionStartDate(LocalDateTime.now());
//        update.setExhibitionEndDate(LocalDateTime.of(2022, 11, 1, 0, 0));
//        dao.updateExhibition(update);
//        System.out.println("Update finish");
//
//    }
    
    
    
    
    
    
    
    
    
    
    
    

