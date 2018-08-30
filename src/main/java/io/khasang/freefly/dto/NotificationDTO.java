package io.khasang.freefly.dto;

import org.springframework.stereotype.Component;
import java.time.LocalTime;

@Component
public class NotificationDTO {

    private long id;
    private LocalTime sendTime;
    private boolean delivered;

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
}
