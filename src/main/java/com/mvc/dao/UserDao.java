package com.mvc.dao;

import com.mvc.bean.UserBean;
import java.sql.*;

public class UserDao {
    private final String dbURL = "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD";
    private PreparedStatement statement = null;
    private Connection connection = null;

    public UserBean findUser(String email)
    {
        UserBean userBean = new UserBean();
        String sqlString = null;

        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            sqlString = "SELECT * FROM NGUOIDUNG WHERE Email = ?";
            statement = connection.prepareStatement(sqlString);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                userBean.setUserID(resultSet.getString("MaNguoiDung"));
                userBean.setFullName(resultSet.getNString("HoVaTen"));
                userBean.setGender(resultSet.getNString("GioiTinh"));
                userBean.setBirthdate(resultSet.getDate("NgaySinh"));
                userBean.setAddress(resultSet.getNString("DiaChi"));
                userBean.setPhone(resultSet.getString("DienThoai"));
                userBean.setEmail(resultSet.getString("Email"));
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
        String fullName = userBean.getFullName();
        String gender = userBean.getGender();
        Date birthdate = (Date) userBean.getBirthdate();
        String address = userBean.getAddress();
        String phone = userBean.getPhone();
        String email = userBean.getEmail();
        String password = userBean.getPassword();

        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            statement = connection.prepareStatement("UPDATE NGUOIDUNG SET HoVaTen = ?, GioiTinh = ?, NgaySinh = ?, DiaChi = ?, DienThoai = ?, Email = ?, MatKhau = ? WHERE MaNguoiDung = ?");
            statement.setString(1, fullName);
            statement.setString(2, gender);
            statement.setString(3, String.valueOf(birthdate));
            statement.setString(4, address);
            statement.setString(5, phone);
            statement.setString(6, email);
            statement.setString(7, password);
            statement.setString(8, userID);
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
