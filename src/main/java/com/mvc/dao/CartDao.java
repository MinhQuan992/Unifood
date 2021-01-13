package com.mvc.dao;

import com.mvc.entities.DathangEntity;
import com.mvc.entities.GiohangEntity;
import com.mvc.entities.NguoidungEntity;
import com.mvc.entities.SanphamEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    public GiohangEntity GetCartData(NguoidungEntity user)
    {
        GiohangEntity cart = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            System.out.println("This session was created completely");
            transaction = session.beginTransaction();
            Query<GiohangEntity> query = session.createQuery("FROM GiohangEntity Carts WHERE Carts.maNguoiDung=:UserCode " +
                    "ORDER BY Carts.maGio DESC");
            query.setParameter("UserCode",user.getMaNguoiDung());
            cart = query.getResultList().get(0);
            transaction.commit();
        } catch (Exception var9) {
            System.out.println("This session was failed");
            if (transaction != null) {
                transaction.rollback();
            }
            var9.printStackTrace();
        } finally {
            session.close();
        }
        return cart;
    }

    public GiohangEntity GetCartData(int CartCode)
    {
        GiohangEntity cart = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            System.out.println("This session was created completely");
            transaction = session.beginTransaction();
            Query<GiohangEntity> query = session.createQuery("FROM GiohangEntity Carts WHERE Carts.maGio=:CartCode");
            query.setParameter("CartCode",CartCode);
            cart = query.getSingleResult();
            transaction.commit();
        } catch (Exception var9) {
            System.out.println("This session was failed");
            if (transaction != null) {
                transaction.rollback();
            }
            var9.printStackTrace();
        } finally {
            session.close();
        }
        return cart;
    }

    public List<DathangEntity> LoadCartData(int cartCode)
    {
        List<DathangEntity> listOrder = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            //System.out.println("This session was created completely");
            transaction = session.beginTransaction();
            Query<DathangEntity> query = session.createQuery("FROM DathangEntity Oders WHERE Oders.maGio=:CartCode");
            query.setParameter("CartCode", cartCode);
            listOrder = query.getResultList();
            transaction.commit();
        } catch (Exception var9) {
            //System.out.println("This session was failed");
            if (transaction != null) {
                transaction.rollback();
            }
            var9.printStackTrace();
        } finally {
            session.close();
        }
        return listOrder;
    }

    public boolean AddItemToCart(GiohangEntity cart, SanphamEntity item)
    {
        OrderDao orderDao = new OrderDao();
        DathangEntity order = orderDao.GetOrderData(cart.getMaGio(),item.getMaSanPham());
        if (order==null)
        {
            order = new DathangEntity(cart.getMaGio(),item.getMaSanPham());
            if (order.getSoLuong()<99)
                order.setSoLuong(order.getSoLuong()+1);
            order.setDonGia(item.getDonGia());
            return orderDao.InsertOrderData(order);
        }
        if (order.getSoLuong()<99)
            order.setSoLuong(order.getSoLuong()+1);
        order.setDonGia(item.getDonGia());
        return orderDao.UpdateOrderData(order);
    }

    public boolean SubItemFromCart(GiohangEntity cart, SanphamEntity item)
    {
        OrderDao orderDao = new OrderDao();
        DathangEntity order = orderDao.GetOrderData(cart.getMaGio(),item.getMaSanPham());
        if (order==null) return false;
        if (order.getSoLuong()>0)
            order.setSoLuong(order.getSoLuong()-1);
        order.setDonGia(item.getDonGia());
        if (order.getSoLuong()==0) return orderDao.DeleteOrderData(order);
        return orderDao.UpdateOrderData(order);
    }

    public boolean RemoveItemFromCart(GiohangEntity cart, SanphamEntity item)
    {
        OrderDao orderDao = new OrderDao();
        DathangEntity order = orderDao.GetOrderData(cart.getMaGio(),item.getMaSanPham());
        return orderDao.DeleteOrderData(order);
    }

    public boolean RemoveItemFromCart(GiohangEntity cart, DathangEntity order)
    {
        order.setMaGio(cart.getMaGio());
        OrderDao orderDao = new OrderDao();
        return orderDao.DeleteOrderData(order);
    }

    public boolean InsertItemToCart(GiohangEntity cart, SanphamEntity item, int quantity, String note)
    {
        OrderDao orderDao = new OrderDao();
        DathangEntity order = orderDao.GetOrderData(cart.getMaGio(),item.getMaSanPham());
        if (order==null) order = new DathangEntity(cart.getMaGio(),item.getMaSanPham());
        else
        {
            order.setSoLuong(order.getSoLuong()+quantity);
            order.setDonGia(item.getDonGia());
            order.setGhiChu(note);
            return orderDao.UpdateOrderData(order);
        }
        order.setSoLuong(quantity);
        order.setDonGia(item.getDonGia());
        order.setGhiChu(note);
        return orderDao.InsertOrderData(order);
    }

    public boolean InsertItemToCart(GiohangEntity cart, DathangEntity order)
    {
        order.setMaGio(cart.getMaGio());
        OrderDao orderDao = new OrderDao();
        return orderDao.InsertOrderData(order);
    }

    public GiohangEntity GetNewCart(NguoidungEntity user)
    {
        GiohangEntity cart = new GiohangEntity();
        Transaction transaction = null;
        List<Integer> listCartCode = null;
        int newCartCode = 0;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            //System.out.println("This session was created completely");
            transaction = session.beginTransaction();
            Query<Integer> query = session.createQuery("SELECT Carts.maGio FROM GiohangEntity Carts");
            listCartCode = query.getResultList();
            for (int cartCode: listCartCode) newCartCode = Integer.max(cartCode,newCartCode);
            transaction.commit();
        } catch (Exception var9) {
            //System.out.println("This session was failed");
            if (transaction != null) {
                transaction.rollback();
            }
            var9.printStackTrace();
        } finally {
            session.close();
        }
        cart.setMaGio(newCartCode+1);
        cart.setMaNguoiDung(user.getMaNguoiDung());
        session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(cart);
            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return cart;
    }
}



