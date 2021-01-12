package com.mvc.controller;

import com.mvc.dao.SanPhamDAO;
import com.mvc.entities.SanphamEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AddProController", urlPatterns = {"/addPro"})
public class AddProController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String maSanPham = request.getParameter("maSanPham").trim();
        String tenSanPham = request.getParameter("tenSanPham").trim();
        String donGia = request.getParameter("donGia").trim();
        String donViTinh = request.getParameter("donViTinh").trim();
        String soLuong = request.getParameter("soLuong").trim();
        String anhMinhHoa = request.getParameter("anhMinhHoa").trim();
        String maNhom = request.getParameter("maNhom").trim();
        String maKho = request.getParameter("maKho").trim();
        String moTa = request.getParameter("moTa").trim();


        boolean haserror = false;
        int donGiaNum = 0;
        int soLuongNum = 0;
        short maNhomNum = 0;

        Pattern numPattern = Pattern.compile("\\d+");
        Matcher donGiaMatcher = numPattern.matcher(donGia);
        Matcher soLuongMatcher = numPattern.matcher(soLuong);
        Matcher maNhomMatcher = numPattern.matcher(maNhom);

        if (donGia.equals("")) {
            request.setAttribute("donGiaError", "Nhập Số Nguyên Dương");
            haserror = true;
        }
        if (!donGiaMatcher.matches()) {
            request.setAttribute("donGiaError", "Nhập Số Nguyên Dương");
            haserror = true;
        } else {
            donGiaNum = Integer.parseInt(donGia);
            if (donGiaNum == 0 || donGiaNum < 0) {
                haserror = true;
                request.setAttribute("donGiaError", "Nhập Số Nguyên Dương");
            }
        }

        if (soLuong.equals("")) {
            request.setAttribute("soLuongError", "Nhập Số Nguyên Dương");
            haserror = true;
        }
        if (!soLuongMatcher.matches()) {
            request.setAttribute("soLuongError", "Nhập Số Nguyên Dương");
            haserror = true;
        } else {
            soLuongNum = Integer.parseInt(soLuong);
            if (soLuongNum == 0) {
                haserror = true;
                request.setAttribute("soLuongError", "Nhập Số Nguyên Dương");
            }
        }

        if (maNhom.equals(""))
        {
            request.setAttribute("maNhomError", "Nhập Số Nguyên Dương");
            haserror=true;
        }
        if(!maNhomMatcher.matches())
        {
            request.setAttribute("maNhomError", "Nhập Số Nguyên Dương");
            haserror=true;
        }
        else {
            maNhomNum = (short)Integer.parseInt(maNhom);
            if (maNhomNum == 0)
            {
                haserror=true;
                request.setAttribute("maNhomError", "Nhập Số Nguyên Dương");
            }
        }

        if(maSanPham.equals(""))
        {
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
            request.setAttribute("donViTinhError", "Nhập đơn vị của sản phẩm");
        }

        if (anhMinhHoa.equals(""))
        {
            haserror=true;
            request.setAttribute("anhMinhHoaError", "Nhập url hình ảnh");
        }

        if(maKho.equals(""))
        {
            haserror=true;
            request.setAttribute("maKhoError", "Nhập mã kho của sản phẩm");
        }

        if (moTa.equals(""))
        {
            haserror=true;
            request.setAttribute("moTaError", "Nhập mô tả sản phẩm");
        }

        if(haserror==false)
        {
            SanphamEntity sanphamEntity = new SanphamEntity(maSanPham, tenSanPham, donViTinh, donGiaNum, soLuongNum, anhMinhHoa, maNhomNum, maKho, moTa);
            SanPhamDAO sanPhamDAO = new SanPhamDAO();
            boolean canExecute = sanPhamDAO.themSP(sanphamEntity);
            if(canExecute)
            {
                request.setAttribute("Trangthaithem", true);
            }
            else
            {
                request.setAttribute("Trangthaithem", false);
                request.setAttribute("maSanPham", maSanPham);
                request.setAttribute("tenSanPham", tenSanPham);
                request.setAttribute("donGia", donGia);
                request.setAttribute("donViTinh", donViTinh);
                request.setAttribute("soLuong", soLuong);
                request.setAttribute("anhMinhHoa", anhMinhHoa);
                request.setAttribute("maNhom", maNhom);
                request.setAttribute("maKho", maKho);
                request.setAttribute("moTa", moTa);
            }
        }
        else {
            request.setAttribute("Trangthaithem",false);
            request.setAttribute("maSanPham", maSanPham);
            request.setAttribute("tenSanPham", tenSanPham);
            request.setAttribute("donGia", donGia);
            request.setAttribute("donViTinh", donViTinh);
            request.setAttribute("soLuong", soLuong);
            request.setAttribute("anhMinhHoa", anhMinhHoa);
            request.setAttribute("maNhom", maNhom);
            request.setAttribute("maKho", maKho);
            request.setAttribute("moTa", moTa);
        }

        request.getRequestDispatcher("/addPro.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}