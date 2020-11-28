package com.mvc.dao;

import com.mvc.bean.LoginBean;
import com.mvc.bean.UserBean;
import java.sql.*;

public class LoginDao {
    public boolean authorizeLogin(LoginBean loginBean, UserBean userBean)
    {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();

        String dbURL = "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD";

        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);

            statement = connection.prepareStatement("SELECT * FROM NGUOIDUNG WHERE TenDangNhap = ? AND MatKhau = ?");
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                String dbusername = resultSet.getString("TenDangNhap");
                String dbpassword = resultSet.getString("MatKhau");

                if (username.equals(dbusername) && password.equals(dbpassword))
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

                    statement.close();
                    connection.close();

                    System.out.println("Connection OK!");

                    return true;
                }
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            System.out.println("Connection not OK!");
        }

        return false;
    }
}
