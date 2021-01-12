package com.mvc.dao;

import com.mvc.entities.DathangEntity;
import com.mvc.entities.SanphamEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class OrderDao
{
    public DathangEntity GetOrderData(int cartCode, String itemCode)
    {
        DathangEntity order = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("FROM DathangEntity Orders WHERE " +
                    "Orders.maGio=:CartCode AND Orders.maSanPham=:ItemCode");
            query.setParameter("CartCode", cartCode);
            query.setParameter("ItemCode", itemCode);
            order = (DathangEntity) query.uniqueResult();
            transaction.commit();
        } catch (Exception var9) {
            if (transaction != null) {
                transaction.rollback();
            }
            var9.printStackTrace();
        } finally {
            session.close();
        }
        return order;
    }

    public boolean InsertOrderData(DathangEntity order)
    {
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception var8) {
            if (transaction != null) {
                transaction.rollback();
            }
            var8.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

    public boolean UpdateOrderData(DathangEntity order)
    {
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (Exception var8) {
            if (transaction != null) {
                transaction.rollback();
            }
            var8.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

    public boolean DeleteOrderData(DathangEntity order)
    {
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(order);
            transaction.commit();
        } catch (Exception var8) {
            if (transaction != null) {
                transaction.rollback();
            }
            var8.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }
}
