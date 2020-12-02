package com.mvc.dao;
import java.sql.*;
import java.util.*;

import com.mvc.bean.GroupItemBean;
import com.mvc.bean.ItemBean;

public class GroupItemDao {
    private String dbURL = "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD";
    private PreparedStatement statement = null;
    private Connection connection = null;

    public boolean getGroupItemData(GroupItemBean group)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            PreparedStatement statement = connection.prepareStatement("select  * from dbo.NhomSanPham where MaNhom = ?");
            statement.setInt(1,group.getGroupCode());

            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                String groupName = result.getNString("TenNhom");
                group.setGroupName(groupName);
            }
            statement.close();
            connection.close();
            System.out.println("Access to Group OK!!");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Access to Group FAIL!!");
            return false;
        }
    }

    public List<ItemBean> getAllGroupItem(GroupItemBean group)
    {
        List <ItemBean> itemList = new ArrayList<ItemBean>();
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            PreparedStatement statement = connection.prepareStatement("select * from dbo.SanPham where MaNhom = ?");
            statement.setInt(1,group.getGroupCode());
            ResultSet result = statement.executeQuery();
            ItemBean item = new ItemBean();
            ItemDao itemDao = new ItemDao();
            while (result.next())
            {
                String itemCode = result.getString("MaSanPham");
                item.setItemCode(itemCode);
                itemDao.GetItemData(item);
                itemList.add(item);
            }
            statement.close();
            connection.close();
            System.out.println("Access to Group OK!!");
            return itemList;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Access to Group FAIL!!");
            return itemList;
        }
    }

    public boolean insertGroupItemData(GroupItemBean group)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            PreparedStatement statement = connection.prepareStatement("insert into dbo.NhomSanPham values (?,?)");
            statement.setInt(1,group.getGroupCode());
            statement.setNString(2, group.getGroupName());
            statement.executeQuery();
            statement.close();
            connection.close();
            System.out.println("Access to Group OK!!");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Access to Group FAIL!!");
            return false;
        }
    }

    public boolean updateGroupItemData(GroupItemBean group)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            PreparedStatement statement = connection.prepareStatement("UPDATE dbo.NhomSanPham set TenNhom = ? where MaNhom = ?");
            statement.setNString(1,group.getGroupName());
            statement.setInt(2,group.getGroupCode());
            statement.executeQuery();
            statement.close();
            connection.close();
            System.out.println("Access to Group OK!!");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Access to Group FAIL!!");
            return false;
        }
    }
}
