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
import datdt.product.ProductDTO;
import datdt.product.ProductObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
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
@WebServlet(name = "GetOrderDetailServlet", urlPatterns = {"/GetOrderDetailServlet"})
public class GetOrderDetailServlet extends HttpServlet {
    private final String CHECK_OUT_PAGE="CheckOutPage.jsp";
    private final String LOGIN_PAGE="login.jsp";
    private final String PREPARE_SHOP_PAGE_CONTROLLER="PrepareShopPageServlet";
    private final String PREPARE_CART_CONTROLLER="PrepareCartServlet";
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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String url=LOGIN_PAGE;
        HttpSession session=request.getSession(false);
        String userID=null;
        try {
            AccountDTO account =(AccountDTO)session.getAttribute("ACCOUNT");
            if(account!=null){
                if(!account.isAdmin()){
                    userID=account.getUserID();
                    
                    CartDAO cartDAO=new CartDAO();
                    CartObject cartObj=cartDAO.getCart(userID);
                    HashMap<String,Integer> cart=cartObj.getProList();
                    ProductDAO proDAO=new ProductDAO();
                    HashMap<String,ProductObject> proList=new HashMap<String,ProductObject>();
                    if(cart!=null && !cart.isEmpty()){
                        for (String key : cart.keySet()) {
                            int quantityInCart=cart.get(key);
                            ProductDTO dto=proDAO.searchProductByID(key, false);                            
                            ProductObject product=new ProductObject(dto.getProID(),dto.getCateID(),dto.getProName(),dto.getQuantity(),quantityInCart,dto.isStatus(),dto.getImage(),dto.getDescription(),dto.getPrice());
                            proList.put(product.getProID(), product);
                        }
                    }
                    session.setAttribute("CART", proList);
                    url=CHECK_OUT_PAGE;
                }else{
                    url=PREPARE_SHOP_PAGE_CONTROLLER;
                }
            }            
        } catch (NamingException ex) {
            log("ERROR at GetOrderDetailServlet_Naming: "+ex.getMessage());
        } catch (SQLException ex) {
            log("ERROR at GetOrderDetailServlet_SQL: "+ex.getMessage());
        }catch (ClassNotFoundException ex) {
            log("ERROR at GetOrderDetailServlet_ClassNotFound: "+ex.getMessage());
            CartDAO cartDAO=new CartDAO();
            try {
                cartDAO.insertCart(userID, userID);
            } catch (NamingException ex1) {
                log("ERROR at GetOrderDetailServlet_Naming: "+ex.getMessage());
            } catch (SQLException ex1) {
                log("ERROR at GetOrderDetailServlet_SQL: "+ex.getMessage());
            }
            url="ControllerServlet?btAction=PrepareCart";
        }
        finally{
            RequestDispatcher rd=request.getRequestDispatcher(url);
            rd.forward(request, response);
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
