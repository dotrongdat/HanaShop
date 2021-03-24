/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.servlet;

import datdt.account.AccountCreateError;
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
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {
    private final String PREPARE_SHOP_PAGE_CONTROLLER="PrepareShopPageServlet";
    private final String SIGN_UP_PAGE="SignUp.jsp";
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
        request.setCharacterEncoding("UTF-8");
        String url=SIGN_UP_PAGE;
        AccountCreateError error=new AccountCreateError();
        AccountDTO accountInfo=new AccountDTO();
        boolean errorExist=false;
        try {
            String username=request.getParameter("txtUsername").trim();
            String password=request.getParameter("txtPassword");
            String comfirm=request.getParameter("txtConfirm");
            String fullname=request.getParameter("txtFullname").trim();
            System.out.println("Fullname: "+fullname);
            
            if(username.length()<6 || username.length()>30){
                errorExist=true;
                error.setUsernameLengthError("Username contains from 6 to 30 characters");
            }else{
                accountInfo.setUsername(username);
            }
            if(password.length()<6||password.length()>30){
                errorExist=true;
                error.setPasswordLengthError("Password contains from 6 to 30 characters");
            }else if(!password.matches(".+[A-Za-z0-9]+.+")){
                errorExist=true;
                error.setPasswordFormatError("Password must contain numeric character or letter");
            }else{
                if(!comfirm.equals(password)){
                errorExist=true;
                error.setConfirmNotMatched("Confirm is not matched");
                }
            }            
            if(fullname.length()<2 || fullname.length()>50){
                errorExist=true;
                error.setFullnameLengthError("Fullname contains from 2 to 50 characters");
            }else{
                accountInfo.setFullname(fullname);
            }
            //check email existed
            
            if(!errorExist){
                AccountDAO dao=new AccountDAO();
                int count=dao.countAccount()+9;
                String userid=""+count;
                boolean result=dao.insertAccount(userid, username, password, fullname);
                if(result){
                    AccountDTO dto=new AccountDTO(userid, username, password, fullname, false, null);
                    CartDAO cartDAO=new CartDAO();
                    cartDAO.insertCart(userid, userid);
                    //CartObject cart=new CartObject();
                    HttpSession session=request.getSession();
                    session.setAttribute("ACCOUNT", dto);
                    //session.setAttribute("CART", cart);
                    url=PREPARE_SHOP_PAGE_CONTROLLER;
                }
            }else{
                request.setAttribute("ACCOUNTINFO", accountInfo);
                request.setAttribute("ERROR", error);
            }
        } catch (NamingException ex) {
            log("ERROR at SignUpServlet_Naming: "+ex.getMessage());
        }catch(NullPointerException ex){ 
        }catch (SQLException ex) {
            String msg=ex.getMessage();
            if(msg.contains("duplicate")){
                errorExist=true;
                    error.setUsernameDuplicate("Username has existed");
                request.setAttribute("ERROR", error);
            }else{
                log("ERROR at SignUpServlet_SQL: "+msg);
            }
            
        }finally{
            if(errorExist){
                RequestDispatcher rd=request.getRequestDispatcher(url);
                rd.forward(request, response);
            }else{
                response.sendRedirect(url);
            }
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
