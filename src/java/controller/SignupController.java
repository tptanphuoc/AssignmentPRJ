/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import recaptcha.VerifyRecaptcha;

/**
 *
 * @author huynh
 */
public class SignupController extends HttpServlet {

    private static final String ERROR = "Login.jsp";
    private static final String SUCCESS = "Login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserDAO dao = new UserDAO();
        try {
            String userName = request.getParameter("userName");
            String userPhone = request.getParameter("phone");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("repassword");
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean valid = false;
            valid = VerifyRecaptcha.verify(gRecaptchaResponse);
            
            Account account = dao.checkAccount(userName);
            
            if(userName.isEmpty() || userPhone.isEmpty() || password.isEmpty() || rePassword.isEmpty()){
            request.setAttribute("ERROR_SIGNUP", "Please complete all information!");
            } else if(valid == false){
                request.setAttribute("ERROR_SIGNUP", "Please complete the reCAPTCHA");
            }
            else if(password.equals(rePassword) && account == null && valid == true){
                dao.signup(userName, userPhone, password);
                request.setAttribute("MESSAGE", "Register successful!");
                url = SUCCESS;
            }
            
            if(password.compareTo(rePassword)!= 0){
            request.setAttribute("ERROR_SIGNUP", "The entered passwords do not match. Try again!"); 
            }
            
            if(valid == true && account != null){
            request.setAttribute("ERROR_SIGNUP", "Account already existed. Try another username!"); 
        }
            
        } catch (Exception e) {
            log("Error at SigupController: " + e.toString());
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
