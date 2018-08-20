package io.khasang.freefly.dto;

import org.springframework.stereotype.Component;

import java.sql.Time;

@Component
public class NotificationDTO {

    private long id;
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
