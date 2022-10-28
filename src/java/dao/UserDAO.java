package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Account;

/**
 *
 * @author huynh
 */
public class UserDAO {
    
    private static final String LOGIN = "SELECT userID, isAdmin FROM account WHERE userName=? AND password=?";
    private static final String SIGNUP = "INSERT INTO account(userName, userPhone, password, isAdmin) VALUES(?,?,?,0)" ;
    private static final String CHECK_ACCOUNT = "SELECT userID, userName FROM account WHERE userName = ?" ;
    private static final String GET_DATA_EMAIL = "SELECT userName, userPhone FROM account WHERE userID = ?" ;
    
    public Account login(String userName, String password) throws SQLException {
        Account acc = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(LOGIN);
                ps.setString(1, userName);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if(rs.next()){
                    int id = rs.getInt("userID");
                    int isAdmin = rs.getInt("isAdmin");
                    
                    acc = new Account(id, userName, null, null, isAdmin);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return acc;
    }
    public void signup(String userName, String phoneNumber, String password) throws SQLException {       
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(SIGNUP);
                ps.setString(1, userName);
                ps.setString(2, phoneNumber);
                ps.setString(3, password);
                ps.executeUpdate();             
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
    }
    
    public Account checkAccount(String userName) throws SQLException {
        Account acc = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = utils.DBConnect.getConnection();
            if(conn != null){
                ps = conn.prepareStatement(CHECK_ACCOUNT);
                ps.setString(1, userName);
                rs = ps.executeQuery();
                if(rs.next()){
                    int id = rs.getInt("userID");
                    String name = rs.getString("userName");
                    acc = new Account(id, name, null, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return acc;
    }
    
    public Account accountMailData(int userId) throws SQLException {
        Account acc = null;
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
                    String userName = rs.getString("userName");
                    String phone = rs.getString("userPhone");
                    acc = new Account(userName, phone);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }
        return acc;
    }    
    
}
