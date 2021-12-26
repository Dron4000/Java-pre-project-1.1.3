package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.sql.*;


public class Util {

    static private String connectionURL = "jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false";
    static private String password = "rfrfzrhfcjnf93";
    static private String userName = "root";

    public static void main(String[] args) {
        Util.HibernateUtil.getSessionFactory();


    }


    static public Connection getConnection() {
        Connection connection = null;
        try {
            // реализуйте настройку соеденения с БД

            connection = DriverManager.getConnection(connectionURL, userName, password);
            if (connection.isClosed()) {
                System.out.println("соединение нет");
            }
        } catch (SQLException e) {
            System.out.println("Что пошло не так, соединения нет и  есть исключение (");
            e.printStackTrace();

        }
        return connection;
    }


    public static class HibernateUtil {

        private static StandardServiceRegistry registry;
        private static SessionFactory sessionFactory;

        public static SessionFactory getSessionFactory() {

            if (sessionFactory == null) {
                try {
                    StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                    Map<String, String> settings = new HashMap<>();

                    settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false");
                    settings.put(Environment.USER, "root");
                    settings.put(Environment.PASS, "rfrfzrhfcjnf93");
                    settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLInnoDBDialect");
                    //   settings.put(Environment.SHOW_SQL, "true");

                    registryBuilder.applySettings(settings);

                    registry = registryBuilder.build();

                    MetadataSources sources = new MetadataSources(registry);
                    sources.addAnnotatedClass(User.class);


                    Metadata metadata = sources.getMetadataBuilder().build();

                    sessionFactory = metadata.getSessionFactoryBuilder().build();

                } catch (Exception e) {
                    e.printStackTrace();
                    if (registry != null) {
                        StandardServiceRegistryBuilder.destroy(registry);
                    }
                }

                System.out.println("Хибернет заработал");
            }
            return sessionFactory;
        }

        public static void close() {
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }

    }
}





















