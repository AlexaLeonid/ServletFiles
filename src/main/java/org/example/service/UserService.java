package org.example.service;

import org.example.dao.UserDAO;
import org.example.models.UserProfile;
import org.example.repository.UserRepository;

public class UserService{

    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public boolean addNewUser(String login, String password, String email){
        if (login != null & login != "" & password != null & password != "" & email != null & email != "") {
            userDAO.save(new UserProfile(login, password, email));
            return true;
        }else{
            return false;
        }
    }


    public UserProfile getUserByLogin(String login){

        return userDAO.getUser(login);
    }
    public boolean isReal(String login, String password){

        return getUserByLogin(login) != null && (userDAO.getUser(login).getPassword().equals(password));
    }

}
