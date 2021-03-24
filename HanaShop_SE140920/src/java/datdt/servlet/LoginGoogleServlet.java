/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.servlet;

import datdt.account.AccountDAO;
import datdt.account.AccountDTO;
import datdt.googleutil.GooglePojo;
import datdt.googleutil.GoogleUtils;
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
@WebServlet(name = "LoginGoogleServlet", urlPatterns = {"/LoginGoogleServlet"})
public class LoginGoogleServlet extends HttpServlet {
    private  final String PREPARE_SHOP_PAGE_CONTROLLER="PrepareShopPageServlet";
    private  final String LOGIN_PAGE="login.jsp";
     private final String SIGN_UP_GOOGLE_PAGE="SignUpGooglePage.jsp"; 
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
        String url=PREPARE_SHOP_PAGE_CONTROLLER;
        try {
           AccountDTO account=(AccountDTO)session.getAttribute("ACCOUNT");
           if(account==null){
               String code=request.getParameter("code");
               
               if(code==null || code.isEmpty()){
                   url=LOGIN_PAGE;
               }else{
                   String accessToken=GoogleUtils.getToken(code);
                   System.out.println("Code: "+code);
                   GooglePojo googlePojo=GoogleUtils.getUserInfo(accessToken);
                   String email=googlePojo.getEmail();
                   String name=googlePojo.getName();
                   String ID=googlePojo.getId();
                   if(email==null || email.isEmpty()){
                       url=LOGIN_PAGE;
                   }else{
                       AccountDAO accountDAO=new AccountDAO();
                       account= accountDAO.checkLogin(email);
                       if(account!=null){
                           session.setAttribute("ACCOUNT", account);
                       }else{
                           session.setAttribute("CODE", code);
                           session.setAttribute("EMAIL", email);
                           url=SIGN_UP_GOOGLE_PAGE.concat("?txtCode="+code);
                       }
                   }
                   
               }
           }
        } catch (NamingException ex) {
            log("ERROR at LoginGoogleServlet_Naming: "+ex.getMessage());
        } catch (SQLException ex) {
            log("ERROR at LoginGoogleServlet_SQL: "+ex.getMessage());
        }catch (IOException ex){
            log("ERROR at LoginGoogleServlet_IO: "+ex.getMessage());
            url=LOGIN_PAGE;
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
