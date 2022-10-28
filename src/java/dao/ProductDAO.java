package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author huynh
 */
public class ProductDAO {
    
    private static final String GET_ALL_PRODUCT = "SELECT id, name, image, price, title, quantity, cateID from product";
    private static final String GET_ALL_CATEGORY = "SELECT cateID, cateName FROM category";
    private static final String ASCENDING_ORDER = "SELECT id, name, image, price, title, quantity, cateID FROM product ORDER BY price ASC";
    private static final String DESCENDING_ORDER = "SELECT id, name, image, price, title, quantity, cateID FROM product ORDER BY price DESC";
    private static final String LIST_PRODUCT_OF_CATEGORY = "SELECT id, name, image, price, title, quantity, cateID FROM product WHERE cateID =?";
    private static final String LIST_PRODUCT_BY_NAME = "SELECT name, id, image, price, title, quantity, cateID FROM product WHERE [name] like ?";
    private static final String GET_PRODUCT_BY_ID = "SELECT name, image, price, title, quantity, cateID FROM product WHERE id =?";
    private static final String GET_PRODUCT_BY_SELLER_ID = "SELECT id, name, image, price, title, quantity, cateID FROM product WHERE sellerID = ?";
    private static final String ADD_PRODUCT = "INSERT INTO product([name], [image], [price], [title], [quantity], [cateID], [sellerID]) VALUES (?,?,?,?,?,?,?)";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE id= ?";
    private static final String EDIT_PRODUCT = "UPDATE product SET [name] = ?, [image] = ?, [price] = ?, [title] = ?, [quantity] = ?, [cateID] = ? WHERE id = ?";
    private static final String GET_QUANTITY_OF_PRODUCT = "SELECT quantity FROM product WHERE id=?" ;
    private static final String GET_PRICE_OF_PRODUCT = "SELECT price FROM product WHERE id=?" ;
    private static final String UPDATE_QUANTITY = "UPDATE product SET quantity = ? WHERE id = ?" ;
    
    public List<Product> getAllProduct() throws SQLException {
        List<Product> listProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(GET_ALL_PRODUCT);
                rs = ps.executeQuery();
                while (rs.next()) {
                listProduct.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return listProduct;
    }
    
    public List<Category> getAllCategory() throws SQLException {
        List<Category> listCategory = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(GET_ALL_CATEGORY);
                rs = ps.executeQuery();
                while (rs.next()) {
                listCategory.add(new Category(rs.getInt("cateID"),
                        rs.getString("cateName")));
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return listCategory;
    }
    
    public List<Product> getByAscPrice() throws SQLException {
        List<Product> listAsc = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(ASCENDING_ORDER);
                rs = ps.executeQuery();
                while (rs.next()) {
                listAsc.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return listAsc;
    }
    
    public List<Product> getByDesPrice() throws SQLException {
        List<Product> listDes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(DESCENDING_ORDER);
                rs = ps.executeQuery();
                while (rs.next()) {
                listDes.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return listDes;
    }
    
    public List<Product> getByCateID(int cateId) throws SQLException {
        List<Product> listCateID = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(LIST_PRODUCT_OF_CATEGORY);
                ps.setInt(1, cateId);
                rs = ps.executeQuery();
                while (rs.next()) {
                listCateID.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return listCateID; 
    }
    
    public List<Product> getByName(String name) throws SQLException {
        List<Product> listProductByName = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(LIST_PRODUCT_BY_NAME);
                ps.setString(1, "%"+name+"%");
                rs = ps.executeQuery();
                while (rs.next()) {
                listProductByName.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return listProductByName; 
    }
    
    public Product getProductById(int id) throws SQLException {
        Product product = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(GET_PRODUCT_BY_ID);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String pName = rs.getString("name");
                    String pImage = rs.getString("image");
                    int pPrice = rs.getInt("price");
                    String pTitle = rs.getString("title");
                    int pQuantity = rs.getInt("quantity");
                    int pCateId = rs.getInt("cateID");
                    
                    product = new Product(id, pName, pImage, pPrice, pTitle, pQuantity, pCateId);
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        
        return product;
    }
    
    public List<Product> getBySellerId(int userId) throws SQLException {
        List<Product> listProductBySellerId = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(GET_PRODUCT_BY_SELLER_ID);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                listProductBySellerId.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("title"),
                        rs.getInt("quantity"),
                        rs.getInt("cateID")));
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return listProductBySellerId; 
    }  
    
    //bắt buộc phải truyền nhiều param vì có id của account trên session
    public boolean insertProduct(String name, String image, int price, String title, int quantity, int cateID, int sellerID) throws SQLException {
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
            ps = conn.prepareStatement(ADD_PRODUCT);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setInt(3, price);
            ps.setString(4, title);
            ps.setInt(5, quantity);
            ps.setInt(6, cateID);
            ps.setInt(7, sellerID);
            checkInsert = ps.executeUpdate() >0 ? true:false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return checkInsert;
    }
    
    public boolean deleteProductByID(int productId) throws SQLException {
        boolean checkDelete = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
            ps = conn.prepareStatement(DELETE_PRODUCT);
            ps.setInt(1, productId);
            checkDelete = ps.executeUpdate() >0 ? true:false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return checkDelete;
    }
    
    //bắt buộc phải truyền nhiều param vì có id của product khi nhấn vào
    public boolean editProduct(int id, String name, String image, int price, String title, int quantity, int cateID) throws SQLException {
        boolean checkEdit = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
            ps = conn.prepareStatement(EDIT_PRODUCT);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setInt(3, price);
            ps.setString(4, title);
            ps.setInt(5, quantity);
            ps.setInt(6, cateID);
            ps.setInt(7, id);
            checkEdit = ps.executeUpdate() >0 ? true:false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return checkEdit;
    } 
    
    public int getQuantity(int productId) throws SQLException{
        int quantity = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(GET_QUANTITY_OF_PRODUCT);
                ps.setInt(1, productId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return quantity;
    }
    
    public boolean updateQuantity(int quantity, int productId) throws SQLException{
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(UPDATE_QUANTITY);
                ps.setInt(1, quantity);
                ps.setInt(2, productId);
                checkUpdate = ps.executeUpdate() >0 ? true:false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return checkUpdate;
    }    
    
    public int getPrice(int productId) throws SQLException{
        int price = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(GET_PRICE_OF_PRODUCT);
                ps.setInt(1, productId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    price = rs.getInt("price");
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return price;
    }    
}
