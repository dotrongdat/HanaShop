/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.account;

import datdt.utilities.DBHelper;
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
public class AccountDAO implements Serializable{
    public AccountDTO checkLogin(String username, String password) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            //1. make connection
            con=DBHelper.makeConnection();
            if(con!=null){
                //2. create SQL string
                String sql="Select userID, fullname, admin "
                        + "From account "
                        + "Where username=? And password=?";
                //3. create stament
                ps=con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                
                //4. excute query
                rs=ps.executeQuery();
                
                //5. check result
                if(rs.next()){
                    String userID=rs.getString("userID");
                    String fullname=rs.getString("fullname");
                    boolean admin=rs.getBoolean("admin");
                    AccountDTO dto=new AccountDTO();
                    dto.setUserID(userID);
                    dto.setAdmin(admin);
                    dto.setFullname(fullname);
                    return dto;
                }//end if result return not empty
                    
            }//end if connection connected
        }finally{
            if(rs!=null){
                rs.close();
            }//end if result set not null
            if(ps!=null){
                ps.close();
            }//end if prepared statement not null
            if(con!=null){
                con.close();
            }//end if connnection not null
        }
        return null;
    }
    public AccountDTO checkLogin(String email) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            //1. make connection
            con=DBHelper.makeConnection();
            if(con!=null){
                //2. create SQL string
                String sql="Select userID, fullname, admin "
                        + "From account "
                        + "Where email=?";
                //3. create stament
                ps=con.prepareStatement(sql);
                ps.setString(1, email);
                
                //4. excute query
                rs=ps.executeQuery();
                
                //5. check result
                if(rs.next()){
                    String userID=rs.getString("userID");
                    String fullname=rs.getString("fullname");
                    boolean admin=rs.getBoolean("admin");
                    AccountDTO dto=new AccountDTO();
                    dto.setUserID(userID);
                    dto.setAdmin(admin);
                    dto.setFullname(fullname);
                    return dto;
                }//end if result return not empty
                    
            }//end if connection connected
        }finally{
            if(rs!=null){
                rs.close();
            }//end if result set not null
            if(ps!=null){
                ps.close();
            }//end if prepared statement not null
            if(con!=null){
                con.close();
            }//end if connnection not null
        }
        return null; 
    }
    public boolean insertAccount(String userID,String username, String password, String fullname) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        try{
            //1. make connection
            con=DBHelper.makeConnection();
            if(con!=null){
                //2. create SQL string
                String sql="Insert Into account "
                        + "(userid,username,password,fullname,admin,email) "
                        + "Values (?,?,?,?,?,?)";
                //3. create stament
                ps=con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, username);
                ps.setString(3, password);
                ps.setString(4, fullname);
                ps.setBoolean(5, false);
                ps.setString(6,null);
                
                //4. excute query
                int row=ps.executeUpdate();
                
                //5. check result
                if(row==1){
                   return true;
                }//end if 1 row is affected
                    
            }//end if connection connected
        }finally{
            if(ps!=null){
                ps.close();
            }//end if prepared statement not null
            if(con!=null){
                con.close();
            }//end if connnection not null
        }
        return false;
    }
    public boolean insertAccount(String userID,String fullname, String email) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        try{
            //1. make connection
            con=DBHelper.makeConnection();
            if(con!=null){
                //2. create SQL string
                String sql="Insert Into account "
                        + "(userid,username,password,fullname,admin,email) "
                        + "Values (?,?,?,?,?,?)";
                //3. create stament
                ps=con.prepareStatement(sql);
                ps.setString(1, userID);
                ps.setString(2, null);
                ps.setString(3, null);
                ps.setString(4, fullname);
                ps.setBoolean(5, false);
                ps.setString(6,email);
                
                //4. excute query
                int row=ps.executeUpdate();
                
                //5. check result
                if(row==1){
                   return true;
                }//end if 1 row is affected
                    
            }//end if connection connected
        }finally{
            if(ps!=null){
                ps.close();
            }//end if prepared statement not null
            if(con!=null){
                con.close();
            }//end if connnection not null
        }
        return false;
    }
    public int countAccount() throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count=0;
        try{
            //1. make connection
            con=DBHelper.makeConnection();
            if(con!=null){
                //2. create SQL string
                String sql="Select Count(userID) as quantity"
                        + "From account";
                //3. create stament
                ps=con.prepareStatement(sql);
                
                //4. excute query
                rs=ps.executeQuery();
                
                //5. check result
                if(rs.next()){
                    count=rs.getInt("quantity");
                    return count;
                }//end if result return not empty
                    
            }//end if connection connected
        }finally{
            if(rs!=null){
                rs.close();
            }//end if result set not null
            if(ps!=null){
                ps.close();
            }//end if prepared statement not null
            if(con!=null){
                con.close();
            }//end if connnection not null            
        }
        return count;
    }
}
