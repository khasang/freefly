package io.khasang.freefly.dto;

public class NotificationTypesDTO {

    private Long id;

    private String nameType;
    private String description;
    private String testNotification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTestNotification() {
        return testNotification;
    }

    public void setTestNotification(String testNotification) {
        this.testNotification = testNotification;
    }
}
