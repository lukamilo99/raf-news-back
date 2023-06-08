package rs.raf.rafnews.repository;

import rs.raf.rafnews.database.criteria.Criteria;
import rs.raf.rafnews.dto.user.RequestUserDto;
import rs.raf.rafnews.dto.user.ResponseUserDto;
import rs.raf.rafnews.entity.User;

import java.util.List;

public interface UserRepository {

    User findUserByUsername(String username);
    int insert(RequestUserDto object);
    void deleteById(int id);
    void update(RequestUserDto object);
    ResponseUserDto findById(int id);
    List<ResponseUserDto> findAll();
    List<User> findAllPagination(int pageNumber, int pageSize);
    List<User> findByCriteria(Criteria criteria);
    void activateUserById(int id);
    void deactivateUserById(int id);
}
