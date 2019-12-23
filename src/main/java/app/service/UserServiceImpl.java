package app.service;

import app.DAO.UserJdbcDAO;
import app.entities.User;
import app.util.DBHelper;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl;
    private SessionFactory sessionFactory;

    private UserServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserServiceImpl getInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl(DBHelper.getSessionFactory());
        }
        return userServiceImpl;
    }

    @Override
    public void newUser(User user) {
        new UserJdbcDAO().insertUser(user);
    }

    @Override
    public List<User> listUser() {
        return new UserJdbcDAO().selectAllUsers();
    }

    @Override
    public User selectUser(Long id) {
        return new UserJdbcDAO().selectUser(id);
    }

    @Override
    public void editUser(User user) {
        new UserJdbcDAO().updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        new UserJdbcDAO().deleteUser(id);
    }
}
