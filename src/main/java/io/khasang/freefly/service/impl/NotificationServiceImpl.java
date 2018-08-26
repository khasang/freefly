package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.NotificationDao;
import io.khasang.freefly.entity.Notification;
import io.khasang.freefly.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationDao notificationDao;

    @Override
    public Notification addNotification(Notification notification) {
        return notificationDao.create(notification);
    }

    @Override
    public Notification getNotificationById(long id) {
        return notificationDao.getById(id);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationDao.getList();
    }

    @Override
    public Notification updateNotification(Notification notification) {

        notificationDao.update(notification);
        return notification;
    }

    @Override
    public void deleteNotification(Notification notification) {
        notificationDao.delete(notification);
    }
}
