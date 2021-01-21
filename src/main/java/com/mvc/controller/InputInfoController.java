package com.mvc.controller;

import com.mvc.dao.CartDao;
import com.mvc.dao.ItemDao;
import com.mvc.entities.DathangEntity;
import com.mvc.entities.GiohangEntity;
import com.mvc.entities.NguoidungEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "InputInfoController", urlPatterns = "/InputInfo")
public class InputInfoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        Pattern phonePattern = Pattern.compile("^(\\d){10}$");

        String user_name = request.getParameter("User_Name");
        String user_phone = request.getParameter("User_Phone");
        String user_address = request.getParameter("User_Address");

        Matcher phoneMatcher = phonePattern.matcher(user_phone);
        if (!phoneMatcher.matches()||false)
        {
            String url = "Page/InputInfo.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
            requestDispatcher.forward(request,response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("User_Name",user_name);
        session.setAttribute("User_Phone",user_phone);
        session.setAttribute("User_Address",user_address);

        GiohangEntity cart = (GiohangEntity) session.getAttribute("ShoppingCart");
        NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");
        String url = "index.jsp";
        CartDao cartDao = new CartDao();
        ItemDao itemDao = new ItemDao();
        //---------------------------------------------------------------------
        List<String> checkedList = (List<String>) session.getAttribute("CheckedItemList");
        List<DathangEntity> listOrder = cartDao.LoadCartData(cart.getMaGio());
        if (checkedList==null) checkedList = new ArrayList<String>();
        String confirm = request.getParameter("Cart-Confirm");

        GiohangEntity new_cart = cartDao.GetNewCart(user);
        for (DathangEntity order: listOrder)
        {
            if (!checkedList.contains(order.getMaSanPham()))
            {
                cartDao.InsertItemToCart(new_cart, order);
                cartDao.RemoveItemFromCart(cart, order);
            }
        }

        session.setAttribute("ShoppingCart",new_cart);
        checkedList.clear();
        session.setAttribute("CheckedItemList",checkedList);
        session.setAttribute("SelectAllItem",null);

        ServletContext context = this.getServletContext();
        //context.setAttribute("Test","OK!");
        System.out.println("This servlet is being forward you to: /Payment");
        RequestDispatcher dispatcher = context.getRequestDispatcher("/Payment");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
