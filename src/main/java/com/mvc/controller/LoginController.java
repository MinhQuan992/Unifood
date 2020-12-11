package com.mvc.controller;

import com.mvc.dao.UserDao;
import com.mvc.entities.NguoidungEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        NguoidungEntity user = userDao.getUserByEmail(email);

        if (user != null)
        {
            String dbPassword = user.getMatKhau();
            if (password.equals(dbPassword))
            {
                request.setAttribute("userID", user.getMaNguoiDung());
                request.setAttribute("fullName", user.getHoVaTen());
                request.setAttribute("gender", user.getGioiTinh());
                request.setAttribute("birthdate", user.getNgaySinh());
                request.setAttribute("address", user.getDiaChi());
                request.setAttribute("phone", user.getDienThoai());
                request.setAttribute("email", user.getEmail());
                request.setAttribute("loginFailed", false);
            }
            else
            {
                request.setAttribute("loginFailed", true);
            }
        }
        else
        {
            request.setAttribute("loginFailed", true);
        }

        String url = "/index.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
