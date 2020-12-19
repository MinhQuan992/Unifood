package com.mvc.dao;

import com.mvc.entities.ViewDonHangEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ViewOrderDao {
    public List<ViewDonHangEntity> getAllOrder(String userID)
    {
        Transaction transaction = null;
        List<ViewDonHangEntity> listOfOrder = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<ViewDonHangEntity> query = session.createQuery("FROM ViewDonHangEntity WHERE maNguoiDung=:userID");
            query.setParameter("userID", userID);
            listOfOrder = query.getResultList();
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
        return listOfOrder;
    }
}
