package com.mvc.controller;

import com.mvc.dao.EditInfoDao;
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
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        NguoidungEntity user = (NguoidungEntity) request.getSession().getAttribute("User");

        if (user.getDienThoai() == null || user.getDiaChi() == null ||
                user.getDienThoai().isEmpty() || user.getDiaChi().isEmpty()) {
            request.setAttribute("ErrorID", 1);
            request.setAttribute("authorize", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("payment-message.jsp");
            dispatcher.forward(request, response);
            return;
        }

        int maGio = Integer.parseInt(request.getParameter("MaGio"));

        List<DonvigiaohangEntity> listDV = PaymentDao.GetDVGiaoHang();
        List<DathangEntity> listDat = PaymentDao.GetDatHang(maGio);
        List<SanphamEntity> listSP = new ArrayList<>();

        int cost = 0;
        for (DathangEntity d: listDat) {
            SanphamEntity sp = ManageWarehouseDao.GetItem(d.getMaSanPham());
            sp.setSoLuong(d.getSoLuong());
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
        don.setMaDonViGiaoHang(null);
        don.setTtDonHang("Đã tiếp nhận");
        don.setTtThanhToan(false);
        don.setTongGiaTri(cost);
        don.setNgayDat(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        String status = PaymentDao.AddDonHang(don);

        request.setAttribute("MaGio",maGio);
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
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        NguoidungEntity user = (NguoidungEntity) request.getSession().getAttribute("User");

        // String maGioStr = request.getParameter("MaGio");
        int maDon = Integer.parseInt(request.getParameter("MaDon"));
        String maDonViGiaoHang = request.getParameter("MaDonViGiaoHang");
        String diaChi = request.getParameter("DiaChi");
        String diaChiKhac = request.getParameter("DiaChiKhac");

        response.getWriter().append(String.format("MaDon: %s\nMaDonViGiaoHang: %s\nDiaChi: %s\nDiaChiKhac: %s", maDon, maDonViGiaoHang, diaChi, diaChiKhac));
        return;

//        DonhangEntity don = (DonhangEntity) PaymentDao.GetDonHang(maDon);
//        don.setTtDonHang("Đang xử lý");
//        don.setMaDonViGiaoHang(maDonViGiaoHang);
//
//        String status = PaymentDao.EditDonHang(don);
//
//        request.setAttribute("ErrorID", 2);
//        request.setAttribute("status", status);
//        request.setAttribute("authorize", true);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("payment-message.jsp");
//        dispatcher.forward(request, response);

        // response.getWriter().append(String.format("MaGio: %s\nMaGiamGia: %s\nMaDonViGiaoHang: %s\nTongGiaTri: %s", maGioStr, maGiam, maDonViGiaoHang, tongGiaTriStr));
    }
}