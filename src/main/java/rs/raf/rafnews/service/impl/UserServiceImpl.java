package rs.raf.rafnews.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import rs.raf.rafnews.database.criteria.Criteria;
import rs.raf.rafnews.entity.User;
import rs.raf.rafnews.repository.UserRepository;
import rs.raf.rafnews.service.UserService;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllPagination(int pageNumber, int pageSize) {
        return userRepository.findAllPagination(pageNumber, pageSize);
    }

    @Override
    public List<User> findByCriteria(Criteria criteria) {
        return userRepository.findByCriteria(criteria);
    }

    @Override
    public User insert(User object) {
        object.setPassword(hashPassword(object.getPassword()));
        return userRepository.insert(object);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User update(User object) {
        return userRepository.update(object);
    }

    @Override
    public int count() {
        return userRepository.count();
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findUserByUsername(username);

        if(user != null) {
            if (hashPassword(password).equals(user.getPassword())) {
                Date issuedAt = new Date();
                Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000);

                Algorithm algorithm = Algorithm.HMAC256("secret");

                return JWT.create()
                        .withIssuedAt(issuedAt)
                        .withExpiresAt(expiresAt)
                        .withSubject(user.getUsername())
                        .withClaim("role", user.getRole().getName())
                        .sign(algorithm);
            }
            else return null;
        }
        else return null;
    }

    @Override
    public boolean isAuthorized(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String username = jwt.getSubject();
        User user = this.userRepository.findUserByUsername(username);
        return user != null;
    }

    @Override
    public String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedPassword) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
