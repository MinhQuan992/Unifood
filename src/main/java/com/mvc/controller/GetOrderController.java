package com.mvc.controller;

import com.mvc.dao.ViewOrderDao;
import com.mvc.entities.ViewDonHangEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetOrderController", urlPatterns = {"/orders"})
public class GetOrderController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = (String) request.getSession().getAttribute("userID");
        ViewOrderDao viewOrderDao = new ViewOrderDao();
        List<ViewDonHangEntity> orders = viewOrderDao.getAllOrder(userID);

        if (orders == null)
        {
            request.getSession().setAttribute("orders",null);
        }
        else
        {
            request.getSession().setAttribute("orders", orders);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/orders.jsp");
        dispatcher.forward(request, response);
    }
}
