package com.mvc.controller;

import com.mvc.dao.ManageWarehouseDao;
import com.mvc.dao.PaymentDao;
import com.mvc.dao.UserDao;
import com.mvc.entities.*;

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

        List<DonvigiaohangEntity> listDV = PaymentDao.GetDVGiaoHang();
        List<DathangEntity> listDH = PaymentDao.GetDatHang(maGio);
        List<SanphamEntity> listSP = new ArrayList<>();
        int cost = 0;
        for (DathangEntity d: listDH) {
            SanphamEntity sp = ManageWarehouseDao.GetItem(d.getMaSanPham());
            listSP.add(sp);
            cost += sp.getDonGia() * d.getSoLuong();
        }

        NguoidungEntity user = (new UserDao()).getUserByID(userId);

        MagiamgiaEntity maGiam = PaymentDao.ReceivedMaGiamGia(cost);
        int discount = 0;
        if (maGiam != null) {
            discount = cost * (maGiam.getGtDuocGiam() / 100);
            request.setAttribute("TenMaGiamGia", maGiam.getTenMa());
        }

        request.setAttribute("listDV", listDV);
        request.setAttribute("listSP", listSP);
        request.setAttribute("cost", cost);
        request.setAttribute("discount", discount);
        request.setAttribute("user", user);
        request.setAttribute("MaGio", maGio);

        RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maGioStr = request.getParameter("MaGio");
        String maGiam = request.getParameter("MaGiamGia");
        String maDonViGiaoHang = request.getParameter("MaDonViGiaoHang");
        String tongGiaTriStr = request.getParameter("TongGiaTri");

        List<DonhangEntity> listDH = PaymentDao.GetDonHang();
        listDH.sort(Comparator.comparing(DonhangEntity::getMaDon).reversed());
        int maDon = listDH.get(0).getMaDon() + 1;

        // response.getWriter().append(String.format("MaGio: %s\nMaGiamGia: %s\nMaDonViGiaoHang: %s\nTongGiaTri: %s", maGioStr, maGiam, maDonViGiaoHang, tongGiaTriStr));

        DonhangEntity don = new DonhangEntity();
        don.setMaDon(maDon);
        don.setMaGio(Integer.parseInt(maGioStr));
        don.setMaDonViGiaoHang(maDonViGiaoHang);
        don.setTtDonHang("Đã tiếp nhận");
        don.setTtThanhToan(false);
        don.setTongGiaTri(Integer.parseInt(tongGiaTriStr));
        don.setNgayDat(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        String status = PaymentDao.AddDonHang(don);

        request.setAttribute("status", status);
        request.setAttribute("authorize", true);

        RequestDispatcher dispatcher = request.getRequestDispatcher("payment-message.jsp");
        dispatcher.forward(request, response);

    }
}
