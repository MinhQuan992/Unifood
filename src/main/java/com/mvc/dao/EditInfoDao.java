package com.mvc.dao;

import com.mvc.bean.LoginBean;
import com.mvc.bean.UserBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class EditInfoDao {
    String dbURL = "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD";

    public boolean authorizeEditInfo(UserBean userBean)
    {
        String userId = userBean.getUserID();

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

    public String updateUserInfo(UserBean userBean)
    {
        String userID = userBean.getUserID();
        String address = userBean.getAddress();
        Date birthDate = userBean.getBirthDate();
        String email = userBean.getEmail();
        String fullName = userBean.getFullName();
        String gender = userBean.getGender();
        String password = userBean.getPassword();
        String phone = userBean.getPhone();
        String username = userBean.getUsername();

        String status = "";
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(dbURL);
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE NGUOIDUNG SET DiaChi = ?, DienThoai = ?, Email = ?, " +
                            "TenDangNhap = ?, MatKhau = ?, HoVaTen = ?, GioiTinh = ?, " +
                            "NgaySinh = ? WHERE MaNguoiDung = ?");
            statement.setString(1, address);
            statement.setString(2, phone);
            statement.setString(3, email);
            statement.setString(4, username);
            statement.setString(5, password);
            statement.setString(6, fullName);
            statement.setString(7, gender);
            statement.setDate(8, new java.sql.Date(birthDate.getTime()));
            statement.setString(9, userID);
            statement.executeUpdate();

            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            status = e.getMessage();
        }
        return status;
    }
}
