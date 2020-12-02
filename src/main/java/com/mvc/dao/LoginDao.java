package com.mvc.dao;

import com.mvc.bean.LoginBean;
import com.mvc.bean.UserBean;
import java.sql.*;

public class LoginDao {
    public boolean authorizeLogin(LoginBean loginBean, UserBean userBean)
    {
        String email = loginBean.getEmail();
        String password = loginBean.getPassword();

        String dbURL = "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD";
        PreparedStatement statement = null;
        Connection connection = null;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);

            statement = connection.prepareStatement("SELECT * FROM NGUOIDUNG WHERE Email = ? AND MatKhau = ?");
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                String dbemail = resultSet.getString("Email");
                String dbpassword = resultSet.getString("MatKhau");

                if (email.equals(dbemail) && password.equals(dbpassword))
                {
                    userBean.setUserID(resultSet.getString("MaNguoiDung"));
                    userBean.setFullName(resultSet.getNString("HoVaTen"));
                    userBean.setGender(resultSet.getNString("GioiTinh"));
                    userBean.setBirthdate(resultSet.getDate("NgaySinh"));
                    userBean.setAddress(resultSet.getNString("DiaChi"));
                    userBean.setPhone(resultSet.getString("DienThoai"));
                    userBean.setEmail(resultSet.getString("Email"));
                    userBean.setPassword(resultSet.getString("MatKhau"));

                    statement.close();
                    connection.close();

                    return true;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }
}
