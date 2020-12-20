package com.mvc.controller;

import com.mvc.dao.CartDao;
import com.mvc.dao.ItemDao;
import com.mvc.entities.DathangEntity;
import com.mvc.entities.GiohangEntity;
import com.mvc.entities.NguoidungEntity;
import com.mvc.entities.SanphamEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CartControllet")
public class CartControllet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");
        GiohangEntity cart = (GiohangEntity) session.getAttribute("ShoppingCart");

        CartDao cartDao = new CartDao();
        ItemDao itemDao = new ItemDao();

        List<DathangEntity> listOrder = cartDao.LoadCartData(cart.getMaGio());
        Map<String,SanphamEntity> map = new HashMap<String,SanphamEntity>();
        for (DathangEntity order:listOrder)
        {
            SanphamEntity item = itemDao.GetItemData(order.getMaSanPham());
            map.put(item.getMaSanPham(),item);
        }

        request.setAttribute("OrderList",listOrder);
        request.setAttribute("ItemMap",map);

        String url = "/Page/Cart.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request,response);
    }
}
