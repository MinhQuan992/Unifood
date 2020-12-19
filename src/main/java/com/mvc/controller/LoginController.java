package com.mvc.controller;

import com.mvc.dao.CartDao;
import com.mvc.dao.UserDao;
import com.mvc.entities.GiohangEntity;
import com.mvc.entities.NguoidungEntity;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session  =request.getSession();

        UserDao userDao = new UserDao();
        NguoidungEntity user = userDao.getUserByEmail(email);
        String url;

        if (user != null)
        {
            String dbPassword = user.getMatKhau();
            if (password.equals(dbPassword))
            {
                session.setAttribute("User",user);
                CartDao cartDao = new CartDao();
                GiohangEntity cart = cartDao.GetCartData(user);
                if (cart==null) cart=cartDao.GetNewCart(user);
                session.setAttribute("ShoppingCart",cart);
                request.setAttribute("userID", user.getMaNguoiDung());
                request.setAttribute("fullName", user.getHoVaTen());
                request.setAttribute("gender", user.getGioiTinh());
                request.setAttribute("birthdate", user.getNgaySinh());
                request.setAttribute("address", user.getDiaChi());
                request.setAttribute("phone", user.getDienThoai());
                request.setAttribute("email", user.getEmail());
                request.setAttribute("loginFailed", false);
                url = "/index.jsp";
            }
            else
            {
                request.setAttribute("loginFailed", true);
                url = "/login.jsp";
            }
        }
        else
        {
            request.setAttribute("loginFailed", true);
            url = "/login.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
