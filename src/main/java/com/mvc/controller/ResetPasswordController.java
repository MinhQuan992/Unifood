package com.mvc.controller;

import com.mvc.services.CustomerServices;
import com.mvc.utility.EmailUtility;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ResetPasswordController", urlPatterns = {"/ResetPassword"})
public class ResetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String host;
    private String port;
    private String socketFactoryClass;
    private String auth;
    private String email;
    private String name;
    private String pass;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        socketFactoryClass = context.getInitParameter("socketFactoryClass");
        auth = context.getInitParameter("auth");
        email = context.getInitParameter("email");
        name = context.getInitParameter("name");
        pass = context.getInitParameter("pass");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipient = request.getParameter("reset-email");
        String subject = "Mật khẩu của bạn đã được đặt lại";

        CustomerServices customerServices = new CustomerServices();
        String newPassword = customerServices.resetCustomerPassword(recipient);

        String content = "Xin chào, đây là mật khẩu mới của bạn đã được hệ thống tạo ra ngẫu nhiên: " + newPassword;
        content += "\nChú ý: vì lí do bảo mật, bạn phải đổi mật khẩu ngay sau khi đăng nhập.";
        content += "\nĐội ngũ hỗ trợ UNIFOOD";

        String message = "";

        try
        {
            EmailUtility.sendEmail(host, port, socketFactoryClass, auth, email, name, pass,
                    recipient, subject, content);
            message = "Mật khẩu của bạn đã thay đổi, hãy kiểm tra email của bạn!";
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            message = "Có lỗi xảy ra: " + ex.getMessage();
        }
        finally
        {
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }

        protected void doGet (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            String url = "/reset-password.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
}
