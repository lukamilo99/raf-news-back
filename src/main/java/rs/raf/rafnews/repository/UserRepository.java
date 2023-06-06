package rs.raf.rafnews.repository;

import rs.raf.rafnews.entity.User;

public interface UserRepository extends Repository<User>{

    User findUserByUsername(String username);
}
