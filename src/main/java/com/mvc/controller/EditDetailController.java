package com.mvc.controller;

import com.mvc.dao.SanPhamDAO;
import com.mvc.entities.SanphamEntity;

import javax.el.BeanELResolver;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "EditDetailController", urlPatterns = "/editdetail")
public class EditDetailController extends HttpServlet {
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

        boolean haserror = false;
        int donGiaNum = 0;
        int soLuongNum = 0;
        short maNhomNum = 0;

        Pattern numPattern = Pattern.compile("\\d+");
        Matcher donGiaMatcher = numPattern.matcher(donGia);
        Matcher soLuongMatcher = numPattern.matcher(soLuong);
        Matcher maNhomMatcher = numPattern.matcher(maNhom);
        if(!donGiaMatcher.matches())
        {
            request.setAttribute("donGiaError", "Nhập Số Nguyên Dương");
            haserror=true;
        }
        else {
            donGiaNum = Integer.parseInt(donGia);
            if (donGiaNum == 0)
            {
                haserror=true;
                request.setAttribute("donGiaError", "Nhập Số Nguyên Dương");
            }
        }

        if(!soLuongMatcher.matches())
        {
            request.setAttribute("soLuongError", "Nhập Số Nguyên Dương");
            haserror=true;
        }
        else {
            soLuongNum = Integer.parseInt(soLuong);
            if (soLuongNum == 0)
            {
                haserror=true;
                request.setAttribute("soLuongError", "Nhập Số Nguyên Dương");
            }
        }

        if(!maNhomMatcher.matches())
        {
            request.setAttribute("maNhomError", "Nhập Số Nguyên Dương");
            haserror=true;
        }
        else {
            maNhomNum = (short)Integer.parseInt(maNhom);
            if (maNhomNum == 0 || maNhomNum < 0 || maNhomNum > 99)
            {
                haserror=true;
                request.setAttribute("maNhomError", "Nhập Số Nguyên Dương");
            }
        }

        if(maSanPham.equals("")) {
            haserror=true;
            request.setAttribute("maSanPhamError", "Nhập mã sản phẩm");
        }

        if(tenSanPham.equals(""))
        {
            haserror=true;
            request.setAttribute("tenSanPhamError", "Nhập tên sản phẩm");
        }

        if(donViTinh.equals(""))
        {
            haserror=true;
            request.setAttribute("donViTinhError", "Nhập Đơn Vị Tính sản phẩm");
        }

        if(anhMinhHoa.equals(""))
        {
            haserror=true;
            request.setAttribute("anhMinhHoaError", "Chọn hình ảnh sản phẩm");
        }

        if(maKho.equals(""))
        {
            haserror=true;
            request.setAttribute("maKhoError", "Nhập Mã Kho của sản phẩm");
        }
/*
        if(moTa.equals(""))
        {
            haserror=true;
            request.setAttribute("moTaError", "Nhập Mô Tả sản phẩm");
        }
*/
        if(haserror==false)
        {
            SanphamEntity sanphamEntity = new SanphamEntity(maSanPham, tenSanPham, donViTinh, donGiaNum, soLuongNum, anhMinhHoa, maNhomNum, maKho, moTa);
            SanPhamDAO sanPhamDAO = new SanPhamDAO();
            boolean canExecute = sanPhamDAO.suaSP(sanphamEntity);
            if(canExecute)
            {
                request.setAttribute("Trangthaicapnhat", true);
            }
            else
            {
                request.setAttribute("Trangthaicapnhat", false);
            }
        }
        else {
            request.setAttribute("Trangthaicapnhat",false);
        }
        request.setAttribute("maSanPham", maSanPham);
        request.setAttribute("tenSanPham", tenSanPham);
        request.setAttribute("donGia", donGia);
        request.setAttribute("donViTinh", donViTinh);
        request.setAttribute("soLuong", soLuong);
        request.setAttribute("anhMinhHoa", anhMinhHoa);
        request.setAttribute("maNhom", maNhom);
        request.setAttribute("maKho", maKho);
        request.setAttribute("moTa", moTa);

        request.getRequestDispatcher("editDetail.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}