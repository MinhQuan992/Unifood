package com.mvc.dao;

import com.mvc.entities.ViewChiTietDonHangEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ViewOrderDetailDao {
    public List<ViewChiTietDonHangEntity> getOrderDetail(int orderID)
    {
        Transaction transaction = null;
        List<ViewChiTietDonHangEntity> orderDetail = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<ViewChiTietDonHangEntity> query = session.createQuery("FROM ViewChiTietDonHangEntity WHERE maDon=:id");
            query.setParameter("id", orderID);
            orderDetail = query.getResultList();
            transaction.commit();
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return orderDetail;
    }
}
