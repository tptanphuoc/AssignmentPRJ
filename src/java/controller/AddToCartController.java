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
public class AddToCartController extends HttpServlet {

    private static final String HOME = "HomeController";
    private static final String ERROR = "Login.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
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
            ProductDAO dao = new ProductDAO();
            ArrayList<Cart> cartList = new ArrayList<>();
            int productId = Integer.parseInt(request.getParameter("product-id"));
            String name = request.getParameter("name");
            String image = request.getParameter("image");
            int price = Integer.parseInt(request.getParameter("price"));
            
            Cart cart = new Cart();
            cart.setId(productId);
            cart.setName(name);
            cart.setImage(image);
            cart.setPrice(price);
            cart.setAmount(1);
            ArrayList<Cart> currentCart = (ArrayList<Cart>)session.getAttribute("CART_LIST");
            
            if (currentCart == null) { //trường hợp giỏ chưa có sp nào
            if(dao.getQuantity(productId) > 0){ //số lượng sp trong SQL phải > 0
            cartList.add(cart);
            session.setAttribute("CART_LIST", cartList);
            request.setAttribute("alert", "Thêm vào giỏ thành công!");
            url = HOME;
            } else { //nếu số lượng <= 0
                request.setAttribute("alert", "Sản phẩm đã hết hàng!");
                url = HOME;
            }
            } else { //trường hợp giỏ đã có sp
                cartList = currentCart;
                boolean exist = false;
                
                for(Cart c : currentCart) {
                    if(c.getId() == productId) { //thêm vào giỏ sp đã có từ trước
                        exist = true;
                        
                        if (c.getAmount() < dao.getQuantity(productId)) { //từ lần thêm sau, sp trong giỏ cộng 1
                        int quantity = c.getAmount();
                        quantity++;
                        c.setAmount(quantity);
                        request.setAttribute("alert", "Thêm vào giỏ thành công!");
                    } else { //số lượng sp đặt mua vượt quá quantity trong sql
                        request.setAttribute("alert", "Số lượng sản phẩm trong kho đã tối đa, không thể đặt thêm!");
                    }                    
                    url = HOME;
                    }
                }              
                if (!exist) { //thêm 1 sp khác
                    
                if(dao.getQuantity(productId) > 0){ //số lượng sp trong SQL phải > 0
                cartList.add(cart);
                request.setAttribute("alert", "Thêm vào giỏ thành công!");
                } else{ //nếu số lượng <= 0
                    request.setAttribute("alert", "Sản phẩm đã hết hàng!");
                    }
                url = HOME;
            }
            }
        } catch (Exception e) {
            log("Error at AddToCartController: " + e.toString());
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
