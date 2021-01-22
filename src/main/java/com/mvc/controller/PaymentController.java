package com.mvc.controller;

import com.mvc.dao.EditInfoDao;
import com.mvc.dao.ManageWarehouseDao;
import com.mvc.dao.PaymentDao;
import com.mvc.dao.UserDao;
import com.mvc.entities.*;
import com.mvc.utility.EmailUtility;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "PaymentController")
public class PaymentController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String host;
    private String port;
    private String socketFactoryClass;
    private String auth;
    private String email;
    private String name;
    private String pass;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        socketFactoryClass = context.getInitParameter("socketFactoryClass");
        auth = context.getInitParameter("auth");
        email = context.getInitParameter("email");
        name = context.getInitParameter("name");
        pass = context.getInitParameter("pass");
    }

    public void SendEmail(String Message, String recipient)
    {
        String subject = "We have received the order from you!";
        String content = "We send from UNIFOOD\nYour order status was changed to:  " + Message +
                "\nPlease visit our page to track your order information!\nThanks!\nUnifood Team";
        try
        {
            EmailUtility.sendEmail(host, port, socketFactoryClass, auth, email, name, pass, recipient, subject, content);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");

//        if (user.getDienThoai() == null || user.getDiaChi() == null ||
//                user.getDienThoai().isEmpty() || user.getDiaChi().isEmpty()) {
//            request.setAttribute("ErrorID", 1);
//            request.setAttribute("authorize", true);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("payment-message.jsp");
//            dispatcher.forward(request, response);
//            return;
//        }

        int maGio;
        String magio = request.getParameter("MaGio");
        maGio = Integer.parseInt(magio);

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
        if (user.getDienThoai() == null || user.getDiaChi() == null) {
            don.setHoVaTen(session.getAttribute("User_Name").toString());
            don.setDiaChi(session.getAttribute("User_Address").toString());
            don.setDienThoai(session.getAttribute("User_Phone").toString());
        } else {
            don.setHoVaTen(user.getHoVaTen());
            don.setDiaChi(user.getDiaChi());
            don.setDienThoai(user.getDienThoai());
        }


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
        return;
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

        // response.getWriter().append(String.format("MaDon: %s\nMaDonViGiaoHang: %s\nDiaChi: %s\nDiaChiKhac: %s", maDon, maDonViGiaoHang, diaChi, diaChiKhac));
        // return;

        DonhangEntity don = (DonhangEntity) PaymentDao.GetDonHang(maDon);
        don.setTtDonHang("Đang xử lý");
        don.setMaDonViGiaoHang(maDonViGiaoHang);
        // don.setMaNguoiDung(user.getMaNguoiDung());
        // don.setHoVaTen(user.getHoVaTen());
        // don.setDienThoai(user.getDienThoai());
        if (diaChi.equals("default") || diaChiKhac == null) {
            //don.setDiaChi(diaChi);
        } else {
            don.setDiaChi(diaChiKhac);
        }

        String status = PaymentDao.EditDonHang(don);

        if (status != null) {
            request.setAttribute("ErrorID", 2);
            request.setAttribute("status", status);
        }

        request.setAttribute("authorize", true);

        RequestDispatcher dispatcher = request.getRequestDispatcher("payment-message.jsp");
        SendEmail("Your order was accepted!! and in process!!",user.getEmail());
        dispatcher.forward(request, response);

        // response.getWriter().append(String.format("MaGio: %s\nMaGiamGia: %s\nMaDonViGiaoHang: %s\nTongGiaTri: %s", maGioStr, maGiam, maDonViGiaoHang, tongGiaTriStr));

    }
}