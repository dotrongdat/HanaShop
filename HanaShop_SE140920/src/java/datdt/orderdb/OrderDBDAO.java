/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.orderdb;

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
public class OrderDBDAO implements Serializable{
    private List<OrderDBDTO> orderedList;
    public OrderDBDAO(){}

    public List<OrderDBDTO> getOrderedList() {
        return orderedList;
    }

    public void setOrderedList(List<OrderDBDTO> orderedList) {
        this.orderedList = orderedList;
    }
    public boolean insertOrder(String orderID,String userID, Timestamp date,String address,String phoneNumber,String paypalID) throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement ps=null;
        
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
               String sql="Insert Into OrderDB "
                       + "(orderID,userID,date,address,phoneNumber,paypalID) "
                       + "Values(?,?,?,?,?,?)";
               ps=con.prepareStatement(sql);
               ps.setString(1, orderID);
               ps.setString(2, userID);
               ps.setTimestamp(3, date);
               ps.setString(4, address);
               ps.setString(5, phoneNumber);
               ps.setString(6, paypalID);
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
    public void getOrder(String userID) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
               String sql="Select orderID,date, address, phoneNumber, paypalID "
                       + "From OrderDB "
                       + "Where userID=?";

               ps=con.prepareStatement(sql);
               ps.setString(1, userID);
               
               rs=ps.executeQuery();
                this.orderedList=new ArrayList<OrderDBDTO>();
               while(rs.next()){
                   String orderID=rs.getString("orderID");
                   Timestamp date=rs.getTimestamp("date");
                   String adrress=rs.getString("address");
                   String phoneNumber=rs.getString("phoneNumber");
                   String paypalID=rs.getString("paypalNumber");
                   OrderDBDTO dto=new OrderDBDTO(orderID, userID, date,adrress,phoneNumber,paypalID);
                   this.orderedList.add(dto);
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
    public void searchOrderByDate(String userID,Timestamp date) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
               String sql="Select orderID, address, phoneNumber, paypalID "
                       + "From OrderDB "
                       + "Where userID=? And date=?";

               ps=con.prepareStatement(sql);
               ps.setString(1, userID);
               ps.setTimestamp(2, date);
               rs=ps.executeQuery();
               this.orderedList=new ArrayList<OrderDBDTO>();
               while(rs.next()){
                   String orderID=rs.getString("orderID");
                   String address=rs.getString("address");
                   String phoneNumber=rs.getString("phoneNumber");
                   String paypalID=rs.getString("paypalID");
                   OrderDBDTO dto=new OrderDBDTO(orderID, userID, date,address,phoneNumber,paypalID);
                   this.orderedList.add(dto);
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
    public void searchOrder(String userID,String proNameSearch,Timestamp fromDate,Timestamp date) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
               String sql="Select o.orderID, o.date,o.address, o.phoneNumber, o.paypalID "
                       + "From OrderDB o, OrderDetail od, Product p "
                       + "Where o.orderID=od.orderID And p.proID=od.proID And o.userID=? And p.proName like ? And  o.date Between ? And ? "
                       + "Group By o.orderID, o.address, o.phoneNumber, o.paypalID, o.date "
                       + "Order By o.date DESC ";

               ps=con.prepareStatement(sql);
               ps.setString(1, userID);
               ps.setString(2, "%"+proNameSearch+"%");
               ps.setTimestamp(3, fromDate);
               ps.setTimestamp(4, date);
               rs=ps.executeQuery();
               this.orderedList=new ArrayList<OrderDBDTO>();
               while(rs.next()){
                   String orderID=rs.getString("orderID");
                   String address=rs.getString("address");
                   String phoneNumber=rs.getString("phoneNumber");
                   String paypalID=rs.getString("paypalID");
                   date=rs.getTimestamp("date");
                   OrderDBDTO dto=new OrderDBDTO(orderID, userID, date,address,phoneNumber,paypalID);
                   this.orderedList.add(dto);
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
    
    public int countOrderByUserID(String userID) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
               String sql="Select Count(orderID) as quantity "
                       + "From OrderDB "
                       + "Where userID=?";

               ps=con.prepareStatement(sql);
               ps.setString(1, userID);
               rs=ps.executeQuery();
               if(rs.next()){
                   String quantity=rs.getString("quantity");
                   return Integer.parseInt(quantity);
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
        return 0;
    }
}
