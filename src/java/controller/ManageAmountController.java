/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDAO;
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
public class ManageAmountController extends HttpServlet {

    private static final String HOME = "HomeController";
    private static final String ERROR = "Login.jsp";
    private static final String SUCCESS = "CartController";
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
            String task = request.getParameter("task");
            Integer id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Cart> cartList = (ArrayList<Cart>)session.getAttribute("CART_LIST");
            ProductDAO dao = new ProductDAO();
            
            if (!task.isEmpty() && id != null) {
                if (task.equals("increase")) {
                    for (Cart c : cartList) {
                        if (c.getId() == id) {
                            if (c.getAmount() < dao.getQuantity(id)) {
                                int quantity = c.getAmount();
                                quantity++;
                                c.setAmount(quantity);
                                url = SUCCESS;

                            } else {
                                request.setAttribute("alert", "Số lượng sản phẩm trong kho đã tối đa, không thể đặt thêm!");
                                url = SUCCESS;

                            }
                        }
                    }
                }
                if (task.equals("decrease")) {
                    for (Cart c : cartList) {
                        if (c.getId() == id && c.getAmount() > 1) {
                            int quantity = c.getAmount();
                            quantity--;
                            c.setAmount(quantity);
                            break;
                        }
                    }
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at ManageAmountController: " + e.toString());
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
