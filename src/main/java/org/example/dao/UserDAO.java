package org.example.dao;


import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.models.UserProfile;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.List;

public class UserDAO implements DAO<UserProfile>{
    Connection connection;
    public UserDAO(){
       connection = UsersDB.getConnection();
    }
    @Override
    public UserProfile getUser(String login) {
        Session session = Hibernate.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        UserProfile user = session.byNaturalId(UserProfile.class).using("login", login).load();

        transaction.commit();
        session.close();

        return user;
    }

    @Override
    public List<UserProfile> getAll() {
        Session session = Hibernate.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserProfile> cq = cb.createQuery(UserProfile.class);
        Root<UserProfile> rootEntry = cq.from(UserProfile.class);
        CriteriaQuery<UserProfile> all = cq.select(rootEntry);
        TypedQuery<UserProfile> allQuery = session.createQuery(all);

        transaction.commit();
        session.close();

        return allQuery.getResultList();
    }

    @Override
    public boolean save(UserProfile user) {
        boolean saved = true;
        Session session = Hibernate.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.persist(user);
        }catch (PersistentObjectException e){
            saved = false;
        }

        transaction.commit();
        session.close();

        return saved;
    }

    @Override
    public boolean update(UserProfile user) {
        boolean updated = true;

        Session session = Hibernate.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.merge(user);
        }catch (PersistentObjectException e){
            updated = false;
        }

        transaction.commit();
        session.close();

        return updated;
    }

    @Override
    public boolean delete(UserProfile user) {
        boolean deleted = true;
        Session session = Hibernate.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.remove(user);
        }catch (PersistentObjectException e){
            deleted = false;
        }

        transaction.commit();
        session.close();

        return deleted;
    }
}
