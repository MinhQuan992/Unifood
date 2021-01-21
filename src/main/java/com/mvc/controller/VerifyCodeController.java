package com.mvc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VerifyCodeController", urlPatterns = {"/verification"})
public class VerifyCodeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String verificationCode = request.getParameter("verificationCode");
        String inputCode = request.getParameter("inputCode").trim();
        String url = "";

        if (inputCode.equals(verificationCode))
            url = "/change-password.jsp";
        else {
            request.setAttribute("wrongCode", true);
            url = "/verification.jsp";
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
