package org.example.dao;

import org.example.models.UserProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<UserProfile>{
    Connection connection;
    public UserDAO(){
       connection = UsersDB.getConnection();
    }
    @Override
    public Optional<UserProfile> get(String login) {

        if(connection != null){
            String sqlGet = "select * from users where login = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlGet)){
                preparedStatement.setString(1, login);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(resultSet.next()){
                        return Optional.ofNullable(new UserProfile(resultSet.getString("login"),
                                                       resultSet.getString("password"),
                                                       resultSet.getString("email")));
                    }
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    @Override
    public List<UserProfile> getAll() {
        List<UserProfile> users = new ArrayList<>();
        if(connection != null){
            String sqlGet = "select * from users";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlGet)){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()){
                        String loginU = resultSet.getString("login");
                        String passwordU = resultSet.getString("password");
                        String emailU = resultSet.getString("email");
                        users.add(new UserProfile(loginU, passwordU, emailU));
                    }
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public boolean save(UserProfile user) {
        boolean saved = false;
        if(connection != null){
            String sqlGet = "insert into users (login, password, email) values (?, ?, ?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlGet)){
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.executeUpdate();
                saved = true;

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return saved;
    }

    @Override
    public boolean update(UserProfile user) {
        boolean updated = false;
        if(connection != null){
            String sqlGet = "UPDATE users SET login = ?, password = ?, email = ? WHERE login = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlGet)){
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getLogin());
                preparedStatement.executeUpdate();
                updated = true;

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return updated;
    }

    @Override
    public boolean delete(UserProfile user) {
        boolean deleted = false;
        if(connection != null){
            String sqlGet = "DELETE FROM users WHERE login = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sqlGet)){
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.executeUpdate();
                deleted = true;

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return deleted;
    }
}
