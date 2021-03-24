/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.servlet;

import datdt.account.AccountDTO;
import datdt.orderdb.OrderDBDAO;
import datdt.orderdb.OrderDBDTO;
import datdt.orderdetail.OrderDetailDAO;
import datdt.orderdetail.OrderDetailObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
@WebServlet(name = "SearchHistoryOrderServlet", urlPatterns = {"/SearchHistoryOrderServlet"})
public class SearchHistoryOrderServlet extends HttpServlet {
    private final String HISTORY_PAGE="OrderHistoryPage.jsp";
    private final String SHOP_PAGE="ShopPage.jsp";
    private final String ERROR_PAGE="Error.jsp";
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
            AccountDTO account=(AccountDTO)session.getAttribute("ACCOUNT");
            if(account!=null){
                if(!account.isAdmin()){
                    String proNameSearch=request.getParameter("proNameSearch");
                    if(proNameSearch==null){
                        proNameSearch="";
                    }
                    String dateSearch=request.getParameter("dateSearch");
                    String userID=account.getUserID();
                    Timestamp date;
                    Timestamp fromDate;
                    if(dateSearch==null || dateSearch.isEmpty()){
                        Date datetime=new Date();
                        date=new Timestamp(datetime.getTime()); 
                        fromDate =new Timestamp(0);                        
                    }else{ 
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
                        Date datetime=sdf.parse(dateSearch);
                        fromDate=new Timestamp(datetime.getTime()-60*1000);
                        date=new Timestamp(fromDate.getTime()+24*60*60*1000-1);
                    }
                    
 
                    OrderDBDAO orderDBDAO=new OrderDBDAO();
                    orderDBDAO.searchOrder(userID, proNameSearch, fromDate, date);
                    List<OrderDBDTO> orderList=orderDBDAO.getOrderedList();
                    
                    
                    HashMap<OrderDBDTO,List<OrderDetailObject>> orderHistoryMap=new HashMap<OrderDBDTO,List<OrderDetailObject>>();
                    if(orderList!=null){
                        for (OrderDBDTO orderDBDTO : orderList) {
                        OrderDetailDAO orderDetailDAO=new OrderDetailDAO();
                        orderDetailDAO.searchOrderDetailByOrderID(orderDBDTO.getOrderID());
                        orderHistoryMap.put(orderDBDTO, orderDetailDAO.getProList());
                        }
                    }
                    
                    
                    request.setAttribute("ORDERHISTORYMAP", orderHistoryMap);
                    url=HISTORY_PAGE;
                    
                    
                }
            }
        } catch (NamingException ex) {
            log("ERROR at SearchHistoryOrderServlet_Naming: "+ex.getMessage());
        } catch (SQLException ex) {
            log("ERROR at SearchHistoryOrderServlet_SQL: "+ex.getMessage());
        } catch (ParseException ex) {
            log("ERROR at SearchHistoryOrderServlet_Parse: "+ex.getMessage());
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
