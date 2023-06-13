package rs.raf.rafnews.repository;

import rs.raf.rafnews.dto.user.RequestUserDto;
import rs.raf.rafnews.dto.user.ResponseUserDto;
import rs.raf.rafnews.entity.User;

import java.util.List;

public interface UserRepository {


    int insert(RequestUserDto object);
    void deleteById(int id);
    void update(RequestUserDto object);
    ResponseUserDto findById(int id);
    User findByUsername(String username);
    List<ResponseUserDto> findAll();
    void activateUserById(int id);
    void deactivateUserById(int id);
}
