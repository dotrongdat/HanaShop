/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.orderdetail;

import datdt.utilities.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author TRONG DAT
 */
public class OrderDetailDAO implements Serializable{
    private List<OrderDetailObject> proList;

    public List<OrderDetailObject> getProList() {
        return proList;
    }
    public boolean insertOrderDetail(String orderID, String proID, int quantity, int price) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
               String sql="Insert Into OrderDetail "
                       + "(orderID,proID,quantity,price) "
                       + "Values(?,?,?,?)";
               ps=con.prepareStatement(sql);
               ps.setString(1, orderID);
               ps.setString(2, proID);
               ps.setInt(3, quantity);
               ps.setInt(4, price);
               
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
    public void searchOrderDetailByOrderID(String orderID) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
               String sql="Select p.proID,p.proName,p.image,od.quantity, od.price "
                       + "From OrderDetail od, Product p "
                       + "Where p.proID=od.proID And od.orderID=? "
                       + "Group By p.proID,p.proName,p.image,od.quantity, od.price "
                       + "Order By p.proName ASC";
               ps=con.prepareStatement(sql);
               ps.setString(1, orderID);
               rs=ps.executeQuery();
               this.proList=new ArrayList<OrderDetailObject>();
               while(rs.next()){                 
                   String proID=rs.getString("proID");
                   String proName=rs.getString("proName");
                   String image=rs.getString("image");
                   int quantity=rs.getInt("quantity");
                   int price=rs.getInt("price");
                   OrderDetailObject odObj=new OrderDetailObject(proName, image, orderID, proID, quantity, price);
                   this.proList.add(odObj);
               }
            }
        }finally{
            if(con!=null){
                con.close();
            }
            if(ps!=null){
                ps.close();
            }
            if(rs!=null){
                rs.close();
            }
        }
    }

}
