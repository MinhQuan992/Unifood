package com.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.mvc.bean.GroupItemBean;
import com.mvc.bean.ItemBean;
import com.mvc.bean.ListItemBean;
import com.mvc.dao.GroupItemDao;
import com.mvc.dao.ItemDao;

@WebServlet(name = "ItemController")
public class ItemController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("This servlet: " + getServletName() + " is called");
        HttpSession session = request.getSession();
        String itemCode = request.getParameter("ItemCode");
        System.out.println(itemCode);

        ItemDao itemDao = new ItemDao();
        GroupItemDao groupDao = new GroupItemDao();
        ItemBean itemBean = new ItemBean(itemCode);

        GroupItemBean groupItem = new GroupItemBean(10);
        itemDao.GetItemData(itemBean);
        groupDao.getGroupItemData(groupItem);
        ListItemBean listItem1 = new ListItemBean(groupItem.getGroupName(),itemBean, groupItem);
        itemDao.getListDependenceItems(itemBean,groupItem,listItem1);

        groupItem = new GroupItemBean(11);
        groupDao.getGroupItemData(groupItem);
        ListItemBean listItem2 = new ListItemBean(groupItem.getGroupName(),itemBean, groupItem);
        itemDao.getListDependenceItems(itemBean,groupItem,listItem2);

        groupItem = new GroupItemBean(12);
        groupDao.getGroupItemData(groupItem);
        ListItemBean listItem3 = new ListItemBean(groupItem.getGroupName(),itemBean, groupItem);
        itemDao.getListDependenceItems(itemBean,groupItem,listItem3);

        request.setAttribute("Item",itemBean);
        request.setAttribute("ListDependenceItems1",listItem1);
        request.setAttribute("ListDependenceItems2",listItem2);
        request.setAttribute("ListDependenceItems3",listItem3);
        String url = "/Page/Test.jsp";
        System.out.println("Serlet forward to : " + url);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
