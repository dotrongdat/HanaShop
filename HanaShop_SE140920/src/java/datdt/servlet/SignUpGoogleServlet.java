/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.servlet;

import datdt.account.AccountDAO;
import datdt.account.AccountDTO;
import datdt.googleutil.GoogleUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "SignUpGoogleServlet", urlPatterns = {"/SignUpGoogleServlet"})
public class SignUpGoogleServlet extends HttpServlet {
    private final String PREPARE_SHOP_PAGE_CONTROLLER="PrepareShopPageServlet";
    private final String SIGN_UP_GOOGLE_PAGE="SignUpGooglePage.jsp"; 
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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        String url=PREPARE_SHOP_PAGE_CONTROLLER;
        try {
            AccountDTO account=(AccountDTO)session.getAttribute("ACCOUNT");
            if(account==null){                    
                    String code=request.getParameter("txtCode");
                    String cmpCode=(String)session.getAttribute("CODE");
                    String email=(String)session.getAttribute("EMAIL");
                    if(cmpCode!=null && cmpCode.equals(code)){
                        String fullname=request.getParameter("txtFullname").trim();
                    if(fullname.length()>=2 && fullname.length()<=50){
                        AccountDAO accountDAO=new AccountDAO();
                        if(accountDAO.checkLogin(email)==null){
                            int count=accountDAO.countAccount()+9;
                            String userID=""+count;
                            boolean result= accountDAO.insertAccount(userID, fullname, email);
                            if(result){
                                account=new AccountDTO(userID, fullname, false, email);                                
                                session.setAttribute("ACCOUNT", account);
                            }else{
                                url=LOGIN_PAGE.concat("?msg=Fail to login by Google Account");
                            }
                        }                        
                    }else{
                        url=SIGN_UP_GOOGLE_PAGE.concat("?msg=Fullname must only contain from 2 to 50 characters&txtCode="+code);
                    }
                   }else {
                        url=LOGIN_PAGE;
                    }
                                        
            }else{
                url=PREPARE_SHOP_PAGE_CONTROLLER;
            }
        } catch (NamingException ex) {
            log("ERROR at SignUpGoogleServlet_Naming: "+ex.getMessage());
        } catch (SQLException ex) {
            log("ERROR at SignUpGoogleServlet_SQL: "+ex.getMessage());
        }
        finally{
            session.removeAttribute("CODE");
            session.removeAttribute("EMAIL");
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
