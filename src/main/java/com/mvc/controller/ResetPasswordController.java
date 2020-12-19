package com.mvc.controller;

import com.mvc.dao.UserDao;
import com.mvc.entities.NguoidungEntity;
import com.mvc.utility.EmailUtility;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ResetPasswordController", urlPatterns = {"/resetPassword"})
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
        UserDao userDao = new UserDao();
        NguoidungEntity user = userDao.getUserByEmail(recipient);

        if (user != null)
        {
            String randomPassword = RandomStringUtils.randomAlphanumeric(8);
            user.setMatKhau(randomPassword);
            String message = "";
            boolean canExecute = userDao.updateUser(user);
            if (canExecute)
            {
                String subject = "Mat khau cua ban da duoc dat lai";
                String content = "Xin chao, day la mat khau moi cua ban da duoc he thong tao ra ngau nhien: " + randomPassword;
                content += "\nChu y: vi li do bao mat, ban phai doi mat khau ngay sau khi dang nhap.";
                content += "\nDoi ngu ho tro UNIFOOD";

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
            }
            else
            {
                message = "Có lỗi xảy ra";
            }
            request.setAttribute("wrongEmail",false);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
        else
        {
            request.setAttribute("wrongEmail",true);
            request.getRequestDispatcher("/reset-password.jsp").forward(request, response);
        }
    }

        protected void doGet (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            String url = "/reset-password.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }
}
