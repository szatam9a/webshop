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
        if (hasUser(user)){
            System.out.println("user already exist");
            return;
        }
        userDao.saveUser(user);
    }

    public boolean hasUser(User user) {
        Optional<User> userToCheck = Optional.of(userDao.findUserByEmail(user.getEmailAddress()));
        if (userToCheck.isPresent()) {
            return true;
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
