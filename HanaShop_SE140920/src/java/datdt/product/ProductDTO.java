/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.product;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author TRONG DAT
 */
public class ProductDTO implements Serializable{
    private String proID;
    private String cateID;
    private String proName;
    private int quantity;
    private boolean status;
    private String image;
    private String description;
    private int price;
    private Timestamp createDate;

    public ProductDTO(String proID, String cateID, String proName, int quantity, boolean status, String image, String description, int price, Timestamp createDate) {
        this.proID = proID;
        this.cateID = cateID;
        this.proName = proName;
        this.quantity = quantity;
        this.status = status;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
    }

    public ProductDTO(String proID, String cateID, String proName, int quantity, boolean status, String image, String description, int price) {
        this.proID = proID;
        this.cateID = cateID;
        this.proName = proName;
        this.quantity = quantity;
        this.status = status;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate=null;
    }
    
    public ProductDTO(){}

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
     * @return the cateID
     */
    public String getCateID() {
        return cateID;
    }

    /**
     * @param cateID the cateID to set
     */
    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    /**
     * @return the proName
     */
    public String getProName() {
        return proName;
    }

    /**
     * @param proName the proName to set
     */
    public void setProName(String proName) {
        this.proName = proName;
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
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

    /**
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    
    
}
