/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author huynh
 */
public class Account {
    private int userID;
    private String userName;
    private String phoneNum;
    private String password;
    private int isAdmin;

    public Account() {
    }

    public Account(int userID, String userName, String phoneNum, String password, int isAdmin) {
        this.userID = userID;
        this.userName = userName;
        this.phoneNum = phoneNum;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Account(int userID, String userName, String phoneNum, String password) {
        this.userID = userID;
        this.userName = userName;
        this.phoneNum = phoneNum;
        this.password = password;
    }

    public Account(String userName, String phoneNum) {
        this.userName = userName;
        this.phoneNum = phoneNum;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

}
