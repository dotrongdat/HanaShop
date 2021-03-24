/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.servlet;

import datdt.account.AccountDTO;
import datdt.product.ProductDAO;
import datdt.product.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "PrepareProductPageServlet", urlPatterns = {"/PrepareProductPageServlet"})
public class PrepareProductPageServlet extends HttpServlet {
    private final String PRODUCT_PAGE="ProductPage.jsp";
    private final String ERROR_PAGE="Error.jsp";
    private final String SHOP_PAGE="ShopPage.jsp";
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
            boolean admin=false;
            AccountDTO account=(AccountDTO)session.getAttribute("ACCOUNT");
            if(account!=null){
                admin=account.isAdmin();
            }
            
            String proID=request.getParameter("proID");
            ProductDAO proDAO=new ProductDAO();
            ProductDTO proDTO=proDAO.searchProductByID(proID, admin);
            if(proDTO!=null){
                url=PRODUCT_PAGE;
                proDAO.searchProductsOftenPurchasedTogether(proID);
                List<ProductDTO> oftenPurchasedTogetherProList=proDAO.getProList();
                Integer total=(int)Math.ceil((double)oftenPurchasedTogetherProList.size()/3);
                request.setAttribute("TOTAL", total);
                request.setAttribute("OFTENPURCHASEDPRODUCT", oftenPurchasedTogetherProList);
                request.setAttribute("PRODUCT", proDTO);
            }
            
        } catch (NamingException ex) {
            log("ERROR at PrepareProductServlet_SQL: "+ex.getMessage());
        } catch (SQLException ex) {
            log("ERROR at PrepareProductServlet_Naming: "+ex.getMessage());
        }finally{
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
