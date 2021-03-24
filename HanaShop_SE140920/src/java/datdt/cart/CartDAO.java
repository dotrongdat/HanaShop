/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.cart;

import datdt.utilities.DBHelper;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author TRONG DAT
 */
public class CartDAO implements Serializable{
    public boolean updateCart(String userID, CartObject cart) throws SQLException, NamingException, IOException{
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Update Cart "
                        + "Set data=? "
                        + "Where userID=? ";
                ps=con.prepareStatement(sql);
                ps.setString(1, cart.writeObjectToString());
                ps.setString(2, userID);
                
                int result=ps.executeUpdate();
                if(result==1){
                    return true;
                }
            }
        }finally{
            if(con!=null){
                con.close();
            }
            if(ps!=null){
                ps.close();
            }          
        }
        return false;
    }
    public boolean insertCart(String ID,String userID) throws NamingException, SQLException, IOException{
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Insert into Cart "
                        + "(ID,userID,data)"
                        + "Values(?,?,?) ";
                ps=con.prepareStatement(sql);
                ps.setString(1, ID);
                ps.setString(2, userID);
                String data=new CartObject().writeObjectToString();
                ps.setString(3, data);
                                
                int result=ps.executeUpdate();
                if(result==1){
                    return true;
                }
            }
        }finally{
            if(con!=null){
                con.close();
            }
            if(ps!=null){
                ps.close();
            }          
        }
        return false;
    }
    public CartObject getCart(String userID) throws NamingException, SQLException, IOException, ClassNotFoundException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Select data "
                        + "From Cart "
                        + "Where userID=?";
                ps=con.prepareStatement(sql);
                ps.setString(1, userID);
               
                rs=ps.executeQuery();
                if(rs.next()){
                    String data=rs.getString("data");
                    CartObject cart=new CartObject();
                    cart.writeStringToObject(data);
                    return cart;
                }
            }
        }finally{
            if(con!=null){
                con.close();
            }
            if(ps!=null){
                ps.close();
            }          
        }
        return null;
    }
}
