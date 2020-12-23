package com.mvc.utility;

import com.mvc.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtility {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
                settings.put(Environment.URL, "jdbc:sqlserver://localhost;integratedSecurity=True;databaseName=UNIFOOD");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "validate");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(NguoidungEntity.class);
                //configuration.addAnnotatedClass(ViewDonHangEntity.class);
                //configuration.addAnnotatedClass(ViewChiTietDonHangEntity.class);
                configuration.addAnnotatedClass(KhohangEntity.class);
                configuration.addAnnotatedClass(SanphamEntity.class);
                configuration.addAnnotatedClass(DathangEntity.class);
                configuration.addAnnotatedClass(MagiamgiaEntity.class);
                configuration.addAnnotatedClass(DonhangEntity.class);
                configuration.addAnnotatedClass(DonvigiaohangEntity.class);
                // TODO: Add 'configuration.addAnnotatedClass(...)' foremost to avoid
                //  org.hibernate.hql.internal.ast.QuerySyntaxException: ... is not mapped

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
