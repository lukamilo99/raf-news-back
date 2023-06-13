package rs.raf.rafnews.service;


import rs.raf.rafnews.dto.user.RequestUserDto;
import rs.raf.rafnews.dto.user.ResponseUserDto;

import java.util.List;

public interface UserService {

    int insert(RequestUserDto object);
    void deleteById(int id);
    void update(RequestUserDto object);
    ResponseUserDto findById(int id);
    List<ResponseUserDto> findAll();
    void activateUserById(int id);
    void deactivateUserById(int id);
    String login(String username, String password);
    boolean isAuthorized(String token, String path);
    String hashPassword(String password);
}
