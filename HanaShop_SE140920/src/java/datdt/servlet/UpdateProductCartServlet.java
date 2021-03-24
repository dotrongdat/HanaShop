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
@WebServlet(name = "UpdateProductCartServlet", urlPatterns = {"/UpdateProductCartServlet"})
public class UpdateProductCartServlet extends HttpServlet {

    private final String ERROR_PAGE = "Error.jsp";
    private final String PREPARE_CART_CONTROLLER = "PrepareCartServlet";
    private final String LOGIN_PAGE = "login.jsp";

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
        HttpSession session = request.getSession();
        String url = PREPARE_CART_CONTROLLER;
        try {
            AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
            if (account != null) {
                if (!account.isAdmin()) {
                    String userID = account.getUserID();
                    String proID = request.getParameter("proID");
                    String txtQuantity = request.getParameter("quantity");
                    if (proID == null || txtQuantity == null) {
                        url = PREPARE_CART_CONTROLLER;
                    } else {
                        int quantity = Integer.parseInt(txtQuantity);
                        ProductDAO productDAO = new ProductDAO();
                        CartDAO cartDAO = new CartDAO();
                        CartObject cartObj = cartDAO.getCart(userID);
                        boolean checkQuantity = productDAO.checkQuantity(proID, quantity);
                        if (checkQuantity) {
                            cartObj.updateToCart(proID, quantity);
                            boolean result = cartDAO.updateCart(userID, cartObj);

                            if (result) {
                                url = PREPARE_CART_CONTROLLER;
                            }
                        } else {
                            url = PREPARE_CART_CONTROLLER.concat("?msg=Can't add more to Cart (quantity of items in cart > quantity of available items)");
                        }
                    }
                }

            } else {
                url = LOGIN_PAGE;
            }
        } catch (NamingException ex) {
            log("ERROR at UpdateProductCartServlet_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("ERROR at UpdateProductCartServlet_SQL: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("ERROR at UpdateProductCartServlet_ClassNotFound: " + ex.getMessage());
        } catch(NullPointerException ex){            
        } 
        catch (NumberFormatException ex) {            
        } finally {
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
