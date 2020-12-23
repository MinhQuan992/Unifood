package com.mvc.controller;

import com.mvc.dao.ManageWarehouseDao;
import com.mvc.dao.PaymentDao;
import com.mvc.dao.UserDao;
import com.mvc.entities.DathangEntity;
import com.mvc.entities.NguoidungEntity;
import com.mvc.entities.SanphamEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "PaymentController")
public class PaymentController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int maGio = Integer.parseInt(request.getParameter("MaGio"));
        String userId = request.getParameter("UserId");

        List<DathangEntity> listDH = PaymentDao.GetDatHang(maGio);
        List<SanphamEntity> listSP = new ArrayList<>();
        int cost = 0;
        for (DathangEntity d: listDH) {
            SanphamEntity sp = ManageWarehouseDao.GetItem(d.getMaSanPham());
            listSP.add(sp);
            cost += sp.getDonGia() * d.getSoLuong();
        }

        NguoidungEntity user = (new UserDao()).getUserByID(userId);

        request.setAttribute("listSP", listSP);
        request.setAttribute("cost", cost);
        request.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
