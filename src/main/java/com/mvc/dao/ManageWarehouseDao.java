package com.mvc.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.mvc.entities.KhohangEntity;
import com.mvc.entities.SanphamEntity;

import java.sql.*;
import java.util.*;

public class ManageWarehouseDao {
    //private String dbURL = "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD";
    //private PreparedStatement statement = null;
    //private Connection connection = null;

    public static List<KhohangEntity> GetWarehouse()
    {
        List<KhohangEntity> list = new ArrayList<KhohangEntity>();

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setIntegratedSecurity(true);
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("UNIFOOD");
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM KHOHANG ORDER BY MaKho");)
        {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                KhohangEntity kho = new KhohangEntity();
                kho.setMaKho(rs.getString("MaKho"));
                kho.setTenKho(rs.getString("TenKho"));
                kho.setDiaChi(rs.getString("DiaChi"));
                list.add(kho);
            }
            return list;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<SanphamEntity> GetItemsInWarehouse(KhohangEntity kh)
    {
        String maKho = kh.getMaKho();
        List<SanphamEntity> list = new ArrayList<SanphamEntity>();

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setIntegratedSecurity(true);
        ds.setServerName("localhost");
        ds.setPortNumber(Integer.parseInt("1433"));
        ds.setDatabaseName("UNIFOOD");
        try (Connection con = ds.getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM SANPHAM WHERE MaKho=? ORDER BY MaSanPham");)
        {
            pstmt.setString(1, maKho);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                SanphamEntity sp = new SanphamEntity();
                sp.setMaSanPham(rs.getString("MaSanPham"));
                sp.setTenSanPham(rs.getString("TenSanPham"));
                sp.setDonViTinh(rs.getString("DonViTinh"));
                sp.setDonGia(rs.getInt("DonGia"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setAnhMinhHoa(rs.getString("AnhMinhHoa"));
                sp.setMaNhom(rs.getShort("MaNhom"));
                sp.setMaKho(rs.getString("MaKho"));
                list.add(sp);
            }
            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
