/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.cart;

import java.io.Serializable;

/**
 *
 * @author TRONG DAT
 */
public class CartDTO implements Serializable{
    private String data;
    private String cartID;
    public CartDTO(){}

    public String getCartID() {
        return cartID;
    }

    public String getData() {
        return data;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public void setData(String data) {
        this.data = data;
    }
        
}
