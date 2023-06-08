package rs.raf.rafnews.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tag {

    private int id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
