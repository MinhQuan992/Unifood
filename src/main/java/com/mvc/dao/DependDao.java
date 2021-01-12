//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mvc.dao;

import com.mvc.entities.AnkemEntity;
import com.mvc.utility.HibernateUtility;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DependDao
{
    public DependDao() {
    }

    public List<AnkemEntity> getDependItemData(String itemCode) {
        List<AnkemEntity> list = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            Query<AnkemEntity> query = session.createQuery("FROM AnkemEntity AK WHERE  AK.maMonAnChinh=:ItemCode");
            query.setParameter("ItemCode", itemCode);
            list = query.getResultList();
            System.out.println("Depend Item List was loaded completely: " + itemCode);
            transaction.commit();
        } catch (Exception var9) {
            System.out.println("Depend Item List was loaded failed: " + itemCode);
            if (transaction != null) {
                transaction.rollback();
            }

            var9.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }
}
