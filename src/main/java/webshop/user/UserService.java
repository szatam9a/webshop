package webshop.user;

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
}
