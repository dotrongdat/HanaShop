/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.orderdetail;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author TRONG DAT
 */
public class OrderDetailObject extends OrderDetailDTO implements Serializable{
    private String proName;
    private String image;
    
    public OrderDetailObject(){super();}

    public OrderDetailObject(String proName,String image, String orderID, String proID, int quantity, int price) {
        super(orderID, proID, quantity, price);
        this.proName = proName;
        this.image=image;
    }

    

    /**
     * @return the userID

    /**
     * @return the orderID
     */
    

    /**


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the proID
     */
    
    
}
