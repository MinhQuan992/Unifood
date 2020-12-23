package com.mvc.controller;

import com.mvc.entities.NguoidungEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "OrderManageController")
public class OrderManageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");
        if (!user.getMaNguoiDung().startsWith("QL"))
        {
            request.setAttribute("AcessAlertMessage","You are not allowed to access this page");
            request.getRequestDispatcher("Index.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
