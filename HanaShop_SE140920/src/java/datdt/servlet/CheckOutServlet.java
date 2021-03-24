/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.servlet;

import datdt.account.AccountDTO;
import datdt.cart.CartDAO;
import datdt.cart.CartObject;
import datdt.orderdb.OrderDBDAO;
import datdt.orderdb.OrderDBDTO;
import datdt.orderdetail.OrderDetailDAO;
import datdt.product.ProductDAO;
import datdt.product.ProductObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
    private final String SHOP_PAGE="ShopPage.jsp";
    private final String ERROR_PAGE="Error.jsp";
    private final String PREPARE_CART_CONTROLLER="PrepareCartServlet";
    private final String CART_PAGE="CartPage.jsp";
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
        String url=SHOP_PAGE;
        try {
            AccountDTO account=(AccountDTO)session.getAttribute("ACCOUNT");
            if(account!=null){
                if(!account.isAdmin()){
                    String paypal=request.getParameter("paypal");
                    if(paypal!=null){
                    }else{
                    String userID=account.getUserID();
                    CartDAO cartDAO=new CartDAO();
                    CartObject cartObj=cartDAO.getCart(userID);
                    ProductDAO proDAO=new ProductDAO();
                    HashMap<String,Integer> cartMap=cartObj.getProList();
                    HashMap<String,ProductObject> proList=(HashMap<String,ProductObject>)session.getAttribute("CART");
                    boolean checkQuantity=true;
                    for (String key : cartMap.keySet()) {
                        int quantityInCart=cartMap.get(key);
                        if(!proDAO.checkQuantity(key, quantityInCart)){
                            ProductObject proObj=(ProductObject)proDAO.searchProductByID(key, false);
                            proObj.setQuantityInCart(quantityInCart);
                            proList.put(key, proObj);
                            checkQuantity=false;
                        }
                    }
                    if(checkQuantity){
                        OrderDBDTO orderInfo=(OrderDBDTO)request.getAttribute("ORDERINFO");
                       
                        OrderDBDAO orderDBDAO=new OrderDBDAO();
                        String orderID=userID+"_"+orderDBDAO.countOrderByUserID(userID)+1;
                        String address;
                        String phoneNumber;
                                               
                        if(orderInfo==null){                             
                             address=request.getParameter("address");
                             phoneNumber=request.getParameter("phoneNumber");
                             session.setAttribute("ORDERINFO", new OrderDBDTO(orderID, userID, null, address, phoneNumber, ""));
                        }else{
                             address=orderInfo.getAddress();
                             phoneNumber=orderInfo.getPhoneNumber();
                        }
                        Date currentDate=new Date();
                        Timestamp date=new Timestamp(currentDate.getTime()); 
                        String paypalID=request.getParameter("paypalID");
                        if(paypalID==null){
                            paypalID="";
                        }
                        
                        boolean orderResult=orderDBDAO.insertOrder(orderID, userID, date,address,phoneNumber,paypalID);
                        if(orderResult){
                            OrderDetailDAO orderDetailDAO=new OrderDetailDAO();   
                            boolean check=true;
                            for (String key : cartMap.keySet()) {
                            int quantity=cartMap.get(key);
                            int price=proList.get(key).getPrice();
                             check=orderDetailDAO.insertOrderDetail(orderID, key, quantity,price);
                             check=proDAO.subtractProductQuantity(key, quantity);
                            }
                            cartObj=new CartObject();
                            cartDAO.updateCart(userID, cartObj);
                            if(check){
                                session.removeAttribute("CART");
                            }                            
                        }
                        
                    }else{
                        session.setAttribute("CART", proList);
                    }
                    url=CART_PAGE;
                    }   
                }else{
                    url=SHOP_PAGE;
                }
            }else{
                url=LOGIN_PAGE;
            }
        } catch (NamingException ex) {
            log("ERROR at CheckOutServlet_Naming: "+ex.getMessage());
        } catch (SQLException ex) {
            log("ERROR at CheckOutServlet_SQL: "+ex.getMessage());
        } catch (ClassNotFoundException ex) {
            log("ERROR at CheckOutServlet_ClassNotFound: "+ex.getMessage());
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
