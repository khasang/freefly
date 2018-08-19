package io.khasang.freefly.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String description;

    @Column(name = "color_id")
    private Integer colorId;

    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    private List<CatWoman> catWomans = new ArrayList<>();

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public List<CatWoman> getCatWomans() {
        return catWomans;
    }

    public void setCatWomans(List<CatWoman> catWomans) {
        this.catWomans = catWomans;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", colorId=" + colorId +
                '}';
    }
}
