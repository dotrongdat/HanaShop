/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.history;

import datdt.utilities.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author TRONG DAT
 */
public class HistoryDAO implements Serializable{
    private List<HistoryDTO> list;

    public List<HistoryDTO> getList() {
        return list;
    }

    public void setList(List<HistoryDTO> list) {
        this.list = list;
    }
    public int countTotal() throws SQLException, NamingException{
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Select Count(id) as quantity "
                        + "From History";
                stm=con.createStatement();
                rs=stm.executeQuery(sql);
                if(rs.next()){
                    int count=Integer.parseInt(rs.getString("quantity"));
                    return count;
                }
            }
        }finally{
            if(con!=null){
                con.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(rs!=null){
                rs.close();
            }
        }
        return 0;
    }
    public void getHistoty() throws SQLException, NamingException{
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Select h.userID,a.fullname,h.date, h.activity "
                        + "From History h, Account a "
                        + "Where h.userID=a.userID "
                        + "Group By h.userID,a.fullname,h.date, h.activity "
                        + "Order by h.date DESC ";
                stm=con.createStatement();
                rs=stm.executeQuery(sql);
                this.list=new ArrayList<HistoryDTO>();
                while(rs.next()){
                    String userID=rs.getString("userID");
                    String fullname=rs.getString("fullname");
                    Timestamp date=rs.getTimestamp("date");
                    String activity=rs.getString("activity");
                    
                    HistoryDTO dto=new HistoryDTO(sql, userID, fullname, date, activity);
                    this.list.add(dto);
                }
            }
        }finally{
            if(con!=null){
                con.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(rs!=null){
                rs.close();
            }
        }
    }
    public boolean addToHistory(String id,String userID, Timestamp date,String activity) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Insert into history "
                        + "(id,userID,date,activity) "
                        + "Values(?,?,?,?)";
                ps=con.prepareStatement(sql);
                ps.setString(1, id);
                ps.setString(2, userID);
                ps.setTimestamp(3, date);
                ps.setString(4, activity);
                
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
    public int countRowOfHistory() throws NamingException, SQLException{
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;
        int count=0;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Select Count(id) as quantity "
                        + "From history ";
                stm=con.createStatement();
                rs=stm.executeQuery(sql);
                if(rs.next()){
                   count=rs.getInt("quantity");
                }
            }
        }finally{
            if(con!=null){
                con.close();
            }
            if(stm!=null){
                stm.close();
            }
            if(rs!=null){
                rs.close();
            }
            return count;
        }
    }
}
