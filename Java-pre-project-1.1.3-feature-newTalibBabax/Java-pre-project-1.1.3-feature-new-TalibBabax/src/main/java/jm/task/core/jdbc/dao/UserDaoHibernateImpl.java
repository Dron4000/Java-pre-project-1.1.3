package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            String SQLcreateTable = "CREATE TABLE IF NOT EXISTS users(\n" +
                    "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) NOT NULL,\n" +
                    "  `lastName` varchar(45) NOT NULL,\n" +
                    "  `age` tinyint NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";

            session.createSQLQuery(SQLcreateTable).executeUpdate();
            System.out.println(" Таблица создана");
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String SQLdropUsersTable = "TRUNCATE TABLE users  ";
            session.createSQLQuery(SQLdropUsersTable).addEntity(User.class).executeUpdate();
            System.out.println(" Таблица удалена");
            transaction.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            User user = new User(name, lastName, age);
            session.save(user);
            System.out.println("Пользователь " + user + " добавлен");
            transaction.commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            System.out.println("Был удален " + user);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from  User", User.class).list();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return list;
    }


    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            final List<User> list = session.createQuery("from User ", User.class).getResultList();
            for (Object obj : list) {
                session.delete(obj);
            }
            transaction.commit();
            System.out.println("Таблица очищена");

        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        }


    }
}
