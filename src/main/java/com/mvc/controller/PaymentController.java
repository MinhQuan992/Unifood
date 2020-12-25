package com.mvc.controller;

import com.mvc.dao.ManageWarehouseDao;
import com.mvc.dao.PaymentDao;
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

        List<DonvigiaohangEntity> listDV = PaymentDao.GetDVGiaoHang();
        List<DathangEntity> listDat = PaymentDao.GetDatHang(maGio);
        List<SanphamEntity> listSP = new ArrayList<>();
        int cost = 0;
        for (DathangEntity d: listDat) {
            SanphamEntity sp = ManageWarehouseDao.GetItem(d.getMaSanPham());
            listSP.add(sp);
            cost += sp.getDonGia() * d.getSoLuong();
        }

        List<DonhangEntity> listDon = PaymentDao.GetAllDonHang();
        listDon.sort(Comparator.comparing(DonhangEntity::getMaDon).reversed());
        int maDon = 1;
        if (!listDon.isEmpty()) {
            maDon = listDon.get(0).getMaDon() + 1;
        }
        DonhangEntity don = new DonhangEntity();
        don.setMaDon(maDon);
        don.setMaGio(maGio);
        don.setMaGiamGia(null);
        don.setMaDonViGiaoHang(null);
        don.setTtDonHang("Đã tiếp nhận");
        don.setTtThanhToan(false);
        don.setTongGiaTri(cost);
        don.setNgayDat(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        String status = PaymentDao.AddDonHang(don);

        request.setAttribute("listDV", listDV);
        request.setAttribute("listSP", listSP);
        request.setAttribute("cost", cost);
        request.setAttribute("MaDon", maDon);
        // request.setAttribute("discount", discount);
        request.setAttribute("status", status);

        RequestDispatcher dispatcher = request.getRequestDispatcher("payment.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // String maGioStr = request.getParameter("MaGio");
        int maDon = Integer.parseInt(request.getParameter("MaDon"));
        String maDonViGiaoHang = request.getParameter("MaDonViGiaoHang");
        /*
        String status = null;
        for (DonhangEntity dh: listDH) {
            if (dh.getMaGio().equals(Integer.parseInt(maGioStr))) {
                don.setMaDon(dh.getMaDon());
                status = "Đơn hàng có trùng mã giỏ " + maGioStr + "\n";
                break;
            }
        }
         */
        DonhangEntity don = PaymentDao.GetDonHang(maDon);
        don.setTtDonHang("Đang xử lý");
        don.setMaDonViGiaoHang(maDonViGiaoHang);

        String status = PaymentDao.EditDonHang(don);

        request.setAttribute("status", status);
        request.setAttribute("authorize", true);

        RequestDispatcher dispatcher = request.getRequestDispatcher("payment-message.jsp");
        dispatcher.forward(request, response);

        // response.getWriter().append(String.format("MaGio: %s\nMaGiamGia: %s\nMaDonViGiaoHang: %s\nTongGiaTri: %s", maGioStr, maGiam, maDonViGiaoHang, tongGiaTriStr));
    }
}
