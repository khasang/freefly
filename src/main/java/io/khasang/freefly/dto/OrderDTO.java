package io.khasang.freefly.dto;

import java.time.LocalDate;
import java.util.Objects;

public class OrderDTO {

    private Long id;
    private String description;
    private LocalDate creationDate;
    private LocalDate modificationDate;

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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) &&
                Objects.equals(description, orderDTO.description) &&
                Objects.equals(creationDate, orderDTO.creationDate) &&
                Objects.equals(modificationDate, orderDTO.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, creationDate, modificationDate);
    }
}
