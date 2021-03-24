/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.orderdetail;

import java.io.Serializable;

/**
 *
 * @author TRONG DAT
 */
public class OrderDetailDTO implements Serializable{
    private String orderID;
    private String proID;
    private int quantity;
    private int price;
    
    public OrderDetailDTO(){}

    public OrderDetailDTO(String orderID, String proID, int quantity, int price) {
        this.orderID = orderID;
        this.proID = proID;
        this.quantity = quantity;
        this.price = price;
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
     * @return the proID
     */
    public String getProID() {
        return proID;
    }

    /**
     * @param proID the proID to set
     */
    public void setProID(String proID) {
        this.proID = proID;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
}
