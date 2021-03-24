/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.product;

import datdt.utilities.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author TRONG DAT
 */
public class ProductDAO implements Serializable{
    private List<ProductDTO> proList;
    private final int PRODUCT_PER_PAGE=20;
    private final int PRODUCT_SUGGEST_PERTIME=12;
//    private int total;
    public ProductDAO(){}

    public List<ProductDTO> getProList() {
        return proList;
    }

        
    public void setProList(List<ProductDTO> proList) {
        this.proList = proList;
    }


    public void searchProduct(String cateID,String proNameSearch,int from,int to,boolean status, boolean isAdmin,int page) throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Select proID,cateID,proName,quantity,status,image,description,price,createDate "
                        + "From Product "
                        + "Where cateID like ? And proName like ? And price between ? And ? And status=? ";
                if(!isAdmin){
                       sql=sql.concat(" And quantity>0 ");
                }
                       sql=sql.concat("Order by createDate DESC "
                        + "Offset ? Rows "
                        + "Fetch Next ? Rows Only ");                                        
                ps=con.prepareStatement(sql);
                ps.setString(1, cateID);
                ps.setString(2, "%"+proNameSearch+"%");
                ps.setInt(3, from);
                ps.setInt(4, to);
                ps.setBoolean(5, status);
                int offsetRows=(page-1)*PRODUCT_PER_PAGE;
                ps.setInt(6, offsetRows);
                ps.setInt(7, PRODUCT_PER_PAGE);
                
                
                
                
                rs=ps.executeQuery();
                
                while(rs.next()){
                    String proID=rs.getString("proID");
                    cateID=rs.getString("cateID");
                    String proName=rs.getString("proName");
                    int quantity=rs.getInt("quantity");
                    String image=rs.getString("image");
                    String description=rs.getString("description");
                    int price=rs.getInt("price");
                    Timestamp createDate=rs.getTimestamp("createDate");
                    
                    if(this.proList==null){
                        this.proList=new ArrayList<ProductDTO>();
                    }
                    ProductDTO dto=new ProductDTO(proID, cateID, proName, quantity, status, image, description, price, createDate);
                    this.proList.add(dto);
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
    public ProductDTO searchProductByID(String proID,boolean admin) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Select cateID,proName,quantity,status,image,description,price,createDate "
                        + "From product "
                        + "Where proID=? ";
                if(!admin){
                    sql=sql.concat(" And status=1 And quantity>0");
                }
                ps=con.prepareStatement(sql);
                ps.setString(1, proID);
                
                rs=ps.executeQuery();
                
                if(rs.next()){
                    String cateID=rs.getString("cateID");
                    String proName=rs.getString("proName");
                    int quantity=rs.getInt("quantity");
                    boolean status=rs.getBoolean("status");
                    String image=rs.getString("image");
                    String description=rs.getString("description");
                    int price=rs.getInt("price");
                    Timestamp createDate=rs.getTimestamp("createDate");
                    ProductDTO dto=new ProductDTO(proID, cateID, proName, quantity, status, image, description, price, createDate);
                    return dto;
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
        return null;
    }

    public int countTotal(String cateID,String proNameSearch,int from,int to,boolean status,boolean isAdmin) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
               String sql="Select Count(proID) as totalQuantity "
                       + "From Product "
                       + "Where cateID like ? And proName like ? And price between ? And ? And status=?";
               
               if(!isAdmin){
                    sql=sql.concat(" And quantity>0 ");
                }
               ps=con.prepareStatement(sql);
               ps.setString(1, cateID);
               ps.setString(2, "%"+proNameSearch+"%");
               ps.setInt(3, from);
               ps.setInt(4, to);
               ps.setBoolean(5, status);
               rs=ps.executeQuery();
               if(rs.next()){
                   System.out.println("Total set: "+rs.getInt("totalQuantity"));
                   int total=(int)Math.ceil((double)rs.getInt("totalQuantity")/this.PRODUCT_PER_PAGE) ;                   
                   return total;
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
    public int CountTotal() throws SQLException, NamingException{
        Connection con=null;
        Statement stm=null;
        ResultSet rs=null;
        
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
               String sql="Select Count(proID) As quantity "
                       + "From product ";
               stm=con.createStatement();
               rs=stm.executeQuery(sql);
               if(rs.next()){
                   int total=rs.getInt("quantity");
                   return total;
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
    public boolean updateProduct(String proID,boolean status) throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Update product "
                        + "Set status=? "
                        + "Where proID=?";
                
                ps=con.prepareStatement(sql);
                ps.setBoolean(1, status);
                ps.setString(2, proID);
                
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
    public boolean subtractProductQuantity(String proID,int quantity) throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Update product "
                        + "Set quantity=quantity-? "
                        + "Where proID=?";
                
                ps=con.prepareStatement(sql);
                ps.setInt(1, quantity);
                ps.setString(2, proID);
                
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
    public boolean updateProduct(String proID, String cateID,String proName,int quantity, String image, String description, int price, boolean status) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Update product "
                        + "Set cateID=? ,proName=?, quantity=?, image=?, description=?, price=?, status=? "
                        + "Where proID=?";
                
                ps=con.prepareStatement(sql);
                ps.setString(1, cateID);
                ps.setString(2, proName);
                ps.setInt(3, quantity);
                ps.setString(4, image);
                ps.setString(5, description);
                ps.setInt(6, price);
                ps.setBoolean(7, status);
                ps.setString(8, proID);
                
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
    public boolean updateProduct(String proID, String cateID,String proName,int quantity, String description, int price,boolean status) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Update product "
                        + "Set cateID=? ,proName=?, quantity=?, description=?, price=?, status=?  "
                        + "Where proID=?";
                
                ps=con.prepareStatement(sql);
                ps.setString(1, cateID);
                ps.setString(2, proName);
                ps.setInt(3, quantity);
                ps.setString(4, description);
                ps.setInt(5, price);
                ps.setBoolean(6, status);
                ps.setString(7, proID);
                
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
    public boolean insertProduct(String proID, String cateID,String proName,int quantity, String image, String description, int price, Timestamp createDate) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Insert into product "
                        + "(proID,cateID,proName,quantity,image,description,price,createDate,status) "
                        + "Values (?,?,?,?,?,?,?,?,?)";
                
                ps=con.prepareStatement(sql);
                ps.setString(1, proID);
                ps.setString(2, cateID);
                ps.setString(3, proName);
                ps.setInt(4, quantity);
                ps.setString(5, image);
                ps.setString(6, description);
                ps.setInt(7, price);
                ps.setTimestamp(8, createDate);
                ps.setBoolean(9, true);
                
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
    public boolean checkQuantity(String proID,int quantity) throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Select proID "
                        + "From Product "
                        + "Where proID=? And quantity>=? And status=1";
                ps=con.prepareStatement(sql);
                ps.setString(1, proID);
                ps.setInt(2, quantity);
                rs=ps.executeQuery();
                if(rs.next()){
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
            if(rs!=null){
                rs.close();
            }
        }
        return false;
    }
    
    public void searchProductsOftenPurchasedTogether(String proID) throws SQLException, NamingException{
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            con=DBHelper.makeConnection();
            if(con!=null){
                String sql="Select Top(?) Count(od.proID) as total, od.proID,p.cateID,p.proName,p.quantity,p.status,p.image,p.description,p.price,p.createDate "
                        + "From OrderDetail od, Product p "
                        + "Where od.proID = p.proID "
                              + "And od.orderID in (Select orderID "
                                           + "From OrderDetail "
                                           + "Where proID = ?) "
                              + "And od.proID <> ? "
                              + "And p.status=1 "
                        + "Group By od.proID,p.cateID,p.proName,p.quantity,p.status,p.image,p.description,p.price,p.createDate  "
                        + "Order By total DESC";
                ps=con.prepareStatement(sql);
                ps.setInt(1, this.PRODUCT_SUGGEST_PERTIME);
                ps.setString(2, proID);
                ps.setString(3, proID);
                
                rs=ps.executeQuery();
                this.proList=new ArrayList<ProductDTO>();
                while(rs.next()){
                    String proIDGet=rs.getString("proID");
                    String cateID=rs.getString("cateID");
                    String proName=rs.getString("proName");
                    int quantity=rs.getInt("quantity");
                    String image=rs.getString("image");
                    String description=rs.getString("description");
                    int price=rs.getInt("price");
                    Timestamp createDate=rs.getTimestamp("createDate");                    
                    ProductDTO dto=new ProductDTO(proIDGet, cateID, proName, quantity, true, image, description, price, createDate);
                    this.proList.add(dto);
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
