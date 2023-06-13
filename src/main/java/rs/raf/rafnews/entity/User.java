package rs.raf.rafnews.entity;

import lombok.*;

@Getter
@Setter
public class User {

    private int id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private boolean status;
    private Role role;

    public User(int id, String username, String password, String firstName, String lastName, boolean status, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstName;
        this.lastname = lastName;
        this.status = status;
        this.role = role;
    }
}
