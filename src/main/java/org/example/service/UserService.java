package org.example.service;

import org.example.dao.UserDAO;
import org.example.models.UserProfile;
import org.example.repository.UserRepository;

import java.io.File;
import java.util.Objects;

public class UserService{

    private final UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public boolean addNewUser(String login, String password, String email){
        if (login != null & !Objects.equals(login, "") & password != null & !Objects.equals(password, "") & email != null & !Objects.equals(email, "")) {
            UserProfile userProfile = getUserByLogin(login);
            if(userProfile != null){
                if(Objects.equals(userProfile.getPassword(), password)){
                    return false;
                }
            }
            userDAO.save(new UserProfile(login, password, email));
            File file = new File("C:\\Students\\" + login);
            file.mkdir();
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
