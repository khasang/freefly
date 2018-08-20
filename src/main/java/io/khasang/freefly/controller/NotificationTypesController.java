package io.khasang.freefly.controller;

import io.khasang.freefly.entity.Cat;
import io.khasang.freefly.entity.NotificationTypes;
import io.khasang.freefly.service.CatService;
import io.khasang.freefly.service.NotificationTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notification_types")
public class NotificationTypesController {

    private final NotificationTypesService notificationTypesService;

    @Autowired
    public NotificationTypesController(NotificationTypesService notificationTypesService) {
        this.notificationTypesService = notificationTypesService;
    }

    // localhost:8080/cat/add?name=Barsik&description=Angry&colorID
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public NotificationTypes addNotificationTypes(@RequestBody NotificationTypes notificationTypes) {
        return notificationTypesService.addNotificationTypes(notificationTypes);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public NotificationTypes getNotificationTypesById(@PathVariable(value = "id") String id) {
        return notificationTypesService.getNotificationTypesById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<NotificationTypes> getAllNotificationTypes() {
        return notificationTypesService.getAllNotificationTypes();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public NotificationTypes updateNotificationTypesById(@RequestBody NotificationTypes notificationTypes) {
        return notificationTypesService.updateNotificationTypes(notificationTypes);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public void deleteNotificationTypesById(@PathVariable(value = "id") String id) {
        notificationTypesService.deleteNotificationTypesById(Long.parseLong(id));
    }
}
