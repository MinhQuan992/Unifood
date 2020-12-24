package com.mvc.controller;

import com.mvc.dao.GroupItemDao;
import com.mvc.dao.ItemDao;
import com.mvc.entities.ListItemEntity;
import com.mvc.entities.NhomsanphamEntity;
import com.mvc.entities.SanphamEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MainPageController")
public class MainPageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("This servlet: " + this.getServletName() + " is called");
        HttpSession session = request.getSession();
        List<ListItemEntity> ListItems = new ArrayList<ListItemEntity>();
        ListItemEntity listItemEntity = null;
        NhomsanphamEntity groupItem = null;

        ItemDao itemDao = new ItemDao();
        GroupItemDao groupItemDao = new GroupItemDao();

        for (short i=1; i<9; i++)
        {
            groupItem = groupItemDao.getGroupItemData(i);
            //System.out.println("Group Item was loaded completely: " + groupItem.getTenNhom());
            listItemEntity = new ListItemEntity(groupItem.getTenNhom(), null, groupItem);
            listItemEntity.setItemList(groupItemDao.getAllGroupItem(groupItem));
            ListItems.add(listItemEntity);
        }

        /*for (ListItemEntity list: ListItems)
        {
            System.out.println(list.getListItemName());
            for (SanphamEntity item: list.getItemList())
            {
                System.out.println(item.getMaSanPham()+":"+item.getTenSanPham());
            }
        }*/

        request.setAttribute("ListItems",ListItems);
        String url = "Khach.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request,response);
    }
}
