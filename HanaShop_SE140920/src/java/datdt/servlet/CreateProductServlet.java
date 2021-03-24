/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.servlet;


import datdt.account.AccountDTO;
import datdt.product.ProductDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author TRONG DAT
 */
@WebServlet(name = "CreateProductServlet", urlPatterns = {"/CreateProductServlet"})
public class CreateProductServlet extends HttpServlet {
    private final String PREPARE_SHOP_PAGE_CONTROLLER = "PrepareShopPageServlet";
    private final String SHOP_PAGE="ShopPage.jsp";
    private final String ERROR_PAGE="Error.jsp"; 
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url=SHOP_PAGE;
        HttpSession session=request.getSession();
        try {
            AccountDTO account=(AccountDTO)session.getAttribute("ACCOUNT");
            if(account!=null){
                if(account.isAdmin()){
                   boolean isMultipart=ServletFileUpload.isMultipartContent(request);
            if(isMultipart){
            List items=(List)request.getAttribute("listFileItem");
            Iterator iter=items.iterator();
            Map<String,String> params=new HashMap<String, String>();
            String image="";
            while(iter.hasNext()){
                FileItem item=(FileItem)iter.next();
                if(item.isFormField()){
                    params.put(item.getFieldName(), item.getString());
                }else{
                    try{
                        byte[] data=item.get();
                        image=Base64.encodeBase64String(data);
                    } catch (Exception ex) {
                        log("ERROR at CreateProductServlet: "+ex.getMessage());
                    }
                    
                }
            }
            String cateID=params.get("cateID");
            String proName=params.get("proName");
            String txtQuantity=params.get("quantity");
            int quantity=Integer.parseInt(txtQuantity);
            String description=params.get("description");
            int price=Integer.parseInt(params.get("price"));
            Date currentTime=new Date();
            Timestamp createDate=new Timestamp(currentTime.getTime());
            
            ProductDAO proDAO=new ProductDAO();
            String proID=""+proDAO.CountTotal()+1;
            boolean result=proDAO.insertProduct(proID, cateID, proName, quantity, image, description, price, createDate);
            if(result){
                url=PREPARE_SHOP_PAGE_CONTROLLER.concat("?msg=Create new product successfully");
            }
            } 
                }
            }
            
        } catch (SQLException ex) {
            log("ERROR at CreateProductServlet_SQL: "+ex.getMessage());
        } catch (NamingException ex) {
            log("ERROR at CreateProductServlet_Naming: "+ex.getMessage());
        }finally{
            response.sendRedirect(url);
            out.close();
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
