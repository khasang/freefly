package io.khasang.freefly.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    public Employee() {
    }

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    String name;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Car> carList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
