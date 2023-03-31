package org.example.dao;


import org.example.models.UserProfile;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class Hibernate {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
              Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
              configuration.addAnnotatedClass(UserProfile.class);

              StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
              sessionFactory = configuration.buildSessionFactory(builder.build());

            }catch (Exception e){
              e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
