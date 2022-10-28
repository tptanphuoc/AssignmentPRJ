package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cart;

/**
 *
 * @author huynh
 */
public class CartDAO {
    
    private static final String GET_TOTAL_PRICE_IN_CART = "SELECT price FROM product WHERE id=?";
    
        public int totalCartPrice(ArrayList<Cart> cartList) throws SQLException{
        int total = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                if(cartList.size() > 0) {
                    for(Cart item : cartList){
                        ps = conn.prepareStatement(GET_TOTAL_PRICE_IN_CART);
                        ps.setInt(1, item.getId());
                        rs = ps.executeQuery();
                        while (rs.next()) {
                        total += rs.getInt("price")*item.getAmount();
                        }
                    }
                }               
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return total;
    }
}
