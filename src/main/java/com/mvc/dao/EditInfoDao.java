package com.mvc.dao;

import com.mvc.bean.LoginBean;
import com.mvc.bean.UserBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditInfoDao {
    public boolean authorizeEditInfo(UserBean userBean)
    {
        String userId = userBean.getUserID();

        String dbURL = "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD";
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);

            statement = connection.prepareStatement("SELECT * FROM NGUOIDUNG WHERE MaNguoiDung = ?");
            statement.setString(1, userId.trim());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                userBean.setUserID(resultSet.getString("MaNguoiDung"));
                userBean.setFullName(resultSet.getNString("HoVaTen"));
                userBean.setGender(resultSet.getNString("GioiTinh"));
                userBean.setBirthDate(resultSet.getDate("NgaySinh"));
                userBean.setAddress(resultSet.getNString("DiaChi"));
                userBean.setPhone(resultSet.getString("DienThoai"));
                userBean.setEmail(resultSet.getString("Email").trim());
                userBean.setUsername(resultSet.getString("TenDangNhap").trim());
                userBean.setPassword(resultSet.getString("MatKhau").trim());

                statement.close();
                connection.close();

                return true;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            userId = e.getMessage();
            return false;
        }

        return true;
    }
}
