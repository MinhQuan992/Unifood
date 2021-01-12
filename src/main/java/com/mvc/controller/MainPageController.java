package com.mvc.controller;

import com.mvc.dao.GroupItemDao;
import com.mvc.entities.ListItemEntity;
import com.mvc.entities.NhomsanphamEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MainPageController")
public class MainPageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("This servlet: " + this.getServletName() + " is called");
        List<ListItemEntity> ListItems = new ArrayList<>();
        ListItemEntity listItemEntity;
        NhomsanphamEntity groupItem;
        GroupItemDao groupItemDao = new GroupItemDao();

        for (short i=1; i<4; i++)
        {
            groupItem = groupItemDao.getGroupItemData(i);
            listItemEntity = new ListItemEntity(groupItem.getTenNhom(), null, groupItem);
            listItemEntity.setItemList(groupItemDao.getAllGroupItem(groupItem));
            ListItems.add(listItemEntity);
        }

        request.setAttribute("ListItems",ListItems);
        String url = "/Khach.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request,response);
    }
}