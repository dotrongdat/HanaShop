/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.orderdb;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author TRONG DAT
 */
public class OrderDBDTO implements Serializable{
    private String orderID;
    private String userID;
    private Timestamp date;
    private String address;
    private String phoneNumber;
    private String paypalID;
    
    public OrderDBDTO(){}

    public OrderDBDTO(String orderID, String userID, Timestamp date,String address, String phoneNumber, String paypalID) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.paypalID=paypalID;
    }

    /**
     * @return the orderID
     */
    public String getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(String orderID) {
        this.orderID = orderID;
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
     * @return the date
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPaypalID() {
        return paypalID;
    }

    public void setPaypalID(String paypalID) {
        this.paypalID = paypalID;
    }
    
    
}
