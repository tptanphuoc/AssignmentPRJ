/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CartDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Cart;

/**
 *
 * @author huynh
 */
public class CartController extends HttpServlet {

    private static final String HOME = "HomeController";
    private static final String ERROR = "Login.jsp";
    private static final String SUCCESS = "ShoppingCart.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME;
        try {
            HttpSession session = request.getSession();
            Account account = (Account)session.getAttribute("LOGIN_USER");
            if(account == null) {
                request.setAttribute("alert", "Bạn chưa login");
                url = ERROR;
                return;
            }
            if(account.getIsAdmin() != 0) {
                request.setAttribute("alert", "Bạn không có quyền truy cập vào trang này");
                url = HOME;
                return;
            }
            CartDAO cartDao = new CartDAO();
            ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("CART_LIST");
            int totalPrice = cartDao.totalCartPrice(cartList);
            request.setAttribute("TOTAL", totalPrice);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at CartController: " + e.toString());
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