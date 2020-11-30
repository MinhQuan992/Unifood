package com.mvc.controller;

import com.mvc.bean.UserBean;
import com.mvc.dao.EditInfoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditInfoController")
public class EditInfoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");

        UserBean userBean = new UserBean();
        userBean.setUserID(userId);

        EditInfoDao editInfoDao = new EditInfoDao();
        boolean authorize = editInfoDao.authorizeEditInfo(userBean);

        if (authorize)
        {
            request.setAttribute("userID", userBean.getUserID());
            request.setAttribute("userName", userBean.getUsername());
            request.setAttribute("password", userBean.getPassword());
            request.setAttribute("fullName", userBean.getFullName());
            request.setAttribute("gender", userBean.getGender());
            request.setAttribute("birthDate", userBean.getBirthDate());
            request.setAttribute("address", userBean.getAddress());
            request.setAttribute("phone", userBean.getPhone());
            request.setAttribute("email", userBean.getEmail());
            request.setAttribute("authorize", true);
        }

        String url = "/editInfo.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: Check for error, update changes to database and show changes to user
    }
}
