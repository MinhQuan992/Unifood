package com.mvc.controller;

import com.mvc.dao.UserDao;
import com.mvc.entities.NguoidungEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "ChangePasswordController", urlPatterns = {"/changePassword"})
public class ChangePasswordController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String url = "";
        boolean hasError = false;

        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{10,50}$");
        Matcher passwordMatcher = passwordPattern.matcher(newPassword);
        boolean rightPassword =  passwordMatcher.matches();
        if (!rightPassword)
        {
            hasError = true;
            request.setAttribute("newPasswordError",true);
        }
        else
        {
            request.setAttribute("newPasswordError", null);
        }

        if (!confirmPassword.equals(newPassword))
        {
            hasError = true;
            request.setAttribute("confirmPasswordError","Mật khẩu không trùng khớp");
        }
        else
        {
            request.setAttribute("confirmPasswordError",null);
        }

        if (hasError)
        {
            request.setAttribute("changePasswordFailed", true);
            url = "/change-password.jsp";
        }
        else
        {
            String email = (String) request.getSession().getAttribute("userEmail");
            UserDao userDao = new UserDao();
            NguoidungEntity user = userDao.getUserByEmail(email);
            NguoidungEntity userWithNewPassword = new NguoidungEntity(user.getMaNguoiDung(), user.getHoVaTen(), user.getGioiTinh(), user.getNgaySinh(), user.getDiaChi(), user.getDienThoai(), user.getEmail(), newPassword);

            boolean canExecute = userDao.updateUser(userWithNewPassword);

            if (canExecute)
            {
                request.setAttribute("changePasswordFailed", false);
                url = "/signin.jsp";
            }
            else
            {
                request.setAttribute("changePasswordFailed", true);
                url = "/change-password.jsp";
            }
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
