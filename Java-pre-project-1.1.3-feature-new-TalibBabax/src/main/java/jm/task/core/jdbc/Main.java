package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        String name = "Andrey";
        System.out.println(" пользователь добавлен успешно " + name);

        name = "Dron";
        userService.saveUser(name, "5000", (byte) 55);
        System.out.println(" пользователь добавлен успешно " + name);

        name = "Vitalik";
        userService.saveUser(name, "Vitalikov", (byte) 21);
        System.out.println(" пользователь добавлен успешно " + name);

        name = "Alex";
        userService.saveUser(name, "Pytin", (byte) 43);
        System.out.println(" пользователь добавлен успешно " + name);

        List<User> list = userService.getAllUsers();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);

            System.out.println(user);
        }


        userService.removeUserById(2);


        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.HibernateUtil.close();



    }

}

