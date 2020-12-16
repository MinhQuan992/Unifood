package com.mvc.controller;

import com.mvc.dao.EditInfoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.mvc.dao.ManageWarehouseDao;
import com.mvc.entities.KhohangEntity;
import com.mvc.entities.SanphamEntity;

@WebServlet(name = "ManageWarehouseController")
public class ManageWarehouseController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map< KhohangEntity, List<SanphamEntity> > map = new HashMap<>();
        List< KhohangEntity > listKho = ManageWarehouseDao.GetWarehouse();
        if (listKho != null) {
            for (KhohangEntity k: listKho) {
                map.put(k, ManageWarehouseDao.GetItemsInWarehouse(k));
            }
        }

        request.setAttribute("map", map);
        request.setAttribute("authorize", true);

        String url = "/manageWarehouse.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String type = request.getParameter("Type");
        String maKho = request.getParameter("MaKho");
        String maSanPham = request.getParameter("MaSanPham");



        response.getWriter().append(String.format("Type: %s\nMaKho: %s\nMaSanPham: %s", type, maKho, maSanPham));
        //String url = "/manageWarehouse.jsp";
        //RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        //dispatcher.forward(request, response);
    }
}
