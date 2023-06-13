package rs.raf.rafnews.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import rs.raf.rafnews.dto.user.RequestUserDto;
import rs.raf.rafnews.dto.user.ResponseUserDto;
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
    public ResponseUserDto findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<ResponseUserDto> findAll() {
        return userRepository.findAll();
    }

    @Override
    public int insert(RequestUserDto object) {
        object.setPassword(hashPassword(object.getPassword()));
        return userRepository.insert(object);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(RequestUserDto object) {
        userRepository.update(object);
    }

    @Override
    public void activateUserById(int id) {
        userRepository.activateUserById(id);
    }

    @Override
    public void deactivateUserById(int id) {
        userRepository.deactivateUserById(id);
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username);
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
                        .withClaim("user", user.getId())
                        .sign(algorithm);
            }
            else return null;
        }
        else return null;
    }

    @Override
    public boolean isAuthorized(String token, String path) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String username = jwt.getSubject();
        String role = jwt.getClaim("role").asString();

        if(path.contains("public")) {
            User user = this.userRepository.findByUsername(username);
            return user != null;
        }
        else if(path.contains("user")) {
            if(role.equals("ADMIN")) {
                User user = this.userRepository.findByUsername(username);
                return user != null;
            }
            else {
                return false;
            }
        }
        else return true;
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
