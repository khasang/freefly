package io.khasang.freefly.controller;

import io.khasang.freefly.entity.OrderStatus;
import io.khasang.freefly.service.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orderStatus")
public class OrderStatusController {
    private final OrderStatusService orderStatusService;

    @Autowired
    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public OrderStatus addOrderStatus(@RequestBody OrderStatus orderStatus) {
        return orderStatusService.addOrderStatus(orderStatus);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public OrderStatus getOrderStatusById(@PathVariable(value = "id") String id) {
        return orderStatusService.getOrderStatusById(Long.parseLong(id));
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusService.getAllOrderStatus();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    @ResponseBody
    public OrderStatus updateOrderStatus(@RequestBody OrderStatus orderStatus) {
        return orderStatusService.updateOrderStatus(orderStatus);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    @ResponseBody
    public OrderStatus deleteOrderStatusById(@PathVariable(value = "id") String id) {
        return orderStatusService.deleteOrderStatusById(Long.parseLong(id));
    }
}