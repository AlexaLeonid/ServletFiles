package org.example.repository;

import org.example.models.UserProfile;

import java.util.HashMap;
import java.util.Map;

public class UserRepository{

    public final static Map<String, UserProfile> loginToProfile = new HashMap<String, UserProfile>();

    public void save(String login, UserProfile userProfile){
        loginToProfile.put(login, userProfile);
    }

    public UserProfile getUser(String login){
        return loginToProfile.get(login);
    }
}
