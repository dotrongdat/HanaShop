/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.category;

import datdt.utilities.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author TRONG DAT
 */
public class CategoryDAO implements Serializable{
    private List<CategoryDTO> cateList;
    public CategoryDAO(){}

    public List<CategoryDTO> getCateList() {
        return cateList;
    }

    public void setCateList(List<CategoryDTO> cateList) {
        this.cateList = cateList;
    }
    
    public void setupCategory() throws NamingException, SQLException{
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Select cateID, cateName "
                        + "From category";
                stm=con.createStatement();
                rs= stm.executeQuery(sql);
                this.cateList=new ArrayList<CategoryDTO>();
                while(rs.next()){
                    String cateID=rs.getString("cateID");
                    String cateName=rs.getString("cateName");
                    CategoryDTO dto=new CategoryDTO(cateID, cateName);
                    this.cateList.add(dto);
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
    public boolean insertCategory(String cateID, String cateName) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Insert into Category "
                  + "(cateID,cateName,status) "
                  + "Values(?,?,?)";
                
                ps=con.prepareStatement(sql);
                ps.setString(1, cateID);
                ps.setString(2, cateName);
                ps.setBoolean(3, true);
                
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
    public int countTotal() throws NamingException, SQLException{
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Select Count(cateID) as quantity "
                  + "From Category ";
                stm=con.createStatement();
                rs=stm.executeQuery(sql);

                if(rs.next()){
                    int quantity=rs.getInt("quantity");
                    return quantity;
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
    public boolean updateCategory(String cateID,String cateName,boolean status) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        Statement stm=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Update Category "
                        + "Set cateName=?, status=? "
                        + "Where cateID=?";
                ps=con.prepareStatement(sql);
                ps.setString(1, cateName);
                ps.setBoolean(2, status);
                ps.setString(3, cateID);
                
                int result=ps.executeUpdate();
                if(result==1){
                    return true;
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
            if(ps!=null){
                ps.close();
            }
        }
        return false;
    }
}
