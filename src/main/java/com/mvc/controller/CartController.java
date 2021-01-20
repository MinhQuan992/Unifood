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
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CartController")
public class CartController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        String url = "index.jsp";
        CartDao cartDao = new CartDao();
        ItemDao itemDao = new ItemDao();

        //---------------------------------------------------------------------
        List<String> checkedList = (List<String>) session.getAttribute("CheckedItemList");
        List<DathangEntity> listOrder = cartDao.LoadCartData(cart.getMaGio());
        if (checkedList==null) checkedList = new ArrayList<String>();
        String confirm = request.getParameter("Cart-Confirm");

        if (confirm!=null && !checkedList.isEmpty())
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
        else
        {
            String FuncRequest = request.getParameter("ParaName");
            String ItemCode = request.getParameter("KeyValue");
            ///System.out.println("This servlet was called with: KeyValue = " + ItemCode + " and ParaName = " + FuncRequest);
            if (FuncRequest!=null)
            switch (FuncRequest)
            {
                case "AddItem":
                    cartDao.AddItemToCart(cart,itemDao.GetItemData(ItemCode));
                    break;
                case "SubItem":
                    cartDao.SubItemFromCart(cart,itemDao.GetItemData(ItemCode));
                    break;
                case "DeleteItem":
                    cartDao.RemoveItemFromCart(cart,itemDao.GetItemData(ItemCode));
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
                    if (ItemCode.equals("On") && session.getAttribute("SelectAllItem")==null)
                    {
                        for (DathangEntity order: listOrder)
                        {
                            if (!checkedList.contains(order.getMaSanPham()))
                                checkedList.add(order.getMaSanPham());
                        }
                        session.setAttribute("SelectAllItem",ItemCode);
                    }
                    else if (session.getAttribute("SelectAllItem")!=null)
                    {
                        checkedList.clear();
                        session.setAttribute("SelectAllItem",null);
                    }
                    break;
                case "TakeNote":
                    String note = request.getParameter("Extend");
                    if (note!=null)
                    {
                        OrderDao orderDao = new OrderDao();
                        DathangEntity order = orderDao.GetOrderData(cart.getMaGio(),ItemCode);
                        order.setGhiChu(note);
                        orderDao.UpdateOrderData(order);
                    }
                    break;
            }
            listOrder = cartDao.LoadCartData(cart.getMaGio());
            Map<String,SanphamEntity> map = new HashMap<String,SanphamEntity>();
            Map<String,Boolean> checkmap = new HashMap<String,Boolean>();
            int totalCost = 0, quantityNumber = 0, shippingFee = 0;
            String SelectAll = "On";
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
            url = "/Page/Cart.jsp";
            System.out.println("This servlet is being forward you to: " + url);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");
        GiohangEntity cart = (GiohangEntity) session.getAttribute("ShoppingCart");
        String url = "index.jsp";
        CartDao cartDao = new CartDao();
        ItemDao itemDao = new ItemDao();

        //---------------------------------------------------------------------
        List<String> checkedList = (List<String>) session.getAttribute("CheckedItemList");
        List<DathangEntity> listOrder = cartDao.LoadCartData(cart.getMaGio());
        if (checkedList==null) checkedList = new ArrayList<String>();
        String confirm = request.getParameter("Cart-Confirm");


        if (confirm!=null && !checkedList.isEmpty())
        {
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
            url = "/Payment?Cart=" + cart.getMaGio();
            System.out.println("This servlet is being forward you to: " + url);
        }
        else
        {
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
            url = "/Page/Cart.jsp";
            System.out.println("This servlet is being forward you to: " + url);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request,response);
    }
}
