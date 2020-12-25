//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mvc.dao;

import com.mvc.entities.NhomsanphamEntity;
import com.mvc.entities.SanphamEntity;
import com.mvc.utility.HibernateUtility;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class GroupItemDao {
    public GroupItemDao() {
    }

    public NhomsanphamEntity getGroupItemData(short groupId) {
        NhomsanphamEntity group = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("FROM NhomsanphamEntity Group WHERE Group.maNhom=:GroupID");
            query.setParameter("GroupID", groupId);
            group = (NhomsanphamEntity)query.uniqueResult();
            transaction.commit();
        } catch (Exception var9) {
            if (transaction != null) {
                transaction.rollback();
            }

            var9.printStackTrace();
        } finally {
            session.close();
        }

        return group;
    }

    public List<SanphamEntity> getAllGroupItem(NhomsanphamEntity group) {
        List<SanphamEntity> listItem = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query<SanphamEntity> query = session.createQuery("SELECT Item FROM SanphamEntity Item WHERE Item.maNhom=:GroupID");
            query.setParameter("GroupID", group.getMaNhom());
            listItem = query.getResultList();
            transaction.commit();
        } catch (Exception var9) {
            if (transaction != null) {
                transaction.rollback();
            }
            var9.printStackTrace();
        } finally {
            session.close();
        }

        return listItem;
    }

    public boolean insertGroupItemData(NhomsanphamEntity group) {
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(group);
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

    public boolean updateGroupItemData(NhomsanphamEntity group) {
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.update(group);
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

    public boolean deleteGroupItemData(NhomsanphamEntity group) {
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.delete(group);
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
