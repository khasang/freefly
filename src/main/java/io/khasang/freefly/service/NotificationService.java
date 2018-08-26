package io.khasang.freefly.service;

import io.khasang.freefly.entity.Notification;
import java.util.List;

public interface NotificationService {
    /**
     * method for add notification
     *
     * @param notification -  new notification for creation
     * @return created notification
     */
    Notification addNotification(Notification notification);

    /**
     * method for getting notification by specific id
     *
     * @param id - notification's id
     * @return notification by id
     */
    Notification getNotificationById(long id);

    /**
     * method for getting all notifications
     *
     * @return notification's list
     */
    List<Notification> getAllNotifications();

    /**
     * method for updating notification
     *
     * @param notification for updating
     * @return updated notification
     */
    Notification updateNotification(Notification notification);

    /**
     * method for deleting notification
     *
     * @param notification for deleting
     */
    void deleteNotification(Notification notification);
}
