package com.mvc.controller;

import com.mvc.dao.CartDao;
import com.mvc.dao.DependDao;
import com.mvc.dao.GroupItemDao;
import com.mvc.dao.ItemDao;
import com.mvc.entities.*;

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
        if (cart==null) cart = cartDao.GetNewCart((NguoidungEntity) session.getAttribute("User"));
        System.out.println(cart.getMaNguoiDung() + ":" + cart.getMaGio());
        String note = request.getParameter("GhiChuChoNhanVien");
        int quantity = Integer.parseInt(request.getParameter(item.getMaSanPham()));
        cartDao.InsertItemToCart(cart,item,quantity,note);
        for (AnkemEntity depend: list)
        {
            String checked = request.getParameter("Check"+depend.getMaDoAnKem());
            System.out.println(checked);
            if (checked!=null && checked.equals("on"))
            {
                SanphamEntity depend_item = itemDao.GetItemData(depend.getMaDoAnKem());
                quantity = Integer.parseInt(request.getParameter(depend_item.getMaSanPham()));
                cartDao.InsertItemToCart(cart,depend_item,quantity,"");
            }
        }
    }
}
