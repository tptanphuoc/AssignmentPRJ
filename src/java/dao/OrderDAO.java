package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Order;

/**
 *
 * @author huynh
 */
public class OrderDAO {
    private static final String INSERT_ORDER = "INSERT INTO [order] (userId, total, [date]) VALUES (?,?,?)";
    private static final String INSERT_ORDER_DETAIL = "INSERT INTO [orderDetail] (orderId, productId, price, quantity) VALUES ((SELECT TOP 1 orderId FROM [order] ORDER BY orderId DESC),?,?,?)";
    private static final String GET_USER_ORDERS = "SELECT product.id AS productId, product.[name], product.[image], orderDetail.price, orderDetail.quantity, [order].[date]\n" +
                                                    "FROM(([order] INNER JOIN [orderDetail] ON [order].orderId = [orderDetail].orderId)\n" +
                                                    "INNER JOIN product ON orderDetail.productId = product.id) WHERE [order].userId =? ORDER BY [order].orderId DESC";
    private static final String GET_DATA_EMAIL = "SELECT TOP 1 total, date FROM [order] WHERE userId =? ORDER BY orderId DESC";

    public boolean addOrder(Order order) throws SQLException{
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
            ps = conn.prepareStatement(INSERT_ORDER);
            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getTotal());
            ps.setString(3, order.getDate());
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
    
    public boolean addOrderDetail(Order order) throws SQLException{
        boolean checkInsert = false;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
            ps = conn.prepareStatement(INSERT_ORDER_DETAIL);
            ps.setInt(1, order.getProductId());
            ps.setInt(2, order.getPrice());
            ps.setInt(3, order.getQuantity());
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
    
    public List<Order> userOrders(int userId) throws SQLException {
        List<Order> listOrders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(GET_USER_ORDERS);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                Order order = new Order();
                
                order.setProductId(rs.getInt("productId"));
                order.setName(rs.getString("name"));
                order.setImage(rs.getString("image"));
                order.setPrice(rs.getInt("price"));
                order.setQuantity(rs.getInt("quantity"));
                order.setDate(rs.getString("date"));
                listOrders.add(order);
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return listOrders;
    }  
    
    public Order accountMailData(int userId) throws SQLException {
        Order order = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(GET_DATA_EMAIL);
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                if(rs.next()){
                    int total = rs.getInt("total");
                    String date = rs.getString("date");
                    order = new Order(total, date);
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return order;
    }  
}
