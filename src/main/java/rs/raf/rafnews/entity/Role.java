package rs.raf.rafnews.entity;

import lombok.Data;

@Data
public class Role {

    private int id;
    private String name;

    public Role() {
        this.name = "CONTENT_CREATOR";
    }
}
