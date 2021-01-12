//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mvc.dao;

import com.mvc.entities.AnkemEntity;
import com.mvc.entities.NhomsanphamEntity;
import com.mvc.entities.SanphamEntity;
import com.mvc.utility.HibernateUtility;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class ItemDao {
    public ItemDao() {
    }

    public SanphamEntity GetItemData(String itemCode) {
        SanphamEntity item = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            System.out.println("This session was created completely");
            transaction = session.beginTransaction();
            Query<?> query = session.createQuery("FROM SanphamEntity Item WHERE Item.maSanPham=:itemcode");
            query.setParameter("itemcode", itemCode);
            item = (SanphamEntity)query.uniqueResult();
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

        return item;
    }

    public boolean updateItemQuantity(SanphamEntity item, int Amount) {
        Transaction transaction = null;
        int newQuantity = item.getSoLuong() - Amount;
        if (newQuantity < 0) {
            return false;
        } else {
            Session session = HibernateUtility.getSessionFactory().openSession();

            try {
                transaction = session.beginTransaction();
                item.setSoLuong(newQuantity);
                session.update(item);
                transaction.commit();
            } catch (Exception var10) {
                if (transaction != null) {
                    transaction.rollback();
                }

                var10.printStackTrace();
            } finally {
                session.close();
            }

            return true;
        }
    }

    public boolean updateItemData(SanphamEntity item) {
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.update(item);
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

    public boolean insertItemData(SanphamEntity item) {
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(item);
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

    public boolean deleteItemData(SanphamEntity item) {
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.delete(item);
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

    public List<SanphamEntity> getListDependenceItems(SanphamEntity mainItem, NhomsanphamEntity group) {
        List<SanphamEntity> listItem = null;
        SanphamEntity item = null;
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            listItem = new ArrayList();
            DependDao denpendDao = new DependDao();
            List<AnkemEntity> dependList = denpendDao.getDependItemData(mainItem.getMaSanPham());
            Iterator var9 = dependList.iterator();

            while(var9.hasNext()) {
                AnkemEntity ak = (AnkemEntity)var9.next();
                SanphamEntity sp = this.GetItemData(ak.getMaDoAnKem());
                if (sp.getMaNhom() == group.getMaNhom()) {
                    listItem.add(sp);
                }
            }

            transaction.commit();
        } catch (Exception var15) {
            if (transaction != null) {
                transaction.rollback();
            }

            var15.printStackTrace();
        } finally {
            session.close();
        }

        return listItem;
    }
}
