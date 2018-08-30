package io.khasang.freefly.service.impl;

import io.khasang.freefly.dao.NotificationTypesDao;
import io.khasang.freefly.entity.NotificationTypes;
import io.khasang.freefly.service.NotificationTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationTypesServiceImpl implements NotificationTypesService {

    @Autowired
    private NotificationTypesDao notificationTypesDao;


    @Override
    public NotificationTypes addNotificationTypes(NotificationTypes notificationTypes) {
        return notificationTypesDao.create(notificationTypes);
    }

    @Override
    public NotificationTypes getNotificationTypesById(long id) {
        return notificationTypesDao.getById(id);
    }

    @Override
    public List<NotificationTypes> getAllNotificationTypes() {
        return notificationTypesDao.getList();
    }

    @Override
    public NotificationTypes updateNotificationTypes(NotificationTypes notificationTypes) {
        notificationTypesDao.update(notificationTypes);
        return notificationTypes;
    }

    @Override
    public NotificationTypes deleteNotificationTypesById(long id) {
        return notificationTypesDao.delete(getNotificationTypesById(id));
    }
}
