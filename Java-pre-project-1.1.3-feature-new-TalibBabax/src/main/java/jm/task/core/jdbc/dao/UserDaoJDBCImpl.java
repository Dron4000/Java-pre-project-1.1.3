package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Transaction;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }


    public void createUsersTable() {

        String createTable = "CREATE TABLE IF NOT EXISTS users (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(45) NOT NULL,\n" +
                "  `lastName` varchar(45) NOT NULL,\n" +
                "  `age` tinyint NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(createTable)) {
            preparedStatement.executeUpdate();
            System.out.println("Табица создана");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void dropUsersTable() {
        String SQLdropUsersTable = "DROP TABLE IF EXISTS users ";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SQLdropUsersTable)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String SQLsaveUser = "INSERT INTO users (name,lastName,age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SQLsaveUser)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void removeUserById(long id) {
        String SQLremoveUserById = "DELETE FROM users WHERE id =?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SQLremoveUserById)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String SQLgetAllUsers = "SELECT * FROM users";
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SQLgetAllUsers)) {
            resultSet = preparedStatement.executeQuery(SQLgetAllUsers);
            while (resultSet.next()) {
                long id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = (byte) resultSet.getInt(4);
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);
                users.add(user);
                System.out.println(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQLcleanUsersTable = "DELETE FROM users ";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SQLcleanUsersTable)) {
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}







