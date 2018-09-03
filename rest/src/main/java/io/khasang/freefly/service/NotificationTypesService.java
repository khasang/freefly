package io.khasang.freefly.service;

import io.khasang.freefly.entity.NotificationTypes;

import java.util.List;

public interface NotificationTypesService {

    /**
     * method for add notificationTypes
     *
     * @param notificationTypes -  new notificationTypes for creation
     * @return created notificationTypes
     */
    NotificationTypes addNotificationTypes(NotificationTypes notificationTypes);

    /**
     * method for getting notificationTypes by specific id
     *
     * @param id - notificationTypes' id
     * @return notificationTypes by id
     */
    NotificationTypes getNotificationTypesById(long id);

    /**
     * method for getting all notificationTypes
     *
     * @return notificationType's list
     */
    List< NotificationTypes> getAllNotificationTypes();

    /**
     * method for updating notificationTypes
     * @param notificationTypes for updating
     * @return updated notificationTypes
     */
    NotificationTypes updateNotificationTypes(NotificationTypes notificationTypes);

    /**
     * method for deleting notificationTypes by id
     * @param id for deleting
     */
    NotificationTypes deleteNotificationTypesById(long id);
}
