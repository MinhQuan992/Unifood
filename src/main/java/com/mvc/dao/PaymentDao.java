package com.mvc.dao;

import com.mvc.entities.*;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;

public class PaymentDao {

    public static List<DathangEntity> GetDatHang(Integer maGio)
    {
        List<DathangEntity> list = null;

        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            list = session.createQuery("SELECT d FROM DathangEntity d WHERE d.maGio=:maGio", DathangEntity.class)
                    .setParameter("maGio", maGio)
                    .getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return list;
    }

    public DathangEntity getPaymentData(String cartCode)
    {
        DathangEntity payment = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query<DathangEntity> query = session.createQuery("SELECT DH FROM DathangEntity DH WHERE DH.maGio=:CartCode");
            query.setParameter("CartCode",cartCode);
            payment = query.uniqueResult();
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

    public boolean UpdateOrderData(DonhangEntity payment)
    {
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(payment);
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
