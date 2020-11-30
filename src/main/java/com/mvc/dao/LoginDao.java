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
                String dbusername = resultSet.getString("TenDangNhap").trim();
                String dbpassword = resultSet.getString("MatKhau").trim();

                if (username.equals(dbusername) && password.equals(dbpassword))
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
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // username = e.getMessage();
        }

        return false;
    }
}
