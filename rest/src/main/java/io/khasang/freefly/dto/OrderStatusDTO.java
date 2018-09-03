package io.khasang.freefly.dto;

public class OrderStatusDTO {
    private Long id;
    private String description;

    public OrderStatusDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}