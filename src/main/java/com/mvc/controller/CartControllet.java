package com.mvc.controller;

import com.mvc.dao.CartDao;
import com.mvc.dao.ItemDao;
import com.mvc.entities.DathangEntity;
import com.mvc.entities.GiohangEntity;
import com.mvc.entities.NguoidungEntity;
import com.mvc.entities.SanphamEntity;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
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

        //---------------------------------------------------------------------
        HttpSession session1 = request.getSession();
        List<String> checkedList = (List<String>) session1.getAttribute("CheckedItemList");
        if (checkedList==null) checkedList = new ArrayList<String>();
        String AddItemCode = request.getParameter("AddItem");
        if (AddItemCode != null)
        {
            cartDao.AddItemToCart(cart,itemDao.GetItemData(AddItemCode));
        }
        String SubItemCode = request.getParameter("SubItem");
        if (SubItemCode != null)
        {
            cartDao.SubItemFromCart(cart,itemDao.GetItemData(SubItemCode));
        }
        String DeleteItemCode = request.getParameter("DeleteItem");
        if (DeleteItemCode != null)
        {
            cartDao.RemoveItemFromCart(cart,itemDao.GetItemData(DeleteItemCode));
            if (checkedList.contains(DeleteItemCode))
                checkedList.remove(DeleteItemCode);
        }
        String CheckedItemCode = request.getParameter("CheckedItem");
        if (CheckedItemCode != null) {
            if (checkedList.contains(CheckedItemCode))
                checkedList.remove(CheckedItemCode);
            else
                checkedList.add(CheckedItemCode);
        }
        String SelectAll = request.getParameter("SelectAll");
        //---------------------------------------------------------------------
        List<DathangEntity> listOrder = cartDao.LoadCartData(cart.getMaGio());
        Map<String,SanphamEntity> map = new HashMap<String,SanphamEntity>();
        Map<String,Boolean> checkmap = new HashMap<String,Boolean>();
        int totalCost = 0, quantityNumber = 0, shippingFee = 0;
        if (SelectAll!=null && SelectAll.equals("On") && session.getAttribute("SelectAll")==null)
        for (DathangEntity order: listOrder)
        {
            if (!checkedList.contains(order.getMaSanPham()))
                checkedList.add(order.getMaSanPham());
        }
        if (SelectAll!=null && session.getAttribute("SelectAllItem")!=null)
        {
            checkedList.clear();
            SelectAll = null;
        }
        SelectAll = "On";
        for (DathangEntity order:listOrder)
        {
            SanphamEntity item = itemDao.GetItemData(order.getMaSanPham());
            map.put(item.getMaSanPham(),item);
            checkmap.putIfAbsent(item.getMaSanPham(), false);
            if (checkedList.contains(order.getMaSanPham())) {
                totalCost += item.getDonGia() * order.getSoLuong();
                quantityNumber += order.getSoLuong();
            }
            else
                SelectAll = null;
        }
        request.setAttribute("OrderList",listOrder);
        request.setAttribute("ItemMap",map);
        request.setAttribute("CheckMap",checkmap);
        request.setAttribute("TotalCost",totalCost);
        request.setAttribute("QuantityNumber",quantityNumber);
        if (quantityNumber>0) shippingFee = 19000;
        request.setAttribute("ShippingFee",shippingFee);
        request.setAttribute("CheckedItemList",checkedList);
        request.setAttribute("SelectAllItem",SelectAll);

        session.setAttribute("CheckedItemList",checkedList);
        session.setAttribute("SelectAllItem",SelectAll);

        String url = "/Page/Cart.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request,response);
    }
}
