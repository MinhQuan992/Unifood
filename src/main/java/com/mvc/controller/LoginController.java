package com.mvc.controller;

import com.mvc.bean.LoginBean;
import com.mvc.bean.UserBean;
import com.mvc.dao.LoginDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        LoginBean loginBean = new LoginBean();
        UserBean userBean = new UserBean();

        loginBean.setEmail(email);
        loginBean.setPassword(password);

        LoginDao loginDao = new LoginDao();
        boolean authorize = loginDao.authorizeLogin(loginBean, userBean);

        if (authorize)
        {
            request.setAttribute("userID", userBean.getUserID());
            request.setAttribute("fullName", userBean.getFullName());
            request.setAttribute("gender", userBean.getGender());
            request.setAttribute("birthDate", userBean.getBirthDate());
            request.setAttribute("address", userBean.getAddress());
            request.setAttribute("phone", userBean.getPhone());
            request.setAttribute("email", userBean.getEmail());
            request.setAttribute("loginFailed", false);
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
