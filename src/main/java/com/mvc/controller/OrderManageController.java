package com.mvc.controller;

import com.mvc.dao.*;
import com.mvc.entities.*;
import com.mvc.utility.EmailUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderManageController")
public class OrderManageController extends HttpServlet {
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
        String subject = "Your order was updated!";
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
            System.out.println("This servlet is being forward you to: /Page/OderEdit.jsp");
            request.getRequestDispatcher("/Page/OderEdit.jsp").forward(request,response);
            return;
        }

        String cancel = request.getParameter("Cancel");
        String savechange = request.getParameter("Save");
        String orderCode = request.getParameter("OrderCode");

        if (savechange!=null)
        {
            String shippingUnit = request.getParameter("ShippingUnit");
            String shippingDate = request.getParameter("TransDate");
            String completeDate = request.getParameter("CompleteDate");
            String orderStatus = request.getParameter("Status");
            Boolean payStatus = Boolean.parseBoolean(request.getParameter("Payment"));
            DonhangEntity donhangEntity = paymentDao.getPaymentDataRaw(Integer.parseInt(orderCode));
            donhangEntity.setMaDonViGiaoHang(shippingUnit);

            try {
                Date ngayGiaoHang = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(shippingDate).getTime());
                if (donhangEntity.getNgayDat().compareTo(ngayGiaoHang)<=0)
                    donhangEntity.setNgayGiaoHang(ngayGiaoHang);
                else
                    request.setAttribute("AcessAlertMessage","You can't set set delivery time before order placed");
            } catch (ParseException e) {
                donhangEntity.setNgayGiaoHang(null);
                e.printStackTrace();
            }

            try {
                Date ngayThanhToan = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(completeDate).getTime());
                if (donhangEntity.getNgayGiaoHang()!=null && donhangEntity.getNgayGiaoHang().compareTo(ngayThanhToan)<=0)
                    donhangEntity.setNgayThanhToan(new java.sql.Date(ngayThanhToan.getTime()));
                else
                    request.setAttribute("AcessAlertMessage","You can't set set complete time before order delivered");
            } catch (ParseException e) {
                donhangEntity.setNgayThanhToan(null);
                e.printStackTrace();
            }

            donhangEntity.setTtDonHang(orderStatus);
            donhangEntity.setTtThanhToan(payStatus);

            System.out.println(orderStatus);
            String message = "";
            switch (orderStatus)
            {
                case "Đang xử lý":
                    message = "In Processing!!";
                    break;
                case "Đã tiếp nhận":
                    message = "In Accepted Status!!";
                    break;
                case "Giao hàng thành công":
                    message = "Delivered Successfully!!";
                    break;
                case "Đã bị hủy":
                    message = "Canceled!!";
                    break;
                case "Đang giao hàng":
                    message = "Delivering to you!!";
                    break;
                default:
                    message = "Nothings!";
            }

            DonhangEntity DH = (DonhangEntity) paymentDao.GetDonHang(Integer.parseInt(orderCode));
            int CartCode = DH.getMaGio();
            CartDao cartDao = new CartDao();
            GiohangEntity GH = cartDao.GetCartData(CartCode);
            UserDao userDao = new UserDao();
            NguoidungEntity userss = userDao.getUserByID(GH.getMaNguoiDung());
            SendEmail(message,userss.getEmail());
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
