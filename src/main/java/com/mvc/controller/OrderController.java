package com.mvc.controller;

import com.mvc.dao.*;
import com.mvc.entities.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderController")
public class OrderController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mainItemCode = request.getParameter("MainItemCode");
        System.out.println(mainItemCode);

        ItemDao itemDao = new ItemDao();
        SanphamEntity item = itemDao.GetItemData(mainItemCode);
        DependDao denpendDao = new DependDao();
        List<AnkemEntity> list = denpendDao.getDependItemData(item.getMaSanPham());
        CartDao cartDao = new CartDao();
        HttpSession session = request.getSession();
        GiohangEntity cart = (GiohangEntity) session.getAttribute("ShoppingCart");
        NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");

        if (cart==null) cart = cartDao.GetNewCart(user);
        System.out.println(cart.getMaNguoiDung() + ":" + cart.getMaGio());

        String note = request.getParameter("GhiChuChoNhanVien");
        int quantity = Integer.parseInt(request.getParameter(item.getMaSanPham()));

        // check if paycheck -- > send all cur product to new cart
        String payCheck = request.getParameter("PayCheck");
        if (payCheck!=null)
        {
            GiohangEntity cart_new = cartDao.GetNewCart(user);
            List<DathangEntity> listOrder = cartDao.LoadCartData(cart.getMaGio());
            OrderDao orderDao = new OrderDao();
            for (DathangEntity order: listOrder)
            {
                orderDao.DeleteOrderData(order);
                order.setMaGio(cart_new.getMaGio());
                orderDao.InsertOrderData(order);
            }
            session.setAttribute("MaGio",cart.getMaGio());
            session.setAttribute("ShoppingCart",cart_new);
        }

        cartDao.InsertItemToCart(cart,item,quantity,note);
        for (AnkemEntity depend: list)
        {
            String checked = request.getParameter("Check"+depend.getMaDoAnKem());
            //System.out.println(checked);
            if (checked!=null && checked.equals("on"))
            {
                SanphamEntity depend_item = itemDao.GetItemData(depend.getMaDoAnKem());
                quantity = Integer.parseInt(request.getParameter(depend_item.getMaSanPham()));
                cartDao.InsertItemToCart(cart,depend_item,quantity,"");
            }
        }

        String url = "index.jsp";

        // send user to payment
        if (payCheck!=null)
        {
            if (user.getMaNguoiDung().equals("KH0000000"))
            {
                String user_name = (String) session.getAttribute("User_Name");
                String user_phone = (String) session.getAttribute("User_Name");
                String user_address = (String) session.getAttribute("User_Name");

                if (user_name == null || user_phone == null || user_address == null)
                {
                    url = "Page/InputInfo.jsp";
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
                    requestDispatcher.forward(request,response);
                    return;
                }
            }
            url = "/Payment";
            ServletContext servletContext = this.getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(url);
            System.out.println("This servlet is being forward you to: " + url);
            requestDispatcher.forward(request,response);
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mainItemCode = request.getParameter("MainItemCode");
        System.out.println(mainItemCode);

        ItemDao itemDao = new ItemDao();
        SanphamEntity item = itemDao.GetItemData(mainItemCode);
        DependDao denpendDao = new DependDao();
        List<AnkemEntity> list = denpendDao.getDependItemData(item.getMaSanPham());
        CartDao cartDao = new CartDao();
        HttpSession session = request.getSession();
        GiohangEntity cart = (GiohangEntity) session.getAttribute("ShoppingCart");
        NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");

        if (cart==null) cart = cartDao.GetNewCart(user);
        System.out.println(cart.getMaNguoiDung() + ":" + cart.getMaGio());

        String note = request.getParameter("GhiChuChoNhanVien");
        int quantity = Integer.parseInt(request.getParameter(item.getMaSanPham()));

        // check if paycheck -- > send all cur product to new cart
        String payCheck = request.getParameter("PayCheck");
        if (payCheck!=null)
        {
            GiohangEntity cart_new = cartDao.GetNewCart(user);
            List<DathangEntity> listOrder = cartDao.LoadCartData(cart.getMaGio());
            OrderDao orderDao = new OrderDao();
            for (DathangEntity order: listOrder)
            {
                order.setMaGio(cart_new.getMaGio());
                orderDao.UpdateOrderData(order);
            }
        }

        cartDao.InsertItemToCart(cart,item,quantity,note);
        for (AnkemEntity depend: list)
        {
            String checked = request.getParameter("Check"+depend.getMaDoAnKem());
            //System.out.println(checked);
            if (checked!=null && checked.equals("on"))
            {
                SanphamEntity depend_item = itemDao.GetItemData(depend.getMaDoAnKem());
                quantity = Integer.parseInt(request.getParameter(depend_item.getMaSanPham()));
                cartDao.InsertItemToCart(cart,depend_item,quantity,"");
            }
        }

        String url = "index.jsp";

        // send user to payment
        if (payCheck!=null)
        {
            url = "/Payment";
            ServletContext servletContext = this.getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(url);
            requestDispatcher.forward(request,response);
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request,response);
    }
}
