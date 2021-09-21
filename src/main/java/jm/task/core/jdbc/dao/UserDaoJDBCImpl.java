package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public static void main(String[] args) {
    }

    public void createUsersTable() {

        PreparedStatement preparedStatement = null;
        String createTable = "CREATE TABLE IF NOT EXISTS new_fack_table (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(45) NOT NULL,\n" +
                "  `lastName` varchar(45) NOT NULL,\n" +
                "  `age` tinyint NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";

        try {
            preparedStatement = getConnection().prepareStatement(createTable);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null && getConnection() != null) {
                try {
                    preparedStatement.close();
                    getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void dropUsersTable() {
        PreparedStatement preparedStatement = null;
        String SQLdropUsersTable = "DROP TABLE IF EXISTS new_fack_table ";
        try {
            preparedStatement = getConnection().prepareStatement(SQLdropUsersTable);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null && getConnection() != null) {
                try {
                    preparedStatement.close();
                    getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String SQLsaveUser = "INSERT INTO new_fack_table (name,lastName,age)VALUES(?,?,?) ";
        try {
            preparedStatement = getConnection().prepareStatement(SQLsaveUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null && getConnection() != null) {
                try {
                    preparedStatement.close();
                    getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        String SQLremoveUserById = "DELETE FROM new_fack_table WHERE id =?";
        try {
            preparedStatement = getConnection().prepareStatement(SQLremoveUserById);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null && getConnection() != null) {
                try {
                    preparedStatement.close();
                    getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String SQLgetAllUsers = "SELECT * FROM new_fack_table";
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(SQLgetAllUsers);
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
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQLcleanUsersTable = "DELETE FROM new_fack_table ";// TRUNCATE TABLE new_fack_table - не смогу откатить.
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(SQLcleanUsersTable);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null && getConnection() != null) {
                try {
                    preparedStatement.close();
                    getConnection().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        }

    }
}
