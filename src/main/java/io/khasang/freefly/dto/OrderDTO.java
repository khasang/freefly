package io.khasang.freefly.dto;

import java.time.LocalDate;
import java.util.Objects;

public class OrderDTO {

    private Long id;
    private String description;
    private LocalDate created;
    private LocalDate updated;

    public OrderDTO() {
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

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) &&
                Objects.equals(description, orderDTO.description) &&
                Objects.equals(created, orderDTO.created) &&
                Objects.equals(updated, orderDTO.updated);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, description, created, updated);
    }
}
