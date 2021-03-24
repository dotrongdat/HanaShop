/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.servlet;

import datdt.account.AccountDAO;
import datdt.account.AccountDTO;
import datdt.cart.CartDAO;
import datdt.cart.CartObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
     private final String PREPARE_SHOP_PAGE_CONTROLLER="PrepareShopPageServlet";
     private final String ERROR_PAGE="Error.jsp";
     private final String LOGIN_PAGE="login.jsp";
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
        String url=ERROR_PAGE;
        HttpSession session=request.getSession();
        try {
            AccountDTO account=(AccountDTO)session.getAttribute("ACCOUNT");
            if(account==null){
            
            String username=request.getParameter("txtUsername");
            String password=request.getParameter("txtPassword");
            
            AccountDAO dao=new AccountDAO();
            account=dao.checkLogin(username, password);
            if(account!=null){
                
                session.setAttribute("ACCOUNT", account);
               // CartDAO cartDAO=new CartDAO();
               // CartObject cart=cartDAO.getCart(dto.getUserID());
//                session.setAttribute("CART", cart);
                url=PREPARE_SHOP_PAGE_CONTROLLER;
            }else{
                url=LOGIN_PAGE.concat("?msgLogin=Wrong username or password !");
            }
            }else{
                url=PREPARE_SHOP_PAGE_CONTROLLER;
            }
        } catch (NamingException ex) {
            log("ERROR at LoginServlet_Naming: "+ex.getMessage());
        } catch (SQLException ex) {
            log("ERROR at LoginServlet_SQL: "+ex.getMessage());
//        } catch (ClassNotFoundException ex) {
//             log("ERROR at LoginServlet_ClassNotFound: "+ex.getMessage());
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
