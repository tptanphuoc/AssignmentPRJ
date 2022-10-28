/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huynh
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {
    
    private static final String ERROR = "error.jsp";
    
    private static final String HOME= "Home";
    private static final String HOME_CONTROLLER = "HomeController";
    
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    
    private static final String SIGNUP = "Signup";
    private static final String SIGNUP_CONTROLLER = "SignupController";
    
    private static final String ASCENDING = "Ascending";
    private static final String ASCENDING_CONTROLLER = "AscendingController";

    private static final String DESCENDING = "Descending";
    private static final String DESCENDING_CONTROLLER = "DescendingController";
    
    private static final String CATEGORY = "Category";
    private static final String CATEGORY_CONTROLLER = "CategoryController";
    
    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchController";
    
    private static final String DETAIL = "Detail";
    private static final String DETAIL_CONTROLLER = "DetailController";
    
    private static final String MANAGE_PRODUCT = "ManageProduct";
    private static final String PRODUCT_CONTROLLER = "ProductController";
    
    private static final String ADD_PRODUCT = "AddProduct";
    private static final String ADD_PRODUCT_CONTROLLER = "AddProductController";
    
    private static final String DELETE_PRODUCT = "DeleteProduct";
    private static final String DELETE_PRODUCT_CONTROLLER = "DeleteProductController";
    
    private static final String PRODUCT_INFORMATION = "ProductInformation";
    private static final String PRODUCT_INFORMATION_CONTROLLER = "ProductInforController";
    
    private static final String UPDATE_PRODUCT = "UpdateProduct";
    private static final String UPDATE_PRODUCT_CONTROLLER = "UpdateProductController";
    
    private static final String MANAGE_CART = "ManageCart";
    private static final String CART_CONTROLLER = "CartController";
    
    private static final String ADD_TO_CART = "AddToCart";
    private static final String ADD_TO_CART_CONTROLLER = "AddToCartController";
    
    private static final String MANAGE_AMOUNT = "ManageAmount";
    private static final String MANAGE_AMOUNT_CONTROLLER = "ManageAmountController";
    
    private static final String REMOVE_FROM_CART = "RemoveFromCart";
    private static final String REMOVE_FROM_CART_CONTROLLER = "RemoveFromCartController";
    
    private static final String BUY_ONE = "BuyOneProduct";
    private static final String BUY_ONE_CONTROLLER = "BuyOneController";
    
    private static final String CHECK_OUT = "CheckOut";
    private static final String CHECK_OUT_CONTROLLER = "CheckOutController";
    
    private static final String MANAGE_ORDER = "ManageOrder";
    private static final String MANAGE_ORDER_CONTROLLER = "OrderController";
    
    private static final String SEND_MAIL = "SendMail";
    private static final String SEND_MAIL_CONTROLLER = "SendMailController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if(HOME.equals(action)) {
                url = HOME_CONTROLLER;
            } else if(LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if(LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;    
            }else if(SIGNUP.equals(action)) {
                url = SIGNUP_CONTROLLER;    
            } else if(ASCENDING.equals(action)) {
                url = ASCENDING_CONTROLLER;    
            } else if(DESCENDING.equals(action)) {
                url = DESCENDING_CONTROLLER;    
            } else if(CATEGORY.equals(action)) {
                url = CATEGORY_CONTROLLER;    
            } else if(SEARCH.equals(action)) {
                url = SEARCH_CONTROLLER;    
            } else if(DETAIL.equals(action)) {
                url = DETAIL_CONTROLLER;    
            } else if(MANAGE_PRODUCT.equals(action)) {
                url = PRODUCT_CONTROLLER;  
            } else if(ADD_PRODUCT.equals(action)) {
                url = ADD_PRODUCT_CONTROLLER;  
            } else if(DELETE_PRODUCT.equals(action)) {
                url = DELETE_PRODUCT_CONTROLLER;  
            } else if(PRODUCT_INFORMATION.equals(action)) {
                url = PRODUCT_INFORMATION_CONTROLLER;
            } else if(UPDATE_PRODUCT.equals(action)) {
                url = UPDATE_PRODUCT_CONTROLLER;
            } else if(MANAGE_CART.equals(action)) {
                url = CART_CONTROLLER;
            } else if(ADD_TO_CART.equals(action)) {
                url = ADD_TO_CART_CONTROLLER;
            } else if(MANAGE_AMOUNT.equals(action)) {
                url = MANAGE_AMOUNT_CONTROLLER;
            } else if(REMOVE_FROM_CART.equals(action)) {
                url = REMOVE_FROM_CART_CONTROLLER;
            } else if(BUY_ONE.equals(action)) {
                url = BUY_ONE_CONTROLLER;
            } else if(CHECK_OUT.equals(action)) {
                url = CHECK_OUT_CONTROLLER;
            } else if(MANAGE_ORDER.equals(action)) {
                url = MANAGE_ORDER_CONTROLLER;
            } else if(SEND_MAIL.equals(action)) {
                url = SEND_MAIL_CONTROLLER;
            }
            else request.setAttribute("ERROR", "Your function is not supported !");
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
