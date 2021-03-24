/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.servlet;

import datdt.account.AccountDTO;
import datdt.category.CategoryDAO;
import datdt.category.CategoryDTO;
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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
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
        HttpSession session=request.getSession();
        String url=SHOP_PAGE;
        try {
            Integer page;
            String txtPage=request.getParameter("page");
            if(txtPage==null || txtPage.isEmpty()){
                page=1;
            }else {
                page=Integer.parseInt(txtPage);
            }
            int totalPage=1;
            ProductDAO dao=new ProductDAO();
            boolean admin=false;
            AccountDTO account=(AccountDTO)session.getAttribute("ACCOUNT");
            if(account!=null){
                admin=account.isAdmin();
            }
           
           String proNameSearch=request.getParameter("proNameSearch");
           if(proNameSearch==null){
               proNameSearch="";
           }
           
           String cateID=request.getParameter("cateID");
           if(cateID==null || cateID.isEmpty()){
               cateID="%%";
           }
           
           String txtFromPrice=request.getParameter("fromPrice");
           String txtToPrice=request.getParameter("toPrice");
           int fromPrice;
           int toPrice;
           if(txtFromPrice==null || txtFromPrice.isEmpty()){
               fromPrice=0;
           }else{
               fromPrice=Integer.parseInt(txtFromPrice);
           }
           if(txtToPrice==null || txtToPrice.isEmpty()){
               toPrice=Integer.MAX_VALUE;
           }else{
               toPrice=Integer.parseInt(txtToPrice);
           }
           String txtStatus=request.getParameter("status");
           boolean status;
           if(txtStatus==null || txtStatus.isEmpty()){
               txtStatus="1";
           }
           if(txtStatus.equals("1")){
               status=true;
           }else{
               status=false;
           }
                totalPage=dao.countTotal(cateID, proNameSearch, fromPrice,toPrice, status, admin);
                if(page>totalPage && page>1){
                    page=totalPage;
                }
                dao.searchProduct(cateID, proNameSearch, fromPrice, toPrice, status,admin, page);
                                    
            List<ProductDTO> proList=dao.getProList();
            
            session.setAttribute("PROLIST",proList);
            session.setAttribute("TOTALPAGE", totalPage);
            session.setAttribute("PRONAMESEARCH",proNameSearch);
            session.setAttribute("CATEID", cateID);
            session.setAttribute("FROMPRICE", txtFromPrice);
            session.setAttribute("TOPRICE", txtToPrice);
            session.setAttribute("STATUS", txtStatus);
            session.setAttribute("PAGE", page);
            url=SHOP_PAGE;
        } catch (SQLException ex) {
            log("ERROR at SearchServlet_SQL: "+ex.getMessage());
        } catch (NamingException ex) {
            log("ERROR at SearchServlet_Naming: "+ex.getMessage());
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
