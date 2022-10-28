/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author huynh
 */
public class DeleteProductController extends HttpServlet {

    private static final String ERROR = "Login.jsp";
    private static final String SUCCESS = "ProductController";
    private static final String HOME = "HomeController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME;
        try {
            int productId= Integer.parseInt(request.getParameter("product-id"));
            ProductDAO dao = new ProductDAO();
            HttpSession session = request.getSession();
            Account account = (Account)session.getAttribute("LOGIN_USER");
            if(account == null) {
                request.setAttribute("alert", "Bạn chưa login");
                url = ERROR;
                return;
            }
            if(account.getIsAdmin() != 1) {
                request.setAttribute("alert", "Bạn không có quyền truy cập vào trang này");
                url = HOME;
                return;
            }
            boolean checkDelete = dao.deleteProductByID(productId);
            if(checkDelete) {
                url = SUCCESS;
            } else {
                request.setAttribute("alert", "Có lỗi khi xóa sản phẩm");
                url = HOME;
            }
        } catch (Exception e) {
            log("Error at DeleteProductController: "+ e.toString());
        }finally{
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
