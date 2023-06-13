package rs.raf.rafnews.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUserDto {

    private int id;
    private String firstname;
    private String lastname;
    private String username;
    private boolean status;
    private int role;

    public ResponseUserDto(int id, String firstname, String lastname, String username, int role, boolean status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.role = role;
        this.status = status;
    }
}
