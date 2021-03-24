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
@WebServlet(name = "ActiveProductServlet", urlPatterns = {"/ActiveProductServlet"})
public class ActiveProductServlet extends HttpServlet {

    private final String SEARCH_CONTROLLER = "SearchServlet";
    private final String SHOP_PAGE = "ShopPage.jsp";
    private final String ERROR_PAGE = "Error.jsp";

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
        String url = SHOP_PAGE;
        HttpSession session = request.getSession(false);
        try {
            AccountDTO account = (AccountDTO) session.getAttribute("ACCOUNT");
            if (account != null && account.isAdmin()) {
                String[] selectedItem = request.getParameterValues("selectedItem");
                if (selectedItem != null) {
                    for (String proID : selectedItem) {
                        ProductDAO proDAO = new ProductDAO();
                        boolean result = proDAO.updateProduct(proID, true);

                        if (result) {
                            String activity = "Active_ProductID: " + proID;
                            HistoryDAO historyDAO = new HistoryDAO();
                            String id = "" + historyDAO.countTotal() + 1;
                            String userID = account.getUserID();
                            Date currentDate = new Date();
                            Timestamp updateDate = new Timestamp(currentDate.getTime());
                            historyDAO.addToHistory(id, userID, updateDate, activity);
                        }
                    }
                }
            }
            url = SEARCH_CONTROLLER;

        } catch (SQLException ex) {
            log("ERROR at DeleteProductServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("ERROR at DeleteProductServlet_SQL: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
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
