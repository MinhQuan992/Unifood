package com.mvc.dao;

import java.sql.*;

import com.mvc.bean.GroupItemBean;
import com.mvc.bean.ItemBean;
import com.mvc.bean.ListItemBean;

import javax.mail.FetchProfile;
import javax.persistence.criteria.CriteriaBuilder;

public class ItemDao {
    private String dbURL = "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD";
    private PreparedStatement statement = null;
    private Connection connection = null;

    public boolean GetItemData(ItemBean item)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            PreparedStatement statement = connection.prepareStatement("select  * from dbo.SanPham where MaSanPham = ?");
            statement.setString(1,item.getItemCode());

            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                String itemName = result.getNString("TenSanPham");
                String itemPrice = result.getString("DonGia");
                String maximumQuantity = result.getString("SoLuong");
                String itemImage = result.getString("AnhMinhHoa");
                String itemStorage = result.getString("MaKho");
                String itemUnit = result.getNString("DonViTinh");
                String itemGroup = result.getString("MaNhom");
                String itemDescription = result.getNString("MoTa");

                item.setItemImage(itemImage);
                item.setItemName(itemName);
                item.setItemPrice(Integer.parseInt(itemPrice));
                item.setMaximumQuantity(Integer.parseInt(maximumQuantity));
                item.setItemGroup(Integer.parseInt(itemGroup));
                item.setItemStorage(itemStorage);
                item.setItemUnit(itemUnit);
                item.setItemDescription(itemDescription);
                System.out.println(itemDescription);
            }
            statement.close();
            connection.close();
            System.out.println("Access to Product OK!!");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Access to Product FAIL!!");
            return false;
        }
    }

    public boolean updateItemQuantity(ItemBean item)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            PreparedStatement statement =   connection.prepareStatement("UPDATE dbo.SanPham" +
                                                                            "SET Soluong = ?" +
                                                                            "WHERE MaSanPham = ?");
            int newQuantity = item.getMaximumQuantity() - item.getItemQuantity();
            statement.setString(1,Integer.toString(newQuantity));
            statement.setString(2,item.getItemCode());

            statement.executeQuery();
            statement.close();
            connection.close();

            System.out.println("Access to Product OK!!");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Access to Product FAIL!!");
            return false;
        }
    }

    public boolean updateItemData(ItemBean item)
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE dbo.SanPham " +
                    "SET Soluong = ?," +
                        "TenSanPham = ?," +
                        "DonGia = ?," +
                        "AnhMinhHoa = ?,"+
                        "DonViTinh= ?," +
                        "MaKho = ?," +
                        "MaNhom = ?," +
                        "MoTa = ?" +
                    "WHERE MaSanPham = ?");
            statement.setString(1,Integer.toString(item.getMaximumQuantity()));
            statement.setNString(2,item.getItemName());
            statement.setString(3,Integer.toString(item.getItemPrice()));
            statement.setString(4,item.getItemImage());
            statement.setNString(5,item.getItemUnit());
            statement.setString(6,item.getItemStorage());
            statement.setString(7,Integer.toString(item.getItemGroup()));
            statement.setNString(8,item.getItemDescription());
            statement.setString(9,item.getItemCode());

            statement.executeQuery();
            statement.close();
            connection.close();

            System.out.println("Access to Product OK!!");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Access to Product FAIL!!");
            return false;
        }
    }

    public boolean insertItemData(ItemBean item)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO SANPHAM VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1,item.getItemCode());
            statement.setNString(2,item.getItemName());
            statement.setNString(3,item.getItemUnit());
            statement.setString(4,Integer.toString(item.getItemPrice()));
            statement.setString(5,Integer.toString(item.getMaximumQuantity()));
            statement.setString(6,item.getItemImage());
            statement.setString(7,Integer.toString(item.getItemGroup()));
            statement.setString(8,item.getItemStorage());
            statement.setNString(9,item.getItemDescription());

            statement.executeQuery();
            statement.close();
            connection.close();

            System.out.println("Access to Product OK!!");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Access to Product FAIL!!");
            return false;
        }
    }

    public boolean deleteItemData(ItemBean item)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM dbo.SanPham WHERE MaSanPham = ?");
            statement.setString(1,item.getItemCode());
            statement.executeQuery();
            statement.close();
            connection.close();

            System.out.println("Access to Product OK!!");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Access to Product FAIL!!");
            return false;
        }
    }

    public Boolean getListDependenceItems(ItemBean mainItem, GroupItemBean group, ListItemBean listItem)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);
            PreparedStatement statement = connection.prepareStatement("select ANKEM.MaDoAnKem " +
                    "from SANPHAM, NHOMSANPHAM, ANKEM " +
                    "where dbo.ANKEM.MaMonAnChinh = ? " +
                            "and MaSanPham = ANKEM.MaDoAnKem " +
                            "and NHOMSANPHAM.MaNhom = dbo.SANPHAM.MaNhom " +
                            "and NHOMSANPHAM.MaNhom = ?");
            statement.setString(1,mainItem.getItemCode());
            statement.setInt(2,group.getGroupCode());
            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                String itemCode = result.getString("MaDoAnKem");
                ItemBean item = new ItemBean(itemCode);
                this.GetItemData(item);
                listItem.addItemToList(item);
            }
            statement.close();
            connection.close();
            System.out.println("Access to Product OK!!");
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Access to Product FAIL!!");
            return false;
        }
    }
}
