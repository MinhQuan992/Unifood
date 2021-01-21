package com.mvc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetProDetailController", urlPatterns = {"/getprodetail"})
public class GetProDetailController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String maSanPham = request.getParameter("maSanPham");
        String tenSanPham = request.getParameter("tenSanPham");
        String donGia = request.getParameter("donGia");
        String donViTinh = request.getParameter("donViTinh");
        String soLuong = request.getParameter("soLuong");
        String anhMinhHoa = request.getParameter("anhMinhHoa");
        String maNhom = request.getParameter("maNhom");
        String maKho = request.getParameter("maKho");
        String moTa = request.getParameter("moTa");

request.setAttribute("maSanPham", maSanPham);
request.setAttribute("tenSanPham", tenSanPham);
request.setAttribute("donGia", donGia);
request.setAttribute("donViTinh", donViTinh);
request.setAttribute("soLuong", soLuong);
request.setAttribute("anhMinhHoa", anhMinhHoa);
request.setAttribute("maNhom", maNhom);
request.setAttribute("maKho", maKho);
request.setAttribute("moTa", moTa);

        request.getRequestDispatcher("/editDetail.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
