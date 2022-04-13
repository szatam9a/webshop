package webshop.user;

import java.util.Optional;

public class UserService {
    private UserDao userDao;
//    private boolean loggedIn;

    public User findUserByID(long id) {
        return null;
    }

    public void saveUser(String name, String email, String password) {
        User user = new User(name, email, password.hashCode());
        userDao.saveUser(user);
    }

    public boolean hasUser(User user) {
        Optional<User> userToCheck = Optional.of(userDao.findUserByEmail(user.getEmailAddress()));
        if (userToCheck.isPresent()) {
            throw new IllegalArgumentException("the email address is already taken");
        }
        return false;
    }


//    public boolean loginUser(User user) {
//        Optional<User> emailAccessUser = Optional.of(userDao.findUserByEmail(user.getEmailAddress()));
//        if (emailAccessUser.isPresent()) {
//            if (user.equals(emailAccessUser)) {
//            }
//        }
//    }
}
