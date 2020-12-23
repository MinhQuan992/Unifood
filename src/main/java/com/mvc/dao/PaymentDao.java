package com.mvc.dao;

import com.mvc.entities.DathangEntity;
import com.mvc.entities.DathangEntityPK;
import com.mvc.entities.KhohangEntity;
import com.mvc.entities.SanphamEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
