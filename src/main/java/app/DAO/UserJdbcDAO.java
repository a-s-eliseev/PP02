package app.DAO;

import app.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserJdbcDAO implements UserDao {

    private Session session;
    private Transaction transaction;
    private List<User> users = null;
    private User user = null;

    public UserJdbcDAO(Session session) {
        this.session = session;
    }

    public void insertUser(User user) {
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public User selectUser(Long id) {
        try {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    public List<User> selectAllUsers() {

        try {
            transaction = session.beginTransaction();
            users = session.createQuery("SELECT u FROM User u").getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    public void deleteUser(Long id) {
        try {
            transaction = session.beginTransaction();
            user = session.get(User.class, id);

            if (user != null) {
                session.delete(user);
            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateUser(User user) {
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
