package io.khasang.freefly.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "sended_time")
    private java.sql.Time sendedTime;
    private boolean delivered;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Time getSendedTime() {
        return sendedTime;
    }

    public void setSendedTime(Time sendedTime) {
        this.sendedTime = sendedTime;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

}
