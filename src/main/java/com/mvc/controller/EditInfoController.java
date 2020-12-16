package com.mvc.controller;

import com.mvc.dao.EditInfoDao;
import com.mvc.entities.NguoidungEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        NguoidungEntity user = EditInfoDao.authorizeEditInfo(request.getParameter("userId"));

        if (user != null)
        {
            request.setAttribute("userID", user.getMaNguoiDung());
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check for error, update changes to database and show changes to user
        Pattern specialCharsPattern = Pattern.compile("[!@#$%^&*()?\"':{}|<>]");
        Pattern phonePattern = Pattern.compile("^(\\d){10}$");
        Pattern emailPattern = Pattern.compile("\\A(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)\\Z");

        String userId = request.getParameter("UserId");
        String password = request.getParameter("newPassword");
        String rePassword = request.getParameter("rePassword");
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String birthDateStr = request.getParameter("birthDate");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Matcher phoneMatcher = phonePattern.matcher(phone);
        Matcher emailMatcher = emailPattern.matcher(email);

        Map<String, String> errors = new HashMap<String, String>();

        if (specialCharsPattern.matcher(password).find()) {
            errors.put("password", "Must not contain any special characters !@#$%^&*()?\"':{}|<>");
        }

        if (!rePassword.equals(password)) {
            errors.put("rePassword", "Not match with new password");
        } else if (specialCharsPattern.matcher(rePassword).find()) {
            errors.put("rePassword", "Must not contain any special characters !@#$%^&*()?\"':{}|<>");
        }

        if (fullName.equals("")) {
            errors.put("fullName", "Must not be empty");
        } else if (specialCharsPattern.matcher(fullName).find()) {
            errors.put("fullName", "Must not contain any special characters !@#$%^&*()?\"':{}|<>");
        }

        if (!(gender.equals("Nam") || gender.equals("Nữ"))) {
            errors.put("gender", "Must be either 'Nam' or 'Nữ'.");
        }

        java.sql.Date birthDate = null;
        try {
            java.util.Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
            java.util.Date today = Calendar.getInstance().getTime();
            if (birth.after(today)) {
                throw new Exception("Birthdate not valid.");
            }
            birthDate = new java.sql.Date(birth.getTime());
        } catch (Exception e) {
            errors.put("birthDate", e.getMessage());
        }

        if (specialCharsPattern.matcher(address).find()) {
            errors.put("address", "Must not contain any special characters !@#$%^&*()?\"':{}|<>");
        }

        if (!phoneMatcher.find()) {
            errors.put("phone", "Must be a 10-digit number.");
        } else {
            phone = phoneMatcher.group();
        }

        if (!emailMatcher.find()) {
            errors.put("email", "Must be a valid email address.");
        } else {
            email = emailMatcher.group();
        }

        String status = "";
        if (errors.isEmpty())
        {
            NguoidungEntity user = new NguoidungEntity();
            user.setMaNguoiDung(userId);
            user.setDiaChi(address);
            user.setNgaySinh(birthDate);
            user.setEmail(email);
            user.setHoVaTen(fullName);
            user.setGioiTinh(gender);
            user.setMatKhau(password);
            user.setDienThoai(phone);

            status = EditInfoDao.updateUserInfo(user);
            if (status == null) {
                status = "Updated on " + Calendar.getInstance().getTime().toString();
            }
        }

        request.setAttribute("error", errors);
        request.setAttribute("authorize", true);
        request.setAttribute("userID", userId);
        request.setAttribute("newPassword", password);
        request.setAttribute("rePassword", rePassword);
        request.setAttribute("fullName", fullName);
        request.setAttribute("gender", gender);
        request.setAttribute("birthDate", birthDateStr);
        request.setAttribute("address", address);
        request.setAttribute("phone", phone);
        request.setAttribute("email", email);
        request.setAttribute("status", status);

        String url = "/editInfo.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
