/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.product;

import java.io.Serializable;

/**
 *
 * @author TRONG DAT
 */
public class ProductObject extends ProductDTO implements Serializable{
    private int quantityInCart;
    public ProductObject(){super();}
    public ProductObject(String proID, String cateID, String proName, int quantity,int quantityInCart, boolean status, String image, String description, int price) {
        super(proID, cateID, proName, quantity, status, image, description, price);
        this.quantityInCart = quantityInCart;
    }

    public int getQuantityInCart() {
        return quantityInCart;
    }

    public void setQuantityInCart(int quantityInCart) {
        this.quantityInCart = quantityInCart;
    }
    
}
