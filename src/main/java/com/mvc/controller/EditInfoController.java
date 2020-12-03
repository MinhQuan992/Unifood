package com.mvc.controller;

import com.mvc.bean.UserBean;
import com.mvc.dao.EditInfoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "EditInfoController")
public class EditInfoController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");

        UserBean userBean = new UserBean();
        userBean.setUserID(userId);

        EditInfoDao editInfoDao = new EditInfoDao();
        boolean authorize = editInfoDao.authorizeEditInfo(userBean);

        if (authorize)
        {
            request.setAttribute("userID", userBean.getUserID());
            request.setAttribute("userName", userBean.getUsername());
            request.setAttribute("password", userBean.getPassword());
            request.setAttribute("fullName", userBean.getFullName());
            request.setAttribute("gender", userBean.getGender());
            request.setAttribute("birthDate", userBean.getBirthDate());
            request.setAttribute("address", userBean.getAddress());
            request.setAttribute("phone", userBean.getPhone());
            request.setAttribute("email", userBean.getEmail());
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
        String userName = request.getParameter("userName");
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

        String error_userName = "";
        if (userName.equals("")) {
            error_userName = "Must not be empty.";
        } else if (specialCharsPattern.matcher(userName).find()) {
            error_userName = "Must not contain any special characters !@#$%^&*()?\"':{}|<>";
        }

        String error_password = "";
        if (specialCharsPattern.matcher(password).find()) {
            error_password = "Must not contain any special characters !@#$%^&*()?\"':{}|<>";
        }

        String error_rePassword = "";
        if (!rePassword.equals(password)) {
            error_rePassword = "Not match with password";
        } else if (specialCharsPattern.matcher(rePassword).find()) {
            error_rePassword = "Must not contain any special characters !@#$%^&*()?\"':{}|<>";
        }

        String error_fullName = "";
        if (fullName.equals("")) {
            error_fullName = "Must not be empty";
        } else if (specialCharsPattern.matcher(fullName).find()) {
            error_fullName = "Must not contain any special characters !@#$%^&*()?\"':{}|<>";
        }

        String error_gender = "";
        if (!(gender.equals("Nam") || gender.equals("Nữ"))) {
            error_gender = "Must be either 'Nam' or 'Nữ'";
        }

        String error_birthDate = "";
        Date birthDate = new Date();
        try {
            birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
            Date today = Calendar.getInstance().getTime();
            if (birthDate.after(today)) {
                throw new Exception("Birthdate not valid.");
            }
        } catch (Exception e) {
            error_birthDate = e.getMessage();
        }

        String error_address = "";
        if (specialCharsPattern.matcher(address).find()) {
            error_address = "Must not contain any special characters !@#$%^&*()?\"':{}|<>";
        }

        String error_phone = "";
        if (!phoneMatcher.find()) {
            error_phone = "Must be a 10-digit number.";
        } else {
            phone = phoneMatcher.group();
        }

        String error_email = "";
        if (!emailMatcher.find()) {
            error_email = "Must be a valid email address.";
        } else {
            email = emailMatcher.group();
        }

        String status = "";
        if (error_address.equals("") && error_birthDate.equals("") && error_email.equals("")
                && error_fullName.equals("") && error_gender.equals("") && error_password.equals("")
                && error_phone.equals("") && error_rePassword.equals("") && error_userName.equals(""))
        {
            UserBean userBean = new UserBean();
            userBean.setUserID(userId);
            userBean.setAddress(address);
            userBean.setBirthDate(birthDate);
            userBean.setEmail(email);
            userBean.setFullName(fullName);
            userBean.setGender(gender);
            userBean.setPassword(password);
            userBean.setPhone(phone);
            userBean.setUsername(userName);

            EditInfoDao editInfoDao = new EditInfoDao();
            status = editInfoDao.updateUserInfo(userBean);
            if (status.equals("")) {
                status = "Updated on " + Calendar.getInstance().getTime().toString();
            }
        }

        request.setAttribute("authorize", true);
        request.setAttribute("userID", userId);
        request.setAttribute("userName", userName);
        request.setAttribute("error_userName", error_userName);
        request.setAttribute("newPassword", password);
        request.setAttribute("error_password", error_password);
        request.setAttribute("rePassword", rePassword);
        request.setAttribute("error_rePassword", error_rePassword);
        request.setAttribute("fullName", fullName);
        request.setAttribute("error_fullName", error_fullName);
        request.setAttribute("gender", gender);
        request.setAttribute("error_gender", error_gender);
        request.setAttribute("birthDate", birthDateStr);
        request.setAttribute("error_birthDate", error_birthDate);
        request.setAttribute("address", address);
        request.setAttribute("error_address", error_address);
        request.setAttribute("phone", phone);
        request.setAttribute("error_phone", error_phone);
        request.setAttribute("email", email);
        request.setAttribute("error_email", error_email);
        request.setAttribute("status", status);

        String url = "/editInfo.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
