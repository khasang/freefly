package io.khasang.freefly.controller;

import io.khasang.freefly.dto.OrderDTO;
import io.khasang.freefly.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(OrderController.ROOT)
public class OrderController {

    public static final String ROOT = "/order";
    public static final String ADD = "/add";
    public static final String GET_BY_ID = "/get/{id}";
    public static final String UPDATE = "/update";
    public static final String DELETE_BY_ID = "/delete/{id}";
    public static final String GET_ALL = "/get/all";

    @Autowired
    OrderService orderService;

    @ResponseBody
    @RequestMapping(value = OrderController.ADD, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO){
        return orderService.addOrder(orderDTO);
    }

    @ResponseBody
    @RequestMapping(value = OrderController.GET_BY_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderDTO getOrderDTOById(@PathVariable (name = "id") long id){
        return orderService.getOrderDTOById(id);
    }

    @ResponseBody
    @RequestMapping(value = OrderController.UPDATE, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO){
        return orderService.updateOrder(orderDTO);
    }

    @ResponseBody
    @RequestMapping(value = OrderController.DELETE_BY_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderDTO deleteOrderById(@PathVariable (name = "id") long id){
        return orderService.deleteOrderById(id);
    }

    @ResponseBody
    @RequestMapping(value = OrderController.GET_ALL, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<OrderDTO> getAllOrdersDTO(){
        return orderService.getAllOrdersDTO();
    }

}
