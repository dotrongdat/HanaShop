/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datdt.servlet;

import datdt.category.CategoryDAO;
import datdt.category.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author TRONG DAT
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {
    private final String START_UP_CONTROLLER="StartUpServlet";
    private final String LOGIN_CONTROLLER="LoginServlet";
    private final String LOGIN_GOOGLE_CONTROLLER="LoginGoogleServlet";
    private final String LOGOUT_CONTROLLER="LogOutServlet";
    private final String SIGNUP_CONTROLLER="SignUpServlet";
    private final String SIGNUP_GOOGLE_CONTROLLER="SignUpGoogleServlet";
    private final String PREPARE_SHOP_PAGE_CONTROLLER="PrepareShopPageServlet";
    private final String SEARCH_CONTROLLER="SearchServlet";
    private final String SEARCH_HISTORY_ORDER_CONTROLLER="SearchHistoryOrderServlet";
    private final String PREPARE_PRODUCT_PAGE_CONTROLLER="PrepareProductPageServlet";
    private final String PREPARE_CART_CONTROLLER="PrepareCartServlet";
    private final String UPDATE_CATEGORY_CONTROLLER="UpdateCategoryServlet";
    private final String UPDATE_PRODUCT_CART_CONTROLLER="UpdateProductCartServlet";
    private final String UPDATE_PRODUCT_CONTROLLER="UpdateProductServlet";
    private final String CHECK_OUT_CONTROLLER="CheckOutServlet";
    private final String CREATE_CATEGORY_CONTROLLER="CreateCategoryServlet";
    private final String CREATE_PRODUCT_CONTROLLER="CreateProductServlet";
    private final String DELETE_PRODUCT_CONTROLLER="DeleteProductServlet";
    private final String ADD_PRODUCT_CART_CONTROLLER="AddProductCartServlet";
    private final String ACTIVE_PRODUCT_CONTROLLER="ActiveProductServlet";
    private final String GET_ORDER_DETAIL_CONTROLLER="GetOrderDetailServlet";
    private final String PREPARE_HISTORY_ACTIVITY_PAGE_CONTROLLER="PrepareHistoryActivityPageServlet";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
//    @Override
//    public void init(ServletConfig config)
//            throws ServletException {
//        super.init(config); //To change body of generated methods, choose Tools | Templates.
//        
//        try {
//            CategoryDAO cateDAO=new CategoryDAO();
//            cateDAO.setupCategory();
//            List<CategoryDTO> cateDTO=cateDAO.getCateList();
//            
//            ServletContext context=config.getServletContext();
//            context.setAttribute("CATEGORY", cateDTO);
//        } catch (NamingException ex) {
//            log("ERROR at ControllerServlet_Naming: "+ex.getMessage());
//        } catch (SQLException ex) {
//            log("ERROR at ControllerServlet_SQL: "+ex.getMessage());
//        }       
//    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String button = "";
        if(ServletFileUpload.isMultipartContent(request)){
            FileItemFactory factory=new DiskFileItemFactory();
            ServletFileUpload upload=new ServletFileUpload(factory);
            try {                                
                List items= upload.parseRequest(request);
                request.setAttribute("listFileItem", items);
                Iterator iter=items.iterator();
                
                while(iter.hasNext()){
                    FileItem item=(FileItem)iter.next();
                    if(item.isFormField()){
                        if("btAction".equals(item.getFieldName())){
                            button=item.getString();
                        }
                    }
                }
            } catch (FileUploadException ex) {
                log("ERROR at ControllerServlet_FileUpload: "+ex.getMessage());
            }
        }else{
            button=request.getParameter("btAction");
        }
        
        String url=PREPARE_SHOP_PAGE_CONTROLLER;
        try {
            if(button==null){
                url=PREPARE_SHOP_PAGE_CONTROLLER;
            }else if(button.equals("Login")){
                url=LOGIN_CONTROLLER;
            }else if(button.equals("SignUp")){
                url=SIGNUP_CONTROLLER;
            }else if(button.equals("SignUpGoogle")){
                url=SIGNUP_GOOGLE_CONTROLLER;
            }else if(button.equals("LoginGoogle")){
                url=LOGIN_GOOGLE_CONTROLLER;
            }else if(button.equals("Search")){
                url=SEARCH_CONTROLLER;   
            }else if(button.equals("LogOut")){
                url=LOGOUT_CONTROLLER;
            }else if(button.equals("CheckOut")){
                url=CHECK_OUT_CONTROLLER;
            }else if(button.equals("CreateProduct")){
                url=CREATE_PRODUCT_CONTROLLER;
            }else if(button.equals("DeleteProduct")){
                url=DELETE_PRODUCT_CONTROLLER;
            }else if(button.equals("PrepareCart")){
                url=PREPARE_CART_CONTROLLER;
            }else if(button.equals("PrepareProduct")){
                url=PREPARE_PRODUCT_PAGE_CONTROLLER;
            }else if(button.equals("SearchHistoryOrder")){
                url=SEARCH_HISTORY_ORDER_CONTROLLER;
            }else if(button.equals("UpdateCategory")){
                url=UPDATE_CATEGORY_CONTROLLER;
            }else if(button.equals("UpdateProduct")){
                url=UPDATE_PRODUCT_CONTROLLER;
            }else if(button.equals("UpdateProductCart")){
                url=UPDATE_PRODUCT_CART_CONTROLLER;
            }else if(button.equals("AddProductCart")){
                url=ADD_PRODUCT_CART_CONTROLLER;
            }else if("ActiveProduct".equals(button)){
                url=ACTIVE_PRODUCT_CONTROLLER;
            }else if("GetOrderDetail".equals(button)){
                url=GET_ORDER_DETAIL_CONTROLLER;
            }else if("CreateCategory".equals(button)){
                url=CREATE_CATEGORY_CONTROLLER;
            }else if("PrepareHistoryActivity".equals(button)){
                url=PREPARE_HISTORY_ACTIVITY_PAGE_CONTROLLER;
            }
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
