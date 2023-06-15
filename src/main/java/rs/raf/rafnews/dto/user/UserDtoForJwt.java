package rs.raf.rafnews.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDtoForJwt {

    private int id;
    private String username;
    private String password;
    private int roleId;

    public UserDtoForJwt(int id, String username, String password, int roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }
}
