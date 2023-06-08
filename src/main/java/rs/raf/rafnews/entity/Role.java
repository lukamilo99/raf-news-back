package rs.raf.rafnews.entity;

import lombok.Data;

@Data
public class Role {

    private int id;
    private String name;

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
