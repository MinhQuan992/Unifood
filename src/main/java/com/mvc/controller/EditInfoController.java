package com.mvc.controller;

import com.mvc.dao.EditInfoDao;
import com.mvc.entities.NguoidungEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "EditInfoController")
public class EditInfoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        NguoidungEntity user = (NguoidungEntity) request.getSession().getAttribute("User");

        // Check for error, update changes to database and show changes to user
        Pattern specialCharsPattern = Pattern.compile("[!@#$%^&*()?\"':{}|<>]");
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{10,50}$");
        Pattern phonePattern = Pattern.compile("^(\\d){10}$");
        Pattern emailPattern = Pattern.compile("\\A(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)\\Z");

        //  String userId = request.getParameter("UserId");
        String newPassword = request.getParameter("newPassword");
        String rePassword = request.getParameter("rePassword");
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String birthDateStr = request.getParameter("birthDate");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        // String email = request.getParameter("email");

        // Matcher emailMatcher = emailPattern.matcher(email);

        Map<String, String> errors = new HashMap<String, String>();

        String status = "";
        int index = 0;

        if (newPassword != null && rePassword != null) {

            Matcher passwordMatcher = passwordPattern.matcher(newPassword);
            if (specialCharsPattern.matcher(newPassword).find()) {
                errors.put("password", "Phải không chứa ký tự đặc biệt !@#$%^&*()?\"':{}|<>");
            } else if (newPassword.equals(user.getMatKhau())) {
                errors.put("password", "Trùng với mật khẩu hiện tại");
            } else if (!passwordMatcher.find()) {
                errors.put("password", "Phải có từ 10 đến 50 ký tự" +
                        " và có ít nhất một chữ số, chữ thường và chữ hoa");
            }  else {
                newPassword = passwordMatcher.group();
            }

            if (!rePassword.equals(newPassword)) {
                errors.put("rePassword", "Không trùng khớp với mật khẩu");
            } else if (specialCharsPattern.matcher(rePassword).find()) {
                errors.put("rePassword", "Phải không chứa ký tự đặc biệt !@#$%^&*()?\"':{}|<>");
            }

            if (errors.isEmpty()) {
                user.setMatKhau(newPassword);
                request.getSession().setAttribute("User", user);

                status = EditInfoDao.updateUserInfo(user);
                if (status == null) {
                    status = "Updated on " + Calendar.getInstance().getTime().toString();
                }
            }
            index = 1;
        } else if (address != null && phone != null) {

            if (specialCharsPattern.matcher(address).find()) {
                errors.put("address", "Phải không chứa ký tự đặc biệt !@#$%^&*()?\"':{}|<>");
            }

            Matcher phoneMatcher = phonePattern.matcher(phone);
            if (!phoneMatcher.find()) {
                errors.put("phone", "Phải có 10 chữ số");
            } else {
                phone = phoneMatcher.group();
            }

            if (errors.isEmpty()) {
                user.setDiaChi(address);
                user.setDienThoai(phone);
                request.getSession().setAttribute("User", user);

                status = EditInfoDao.updateUserInfo(user);
                if (status == null) {
                    status = "Updated on " + Calendar.getInstance().getTime().toString();
                }
            }
            index = 2;
        } else if (fullName != null && gender != null && birthDateStr != null) {

            if (fullName.trim().equals("")) {
                errors.put("fullName", "Không được để trống");
            } else if (specialCharsPattern.matcher(fullName).find()) {
                errors.put("fullName", "Phải không chứa ký tự đặc biệt !@#$%^&*()?\"':{}|<>");
            }

            if (!(gender.equals("Nam") || gender.equals("Nữ"))) {
                errors.put("gender", "Phải là 'Nam' hoặc 'Nữ'");
            }

            java.sql.Date birthDate = null;
            try {
                java.util.Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, -15);
                java.util.Date date = calendar.getTime();
                if (!birth.before(date)) {
                    String dmy =  new SimpleDateFormat("dd/MM/yyyy").format(date);
                    throw new Exception("Ngày sinh không hợp lệ (phải trước ngày " + dmy + ")");
                }
                birthDate = new java.sql.Date(birth.getTime());
            } catch (Exception e) {
                errors.put("birthDate", e.getMessage());
            }

            if (errors.isEmpty()) {
                user.setHoVaTen(fullName);
                user.setGioiTinh(gender);
                user.setNgaySinh(birthDate);
                request.getSession().setAttribute("User", user);

                status = EditInfoDao.updateUserInfo(user);
                if (status == null) {
                    status = "Updated on " + Calendar.getInstance().getTime().toString();
                }
            }
            index = 3;
        }
/*
        if (!emailMatcher.find()) {
            errors.put("email", "Phải là địa chỉ email hợp lệ");
        } else {
            email = emailMatcher.group();
        }

        if (errors.isEmpty())
        {
            NguoidungEntity user = new NguoidungEntity();
            user.setMaNguoiDung(userId);
            user.setDiaChi(address);
            user.setNgaySinh(birthDate);
            user.setEmail(email);
            user.setHoVaTen(fullName);
            user.setGioiTinh(gender);
            user.setMatKhau(newPassword);
            user.setDienThoai(phone);

            request.getSession().setAttribute("User",user);

            status = EditInfoDao.updateUserInfo(user);
            if (status == null) {
                status = "Updated on " + Calendar.getInstance().getTime().toString();
            }
        }
*/
        request.setAttribute("error", errors);
        request.setAttribute("authorize", true);
        // request.setAttribute("userID", userId);
        request.setAttribute("newPassword", "");
        request.setAttribute("rePassword", "");
        request.setAttribute("fullName", (fullName == null) ? user.getHoVaTen() : fullName);
        request.setAttribute("gender", (gender == null) ? user.getGioiTinh() : gender);
        request.setAttribute("birthDate", (birthDateStr == null) ? user.getNgaySinh().toString() : birthDateStr);
        request.setAttribute("address", (address == null) ? user.getDiaChi() : address);
        request.setAttribute("phone", (phone == null) ? user.getDienThoai() : phone);
        // request.setAttribute("email", email);
        request.setAttribute("status", status);
        request.setAttribute("index", index);

        String url = "/editInfo.jsp";


        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        NguoidungEntity user = (NguoidungEntity) session.getAttribute("User");

        if (user != null)
        {
            // request.setAttribute("userID", user.getMaNguoiDung());
            request.setAttribute("password", user.getMatKhau());
            request.setAttribute("fullName", user.getHoVaTen());
            request.setAttribute("gender", user.getGioiTinh());
            request.setAttribute("birthDate", user.getNgaySinh().toString());
            request.setAttribute("address", user.getDiaChi());
            request.setAttribute("phone", user.getDienThoai());
            request.setAttribute("email", user.getEmail());
            request.setAttribute("authorize", true);
        }

        String url = "/editInfo.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}