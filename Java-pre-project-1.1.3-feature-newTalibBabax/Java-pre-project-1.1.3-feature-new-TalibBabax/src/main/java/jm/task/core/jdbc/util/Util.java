package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.service.ServiceRegistry;



 public final class Util {
    private  static  final String URL = "jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false" ;
    private  static  final String USER= "root";
    private  static  final String PASSWORD =  "rfrfzrhfcjnf93";

        private static StandardServiceRegistry registry;
        private static SessionFactory sessionFactory;

        public static SessionFactory getSessionFactory() {

            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration()
                            .setProperty("hibernate.connection.url",URL)
                            .setProperty("hibernate.connection.username",USER)
                            .setProperty("hibernate.connection.password", PASSWORD)
                            .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                            .addAnnotatedClass(User.class);


                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();
                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                } catch (Exception e) {
                    e.printStackTrace();

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






















