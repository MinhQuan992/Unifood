package com.mvc.controller;

import com.mvc.dao.PaymentDao;
import com.mvc.dao.ShipingUnitDao;
import com.mvc.entities.DonhangEntity;
import com.mvc.entities.DonvigiaohangEntity;
import com.mvc.entities.NguoidungEntity;
import com.mvc.entities.ViewAllOrderEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderManageController")
public class OrderManageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");
        if (!user.getMaNguoiDung().startsWith("QL"))
        {
            System.out.println("Alert: Customer "+user.getMaNguoiDung()+" Accessed this page!");
            request.setAttribute("AcessAlertMessage","You are not allowed to access this page");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        String funcRequest = request.getParameter("ParaName");
        String keyValue = request.getParameter("KeyValue");
        PaymentDao paymentDao = new PaymentDao();

        if (funcRequest!=null && funcRequest.equals("EditPayment"))
        {
            ViewAllOrderEntity payment = paymentDao.getPaymentData(Integer.parseInt(keyValue));
            ShipingUnitDao shipingUnitDao = new ShipingUnitDao();
            List<DonvigiaohangEntity> listShiper = shipingUnitDao.getAllShippingData();
            request.setAttribute("Payment",payment);
            request.setAttribute("ShipperList",listShiper);
            request.getRequestDispatcher("/Page/OderEdit.jsp").forward(request,response);
        }

        String cancel = request.getParameter("Cancel");
        String savechange = request.getParameter("Save");
        if (savechange!=null)
        {
            String orderCode = request.getParameter("OrderCode");
            String shippingUnit = request.getParameter("ShippingUnit");
            String shippingDate = request.getParameter("TransDate");
            String completeDate = request.getParameter("CompleteDate");
            String orderStatus = request.getParameter("Status");
            Boolean payStatus = Boolean.parseBoolean(request.getParameter("Payment"));
            DonhangEntity donhangEntity = paymentDao.getPaymentDataRaw(Integer.parseInt(orderCode));
            donhangEntity.setMaDonViGiaoHang(shippingUnit);
            try {
                donhangEntity.setNgayGiaoHang(new java.sql.Date(
                        new SimpleDateFormat("yyyy-MM-dd").parse(shippingDate).getTime()));
            } catch (ParseException e) {
                donhangEntity.setNgayGiaoHang(null);
                e.printStackTrace();
            }
            try {
                donhangEntity.setNgayThanhToan(new java.sql.Date(
                        new SimpleDateFormat("yyyy-MM-dd").parse(completeDate).getTime()));
            } catch (ParseException e) {
                donhangEntity.setNgayThanhToan(null);
                e.printStackTrace();
            }
            //
            donhangEntity.setTtDonHang(orderStatus);
            donhangEntity.setTtThanhToan(payStatus);

            System.out.println(orderStatus);
            /*System.out.println(donhangEntity.getMaDon()+"//"+donhangEntity.getMaDonViGiaoHang()+"//");
            System.out.println(orderStatus);
            System.out.println(payStatus);*/
            paymentDao.UpdateOrderData(donhangEntity);
        }

        List<ViewAllOrderEntity> listPayment = new ArrayList<ViewAllOrderEntity>();
        listPayment = paymentDao.getAllPaymentData();
        request.setAttribute("ListPayments",listPayment);

        String url = "Page/OrderManage.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
