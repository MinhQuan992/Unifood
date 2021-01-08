package com.mvc.controller;

import com.mvc.dao.CartDao;
import com.mvc.dao.UserDao;
import com.mvc.entities.GiohangEntity;
import com.mvc.entities.NguoidungEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SigninController", urlPatterns = {"/signin", "/index"})
public class SigninController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        NguoidungEntity user = userDao.getUserByEmail(email);
        String url;

        if (user != null)
        {
            String dbPassword = user.getMatKhau();

            if (password.equals(dbPassword))
            {
                String userID = user.getMaNguoiDung();

                if (userID.startsWith("KH"))
                {
                    request.getSession().setAttribute("userType", "Customer");
                    url="/index.jsp";
                }
                else
                {
                    request.getSession().setAttribute("userType", "Manager");
                    url="qlhome.jsp";
                }
                request.getSession().setAttribute("User",user);
                CartDao cartDao = new CartDao();
                GiohangEntity cart = cartDao.GetCartData(user);
                if (cart==null) cart=cartDao.GetNewCart(user);

                request.getSession().setAttribute("ShoppingCart",cart);
                request.getSession().setAttribute("userID", userID);
                request.getSession().setAttribute("fullName", user.getHoVaTen());
                request.getSession().setAttribute("gender", user.getGioiTinh());
                request.getSession().setAttribute("birthdate", user.getNgaySinh());
                request.getSession().setAttribute("address", user.getDiaChi());
                request.getSession().setAttribute("phone", user.getDienThoai());
                request.getSession().setAttribute("email", user.getEmail());
                request.getSession().setAttribute("signinSuccess", true);
            }
            else
            {
                request.setAttribute("signinFailed", true);
                url = "/signin.jsp";
            }
        }
        else
        {
            request.setAttribute("signinFailed", true);
            url = "/signin.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}