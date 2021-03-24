/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.servlet;

import datdt.account.AccountDTO;
import datdt.cart.CartDAO;
import datdt.cart.CartObject;
import datdt.product.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TRONG DAT
 */
@WebServlet(name = "AddProductCartServlet", urlPatterns = {"/AddProductCartServlet"})
public class AddProductCartServlet extends HttpServlet {
    private final String ERROR_PAGE="Error.jsp";
    private final String PREPARE_PRODUCT_PAGE_CONTROLLER="PrepareProductPageServlet";
    private final String PREPARE_SHOP_PAGE_CONTROLLER="PrepareShopPageServlet";
    private final String SHOP_PAGE="ShopPage.jsp";
    private final String LOGIN_PAGE="login.jsp";
    private final String ADD_PRODUCT_CART_CONTROLLER="AddProductCartServlet";
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
        HttpSession session=request.getSession();
        AccountDTO account=(AccountDTO)session.getAttribute("ACCOUNT");
        String url=SHOP_PAGE;
        String proID=request.getParameter("proID");
        String txtQuantity=request.getParameter("quantity");
        try {        
           if(account!=null){
               if(!account.isAdmin()){
                  String userID=account.getUserID();            
            if(proID==null||txtQuantity==null){
               url=PREPARE_PRODUCT_PAGE_CONTROLLER;
            }else{                
                int quantity=Integer.parseInt(txtQuantity);
                ProductDAO productDAO=new ProductDAO();
                CartDAO cartDAO=new CartDAO();
                CartObject cartObj=cartDAO.getCart(userID);
                Integer existQuantity=cartObj.getProList().get(proID);
                if(existQuantity==null){
                    existQuantity=0;
                }
                boolean checkQuantity=productDAO.checkQuantity(proID, existQuantity+quantity);
                if(checkQuantity){
                cartObj.addToCart(proID, quantity);
                boolean result= cartDAO.updateCart(userID, cartObj);
                
                if(result){
                    url=PREPARE_PRODUCT_PAGE_CONTROLLER.concat("?proID="+proID);
                }
                }else{
                    url=PREPARE_PRODUCT_PAGE_CONTROLLER.concat("?proID="+proID+"&msg=Can't add more to Cart (quantity of items in cart > quantity of available items)");
                } 
               }
               
                }else{
                   url=PREPARE_SHOP_PAGE_CONTROLLER;
               }                       
           }else{
               url=LOGIN_PAGE;
           }
                         
        } catch (NamingException ex) {
            log("ERROR at AddToCartServlet_Naming: "+ex.getMessage());
        } catch (SQLException ex) {
            log("ERROR at AddToCartServlet_SQL: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            try {
                log("ERROR at AddToCartServlet_ClassNotFound: "+ex.getMessage());
                CartDAO dao=new CartDAO();
                dao.insertCart(account.getUserID(), account.getUserID());
                url=ADD_PRODUCT_CART_CONTROLLER.concat("?proID="+proID+"&quantity="+txtQuantity);
            } catch (NamingException ex1) {
                log("ERROR at AddToCartServlet_Naming: "+ex.getMessage());
            } catch (SQLException ex1) {
                log("ERROR at AddToCartServlet_SQL: "+ex.getMessage());
            }
        }
        finally{
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
