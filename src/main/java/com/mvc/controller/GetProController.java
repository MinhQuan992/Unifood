package com.mvc.controller;

import com.mvc.dao.SanPhamDAO;
import com.mvc.entities.SanphamEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@WebServlet(name = "GetProController", urlPatterns = {"/getPro"})
public class GetProController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        SanPhamDAO sanPhamDAO=new SanPhamDAO();
        List<SanphamEntity> product= sanPhamDAO.getAllProduct();

        request.getSession().setAttribute("products",product);

        String url = "/editPro.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
