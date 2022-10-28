/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OrderDAO;
import dao.UserDAO;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Order;

/**
 *
 * @author huynh
 */
public class SendMailController extends HttpServlet {

    private static final String HOME = "HomeController";
    private static final String ERROR = "Login.jsp";
    private static final String SEND_MAIL = "SendMail.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletConfig servletConfig = this.getServletConfig();
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
            
            String emailTo = request.getParameter("email");
            Pattern mailPattern = Pattern.compile(regex.RegularExpression.MAIL_PATTERN);
            if(!mailPattern.matcher(emailTo).find()) {
                request.setAttribute("alert", "Định dạng email không hợp lệ");
                url = SEND_MAIL;
                return;
            }
            final String senderMail = servletConfig.getInitParameter("SenderMail");//lấy biến môi trường đã set sẵn
            final String senderPassword = servletConfig.getInitParameter("SenderPassword");
            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.mime.allowutf8", true);
            Session sessionMail = Session.getInstance(prop, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderMail, senderPassword);                   
                }
            });
            
            try {
                UserDAO userDAO = new UserDAO();
                Account acc = userDAO.accountMailData(account.getUserID());
                OrderDAO orderDAO = new OrderDAO();
                Order order = orderDAO.accountMailData(account.getUserID());
                
                MimeMessage message = new MimeMessage(sessionMail);
                
                message.setFrom(new InternetAddress(senderMail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));               
                message.setSubject("Thông tin đơn hàng shop Kon Kưng", "UTF-8");
                message.setContent("<h3 style=\"color: blue\">Bạn đã thanh toán thành công.</h3>\n" +
                                "        <div>Tên người dùng: " + acc.getUserName()+ "</div>\n" +
                                "        <div>Số điện thoại: " + acc.getPhoneNum()+ "</div>\n" +
                                "        <div>Tổng đơn hàng: " + order.getTotal()+ "đ</div>\n" +
                                "        <div>Ngày đặt hàng: " + order.getDate()+ "</div>\n" +
                                "        <h3 style=\"color: #c01508\">Cảm ơn bạn đã ủng hộ!</h3>"
                                , "text/html; charset=UTF-8");
                Transport.send(message);
                request.setAttribute("alert", "Gửi thành công, vui lòng truy cập mail để xem chi tiết");
                url = HOME;
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            
            } catch (Exception e) {
            log("Error at SendMailController: " + e.toString());
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
