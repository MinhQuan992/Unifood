package com.mvc.controller;

import com.mvc.dao.CartDao;
import com.mvc.dao.ItemDao;
import com.mvc.dao.OrderDao;
import com.mvc.dao.UserDao;
import com.mvc.entities.DathangEntity;
import com.mvc.entities.GiohangEntity;
import com.mvc.entities.NguoidungEntity;
import com.mvc.entities.SanphamEntity;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AjaxTestServlet", urlPatterns = {"/AjaxAPI"})
public class AjaxTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("This servlet: " + this.getServletName() + " is called");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        /*int n1 = Integer.parseInt(request.getParameter("n1"));
        int n2 = Integer.parseInt(request.getParameter("n2"));

        out.println(n1 + n2 + "");*/
        out.println("Success");

        HttpSession session = request.getSession();

        NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");
        GiohangEntity cart = (GiohangEntity) session.getAttribute("ShoppingCart");
        if (user==null)
        {
            UserDao userDao = new UserDao();
            user = userDao.getUserByID("KH0000000");
            CartDao cartDao = new CartDao();
            cart = cartDao.GetNewCart(user);
            session.setAttribute("User",user);
            session.setAttribute("ShoppingCart",cart);
        }

        String FuncRequest = request.getParameter("ParaName");
        String ItemCode = request.getParameter("KeyValue");
        ///System.out.println("This servlet was called with: KeyValue = " + ItemCode + " and ParaName = " + FuncRequest);

        CartDao cartDao = new CartDao();
        ItemDao itemDao = new ItemDao();

        //---------------------------------------------------------------------
        List<String> checkedList = (List<String>) session.getAttribute("CheckedItemList");
        List<DathangEntity> listOrder = cartDao.LoadCartData(cart.getMaGio());
        if (FuncRequest!=null)
            switch (FuncRequest) {
                case "AddItem":
                    cartDao.AddItemToCart(cart, itemDao.GetItemData(ItemCode));
                    break;
                case "SubItem":
                    cartDao.SubItemFromCart(cart, itemDao.GetItemData(ItemCode));
                    break;
                case "DeleteItem":
                    cartDao.RemoveItemFromCart(cart, itemDao.GetItemData(ItemCode));
                    if (checkedList.contains(ItemCode))
                        checkedList.remove(ItemCode);
                    break;
                case "CheckedItem":
                    if (checkedList.contains(ItemCode))
                        checkedList.remove(ItemCode);
                    else
                        checkedList.add(ItemCode);
                    break;
                case "SelectAll":
                    if (ItemCode.equals("On") && session.getAttribute("SelectAllItem") == null) {
                        for (DathangEntity order : listOrder) {
                            if (!checkedList.contains(order.getMaSanPham()))
                                checkedList.add(order.getMaSanPham());
                        }
                        session.setAttribute("SelectAllItem", ItemCode);
                    } else if (session.getAttribute("SelectAllItem") != null) {
                        checkedList.clear();
                        session.setAttribute("SelectAllItem", null);
                    }
                    break;
                case "TakeNote":
                    String note = request.getParameter("Extend");
                    if (note != null) {
                        OrderDao orderDao = new OrderDao();
                        DathangEntity order = orderDao.GetOrderData(cart.getMaGio(), ItemCode);
                        order.setGhiChu(note);
                        orderDao.UpdateOrderData(order);
                    }
                    break;
            }

    }
}
