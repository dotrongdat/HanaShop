/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.account;

import java.io.Serializable;

/**
 *
 * @author TRONG DAT
 */
public class AccountDTO implements Serializable{
    private String userID;
    private String username;
    private String password;
    private String fullname;
    private boolean admin;
    private String email;

    public AccountDTO(){
        
    }

    public AccountDTO(String userID, String username, String password, String fullname, boolean admin, String email) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.admin = admin;
        this.email = email;
    }

    public AccountDTO(String userID, String fullname, boolean admin, String email) {
        this.userID = userID;
        this.fullname = fullname;
        this.admin = admin;
        this.email = email;
    }
    
    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
