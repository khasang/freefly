package io.khasang.freefly.entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "send_time")
    private LocalTime sendTime;
    private boolean delivered;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "notificationList")
    private List<User> userList = new ArrayList<>();

    @ManyToOne
    Trips trips;

    @ManyToOne
    NotificationTypes notificationTypes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalTime sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Trips getTrips() {
        return trips;
    }

    public void setTrips(Trips trips) {
        this.trips = trips;
    }

    public NotificationTypes getNotificationTypes() {
        return notificationTypes;
    }

    public void setNotificationTypes(NotificationTypes notificationTypes) {
        this.notificationTypes = notificationTypes;
    }
}