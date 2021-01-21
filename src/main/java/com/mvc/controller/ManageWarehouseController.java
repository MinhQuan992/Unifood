package com.mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import com.mvc.dao.ManageWarehouseDao;
import com.mvc.entities.KhohangEntity;
import com.mvc.entities.SanphamEntity;

@WebServlet(name = "ManageWarehouseController")
public class ManageWarehouseController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        Map< KhohangEntity, List<SanphamEntity> > map = new HashMap<>();
        List< KhohangEntity > listKho = ManageWarehouseDao.GetWarehouses();
        if (listKho != null) {
            for (KhohangEntity k: listKho) {
                map.put(k, ManageWarehouseDao.GetItemsInWarehouse(k));
            }
        }

        request.setAttribute("map", map);
        request.setAttribute("authorize", true);

        String url = "/manageWarehouse.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        Pattern specialCharsPattern = Pattern.compile("[!@#$%^&*()?\"':{}|<>]");

        String type = request.getParameter("Type");
        String maKho = request.getParameter("MaKho");
        String maSanPham = request.getParameter("MaSanPham");

        if (type.contains("Warehouse")) {
            if (type.contains("Delete")) {
                String status = ManageWarehouseDao.DeleteWarehouse(maKho);
                request.setAttribute("DeleteStatus", status);
                doPost(request, response); // Forward to manageWarehouse.jsp
                return;
            }

            // From editWarehouse.jsp
            if (type.contains("Form")) {
                String tenKho = request.getParameter("TenKho");
                String diaChi = request.getParameter("DiaChi");

                KhohangEntity kho = new KhohangEntity();
                Map<String, String> errors = new HashMap<String, String>();

                if (maKho.trim().equals("")) {
                    errors.put("MaKho", "Không được để trống");
                } else if (specialCharsPattern.matcher(maKho).find()) {
                    errors.put("MaKho", "Phải không chứa ký tự đặc biệt !@#$%^&*()?\"':{}|<>");
                }

                if (specialCharsPattern.matcher(tenKho).find()) {
                    errors.put("TenKho", "Phải không chứa ký tự đặc biệt !@#$%^&*()?\"':{}|<>");
                }

                if (specialCharsPattern.matcher(diaChi).find()) {
                    errors.put("DiaChi", "Phải không chứa ký tự đặc biệt !@#$%^&*()?\"':{}|<>");
                }

                String status = "";
                if (errors.isEmpty()) {
                    kho.setMaKho(maKho);
                    kho.setTenKho(tenKho);
                    kho.setDiaChi(diaChi);

                    if (type.contains("Add")) {
                        status = ManageWarehouseDao.AddWarehouse(kho);
                        if (status == null) {
                            doPost(request, response); // Forward to manageWarehouse.jsp
                            return;
                        }
                    }
                    else if (type.contains("Edit")) {
                        status = ManageWarehouseDao.EditWarehouse(kho);
                        if (status == null) {
                            doPost(request, response); // Forward to manageWarehouse.jsp
                            return;
                        }
                    }
                }
                // Found errors
                request.setAttribute("error", errors);
                request.setAttribute("Type", type);
                request.setAttribute("MaKho", maKho);
                request.setAttribute("TenKho", tenKho);
                request.setAttribute("DiaChi", diaChi);
                request.setAttribute("status", status);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/editWarehouse.jsp");
                dispatcher.forward(request, response);
                return;
            }
            // From manageWarehouse.jsp
            else {
                request.setAttribute("Type", type + "Form");
                request.setAttribute("MaKho", maKho);
                if (type.contains("Edit")) {
                    KhohangEntity kh = ManageWarehouseDao.GetWarehouse(maKho);
                    if (kh != null) {
                        request.setAttribute("TenKho", kh.getTenKho());
                        request.setAttribute("DiaChi", kh.getDiaChi());
                    }
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/editWarehouse.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        if (type.contains("Item")) {
            /*if (type.contains("Delete")) {
                String status = ManageWarehouseDao.DeleteItemInWarehouse(maSanPham);
                request.setAttribute("DeleteStatus", status);
                doPost(request, response);
                return;
            }*/

            // From editItem.jsp
            if (type.contains("Form")) {
                //String tenSanPham = request.getParameter("TenSanPham");
                //String donViTinh = request.getParameter("DonViTinh");
                //String donGiaStr = request.getParameter("DonGia");
                String soLuongStr = request.getParameter("SoLuong");
                //String anhMinhHoa = request.getParameter("AnhMinhHoa");
                //String maNhomStr = request.getParameter("MaNhom");
                //String moTa = request.getParameter("MoTa");

                //SanphamEntity sp = new SanphamEntity();
                Map<String, String> errors = new HashMap<String, String>();

                /*if (maSanPham.trim().equals("")) {
                    errors.put("MaSanPham", "Không được để trống");
                } else if (specialCharsPattern.matcher(maSanPham).find()) {
                    errors.put("MaSanPham", "Phải không chứa ký tự đặc biệt !@#$%^&*()?\"':{}|<>");
                }

                if (tenSanPham.trim().equals("")) {
                    errors.put("TenSanPham", "Không được để trống");
                } else if (specialCharsPattern.matcher(tenSanPham).find()) {
                    errors.put("TenSanPham", "Phải không chứa ký tự đặc biệt !@#$%^&*()?\"':{}|<>");
                }

                if (donViTinh.trim().equals("")) {
                    errors.put("DonViTinh", "Không được để trống");
                } else if (specialCharsPattern.matcher(donViTinh).find()) {
                    errors.put("DonViTinh", "Phải không chứa ký tự đặc biệt !@#$%^&*()?\"':{}|<>");
                }

                int donGia = 0;
                try {
                    donGia = Integer.parseInt(donGiaStr);
                    if (donGia <= 0) {
                        throw new NumberFormatException("Phải là số nguyên dương (lớn hơn 0)");
                    }
                }
                catch (Exception e) {
                    errors.put("DonGia", e.getMessage());
                }*/

                int soLuong = 0;
                try {
                    soLuong = Integer.parseInt(soLuongStr);
                    if (soLuong < 0) {
                        throw new NumberFormatException("Phải là số nguyên (lớn hơn hoặc bằng 0)");
                    }
                }
                catch (Exception e) {
                    errors.put("SoLuong", e.getMessage());
                }

                /*if (specialCharsPattern.matcher(anhMinhHoa).find()) {
                    errors.put("AnhMinhHoa", "Phải không chứa ký tự đặc biệt !@#$%^&*()?\"':{}|<>");
                }

                short maNhom = 0;
                try {
                    maNhom = Short.parseShort(maNhomStr);
                }
                catch (Exception e) {
                    errors.put("MaNhom", e.getMessage());
                }

                if (specialCharsPattern.matcher(moTa).find()) {
                    errors.put("AnhMinhHoa", "Phải không chứa ký tự đặc biệt !@#$%^&*()?\"':{}|<>");
                }*/

                String status = "";
                if (errors.isEmpty()) {
                    SanphamEntity sp = ManageWarehouseDao.GetItem(maSanPham);
                    sp.setMaKho(maKho);
                    //sp.setTenSanPham(tenSanPham);
                    //sp.setDonViTinh(donViTinh);
                    //sp.setDonGia(donGia);
                    sp.setSoLuong(soLuong);
                    //sp.setAnhMinhHoa(anhMinhHoa);
                    //sp.setMaNhom(maNhom);
                    //sp.setMoTa(moTa);

                    if (type.contains("Add")) {
                        status = ManageWarehouseDao.AddItemInWarehouse(sp);
                        if (status == null) {
                            doPost(request, response); // Forward to manageWarehouse.jsp
                            return;
                        }
                    }
                    else if (type.contains("Edit")) {
                        status = ManageWarehouseDao.EditItemInWarehouse(sp);
                        if (status == null) {
                            doPost(request, response); // Forward to manageWarehouse.jsp
                            return;
                        }
                    }
                }
                // Found errors
                request.setAttribute("error", errors);
                request.setAttribute("Type", type);
                request.setAttribute("MaKho", maKho);
                request.setAttribute("MaSanPham", maSanPham);
                //request.setAttribute("TenSanPham", tenSanPham);
                //request.setAttribute("DonViTinh", donViTinh);
                //request.setAttribute("DonGia", donGiaStr);
                request.setAttribute("SoLuong", soLuongStr);
                //request.setAttribute("AnhMinhHoa", anhMinhHoa);
                //request.setAttribute("MaNhom", maNhomStr);
                //request.setAttribute("MoTa", moTa);
                request.setAttribute("status", status);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/editItem.jsp");
                dispatcher.forward(request, response);
                return;
            }
            // From manageWarehouse.jsp
            else {
                request.setAttribute("Type", type + "Form");
                request.setAttribute("MaKho", maKho);
                if (type.contains("Edit")) {
                    SanphamEntity sp = ManageWarehouseDao.GetItem(maSanPham);
                    if (sp != null) {
                        request.setAttribute("MaSanPham", sp.getMaSanPham());
                        //request.setAttribute("TenSanPham", sp.getTenSanPham());
                        //request.setAttribute("DonViTinh", sp.getDonViTinh());
                        //request.setAttribute("DonGia", sp.getDonGia());
                        request.setAttribute("SoLuong", sp.getSoLuong());
                        //request.setAttribute("AnhMinhHoa", sp.getAnhMinhHoa());
                        //request.setAttribute("MaNhom", sp.getMaNhom());
                        //request.setAttribute("MoTa", sp.getMoTa());
                    }
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/editItem.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }

        response.getWriter().append(String.format("Type: %s\nMaKho: %s\nMaSanPham: %s", type, maKho, maSanPham));
        //String url = "/manageWarehouse.jsp";
        //RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        //dispatcher.forward(request, response);
    }
}