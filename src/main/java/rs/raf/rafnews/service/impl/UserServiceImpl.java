package rs.raf.rafnews.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import rs.raf.rafnews.dto.user.RequestUserDto;
import rs.raf.rafnews.dto.user.ResponseUserDto;
import rs.raf.rafnews.entity.Role;
import rs.raf.rafnews.entity.User;
import rs.raf.rafnews.repository.RoleRepository;
import rs.raf.rafnews.repository.UserRepository;
import rs.raf.rafnews.service.UserService;

import javax.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;
    @Inject
    private RoleRepository roleRepository;

    @Override
    public ResponseUserDto findById(int id) {
        User user = userRepository.findById(id);
        return new ResponseUserDto(user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getUsername(),
                user.getRole().getId(),
                user.isStatus());
    }

    @Override
    public List<ResponseUserDto> findAll() {
        List<ResponseUserDto> userDtoList = new ArrayList<>();
        for (User user: userRepository.findAll()) {
            ResponseUserDto userDto = new ResponseUserDto(user.getId(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getUsername(),
                    user.getRole().getId(),
                    user.isStatus());
            userDtoList.add(userDto);
        }
        return userDtoList;
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
        Role role = roleRepository.findRoleById(user.getRole().getId());
        if (hashPassword(password).equals(user.getPassword())) {
            Date issuedAt = new Date();
            Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000);
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuedAt(issuedAt)
                    .withExpiresAt(expiresAt)
                    .withSubject(user.getUsername())
                    .withClaim("role", role.getName())
                    .withClaim("user", user.getId())
                    .sign(algorithm);
        } else return null;
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
