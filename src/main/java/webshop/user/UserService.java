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
            return false;
        }
        userDao.saveUser(user);
        return true;
    }

    public boolean hasUser(User user) {
        User userToCheck = (userDao.findUserByEmail(user.getEmailAddress()));
        if (userToCheck != null) {
            return true;
        }
        return false;
    }
    public User findUserByEmail(String email){
        User user = userDao.findUserByEmail(email);
        if (user!= null){
            return user;
        }
        throw  new IllegalArgumentException("no user");
    }

    public boolean loginUser(User user) {
        User emailAccessUser = userDao.findUserByEmail(user.getEmailAddress());
        if (emailAccessUser != null) {
            if (emailAccessUser.login(user)) {
                user.setLoggedIn(true);
                return true;
            }
        }
        return false;
    }

}
