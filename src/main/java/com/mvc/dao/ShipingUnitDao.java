package com.mvc.dao;

import com.mvc.entities.DathangEntity;
import com.mvc.entities.DonvigiaohangEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ShipingUnitDao {
    public List<DonvigiaohangEntity> getAllShippingData()
    {
        List<DonvigiaohangEntity> payment = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query<DonvigiaohangEntity> query = session.createQuery("FROM DonvigiaohangEntity ");
            payment = query.getResultList();
            transaction.commit();
        } catch (Exception var9) {
            if (transaction != null) {
                transaction.rollback();
            }
            var9.printStackTrace();
        } finally {
            session.close();
        }
        return payment;
    }
}
