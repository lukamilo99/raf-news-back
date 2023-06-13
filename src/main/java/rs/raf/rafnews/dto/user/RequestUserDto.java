package rs.raf.rafnews.dto.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequestUserDto {

    private int id;
    @NotNull(message = "Firstname is required")
    @NotEmpty(message = "Firstname is required")
    private String firstname;
    @NotNull(message = "Lastname is required")
    @NotEmpty(message = "Lastname is required")
    private String lastname;
    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    private String username;
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    private String password;
    private int role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
