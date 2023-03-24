package org.example.dao;

import org.example.models.UserProfile;


import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> get (String login);
    List<T> getAll();
    boolean save(T t);
    boolean update(T t);
    boolean delete(T t);
}
