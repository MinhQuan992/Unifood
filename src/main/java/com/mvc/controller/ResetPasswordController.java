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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String recipient = request.getParameter("reset-email").trim();
        UserDao userDao = new UserDao();
        NguoidungEntity user = userDao.getUserByEmail(recipient);
        String url = "";

        if (user != null)
        {
            Pattern codePattern = Pattern.compile("\\d{6}");
            Matcher codeMatcher;
            String verificationCode;
            do {
                verificationCode = RandomStringUtils.randomAlphanumeric(6);
                codeMatcher = codePattern.matcher(verificationCode);
            } while (!codeMatcher.matches());

            String subject = "Yeu cau thay doi mat khau";
            String content = "Xin chao ban, UNIFOOD da nhan duoc yeu cau thay doi mat khau cua ban.";
            content += "\nBan hay nhap ma xac minh nay vao trang web va click XAC NHAN: " + verificationCode;
            content += "\nNeu ban khong yeu cau thay doi mat khau, hay bo qua email nay.";
            content += "\nTran trong.";
            content += "\nDoi ngu ho tro UNIFOOD";

            try
            {
                EmailUtility.sendEmail(host, port, socketFactoryClass, auth, email, name, pass,
                        recipient, subject, content);
                request.getSession().setAttribute("verificationCode", verificationCode);
                request.getSession().setAttribute("userEmail", recipient);
                url = "/verification.jsp";
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                request.setAttribute("sendEmailFailed", false);
                url = "/reset-password.jsp";
            }
        }
        else
        {
            request.setAttribute("wrongEmail",true);
            url = "/reset-password.jsp";
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
