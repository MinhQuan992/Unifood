package com.mvc.dao;

import com.mvc.bean.UserBean;
import java.sql.*;

public class UserDao {
    private String dbURL = "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD";
    private PreparedStatement statement = null;
    private Connection connection = null;

    public UserBean findUser(String identifiedString, String method)
    {
        UserBean userBean = new UserBean();
        String sqlString = null;

        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);

            if (method.equals("ByUsername"))
            {
                sqlString = "SELECT * FROM NGUOIDUNG WHERE TenDangNhap = ?";
            }
            else if (method.equals("ByUserID"))
            {
                sqlString = "SELECT * FROM NGUOIDUNG WHERE MaNguoiDung = ?";
            }
            else if (method.equals("ByEmail"))
            {
                sqlString = "SELECT * FROM NGUOIDUNG WHERE Email = ?";
            }

            statement = connection.prepareStatement(sqlString);
            statement.setString(1, identifiedString);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                userBean.setUserID(resultSet.getString("MaNguoiDung"));
                userBean.setFullName(resultSet.getNString("HoVaTen"));
                userBean.setGender(resultSet.getNString("GioiTinh"));
                userBean.setBirthDate(resultSet.getDate("NgaySinh"));
                userBean.setAddress(resultSet.getNString("DiaChi"));
                userBean.setPhone(resultSet.getString("DienThoai"));
                userBean.setEmail(resultSet.getString("Email"));
                userBean.setUsername(resultSet.getString("TenDangNhap"));
                userBean.setPassword(resultSet.getString("MatKhau"));
            }

            statement.close();
            connection.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return userBean;
    }

    public void updateUser(UserBean userBean)
    {
        String userID = userBean.getUserID();
        String address = userBean.getAddress();
        String phone = userBean.getPhone();
        String email = userBean.getEmail();
        String username = userBean.getUsername();
        String password = userBean.getPassword();

        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            statement = connection.prepareStatement("UPDATE NGUOIDUNG SET DiaChi = ?, DienThoai = ?, Email = ?, TenDangNhap = ?, MatKhau = ? WHERE MaNguoiDung = ?");
            statement.setString(1, address);
            statement.setString(2, phone);
            statement.setString(3, email);
            statement.setString(4, username);
            statement.setString(5, password);
            statement.setString(6, userID);
            statement.executeUpdate();

            statement.close();
            connection.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
