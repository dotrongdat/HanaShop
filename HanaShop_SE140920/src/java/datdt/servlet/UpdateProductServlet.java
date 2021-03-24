/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.servlet;

import datdt.account.AccountDTO;
import datdt.history.HistoryDAO;
import datdt.product.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author TRONG DAT
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {
    private final String PREPARE_PRODUCT_PAGE_CONTROLLER="PrepareProductPageServlet";
    private final String PREPARE_SHOP_PAGE_CONTROLLER="PrepareShopPageServlet";
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
        String url=PREPARE_SHOP_PAGE_CONTROLLER;
        HttpSession session=request.getSession();
        try {
            AccountDTO account=(AccountDTO)session.getAttribute("ACCOUNT");
            if(account!=null && account.isAdmin()){
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
            String proID=params.get("proID");
            String cateID=params.get("cateID");
            String proName=params.get("proName");
            int quantity=Integer.parseInt(params.get("quantity"));
            String description=params.get("description");
            int price=Integer.parseInt(params.get("price")); 
            boolean status=params.get("status").equals("1");
            ProductDAO proDAO=new ProductDAO();
            boolean result;
            if(image.isEmpty()){
                result =proDAO.updateProduct(proID, cateID, proName, quantity, description, price,status);
            }else{
                result =proDAO.updateProduct(proID, cateID, proName, quantity, image, description, price,status);
            }
                    
            if(result){               
                Date currentTime=new Date();
                Timestamp updateDate=new Timestamp(currentTime.getTime());
                String activity="Update_Product: "+proID;
                String userID=account.getUserID();
                HistoryDAO historyDAO=new HistoryDAO();
                String id=""+historyDAO.countTotal()+1;
                historyDAO.addToHistory(id, userID, updateDate, activity);                
                url=PREPARE_PRODUCT_PAGE_CONTROLLER.concat("?proID="+proID);
            }
            }
            }
           
        } catch (NamingException ex) {
            Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductServlet.class.getName()).log(Level.SEVERE, null, ex);
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
