package rs.raf.rafnews.service;

import rs.raf.rafnews.entity.User;

public interface UserService extends Service<User>{

    String login(String username, String password);
    boolean isAuthorized(String token);
    String hashPassword(String password);
}
