package com.mvc.dao;

import com.mvc.entities.SanphamEntity;
import com.mvc.entities.ViewDonHangEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SanPhamDAO {
    public List<SanphamEntity> getAllProduct()
    {
        Transaction transaction = null;
        List<SanphamEntity> listOfProduct = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<SanphamEntity> query = session.createQuery("FROM SanphamEntity ");
            listOfProduct = query.getResultList();
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
        return listOfProduct;
    }

    public boolean themSP(SanphamEntity sp)
    {
        Transaction transaction = null;
        Session session= HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            session.save(sp);
            transaction.commit();
            System.out.println("Thêm thành công.");
            return true;
        }
        catch (Exception e)
        {
            if(transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
    }

    public boolean suaSP(SanphamEntity sp)
    {
        Transaction transaction = null;
        Session session= HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            session.update(sp);
            transaction.commit();
            System.out.println();
            return true;
        }
        catch (Exception e)
        {
            if (transaction!=null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
    }

}
