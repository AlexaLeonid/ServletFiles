package org.example.service;

import org.example.models.UserProfile;
import org.example.repository.UserRepository;

public class UserService{

    private final UserRepository userRepository;


    public UserService() {
        userRepository = new UserRepository();
    }

    public boolean addNewUser(String login, String password, String email){
        if (login != null & login != "" & password != null & password != "" & email != null & email != "") {
            userRepository.save(login, new UserProfile(login, password, email));
            return true;
        }else{
            return false;
        }
    }


    public UserProfile getUserByLogin(String login){
        return userRepository.getUser(login);
    }
    public boolean isReal(String login, String password){

        if(getUserByLogin(login) != null) {
            return (userRepository.getUser(login).getPassword().equals(password));
        }
        return false;
    }

}
