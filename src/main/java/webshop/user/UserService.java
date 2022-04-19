package webshop.user;

import java.util.Optional;

public class UserService {
    private UserDao userDao;


    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findUserByID(long id) {
        return null;
    }


    public boolean saveUser(String name, String email, long password) {
        User user = new User(name, email, password);
        if (hasUser(user)) {
            System.out.println("the email address is already registered");
            return false;
        }
        userDao.saveUser(user);
        return true;
    }

    public boolean hasUser(User user) {
        Optional<User> userToCheck = (userDao.findUserByEmail(user.getEmailAddress()));
        if (userToCheck.isPresent()) {
            return true;
        }
        return false;
    }

    public User findUserByEmail(String email) {
        Optional<User> user = userDao.findUserByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        throw new IllegalArgumentException("no user");
    }

    public boolean loginUser(User user) {
        Optional<User> emailAccessUser = userDao.findUserByEmail(user.getEmailAddress());
        if (emailAccessUser.isPresent()) {
            if (emailAccessUser.get().login(user)) {
                user.setLoggedIn(true);
                return true;
            }
        }
        return false;
    }
}
