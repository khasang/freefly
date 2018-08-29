package io.khasang.freefly.service;

import io.khasang.freefly.entity.OrderStatus;

import java.util.List;

public interface OrderStatusService {

    /**
     * method for add orderStatus
     *
     * @param orderStatus - new orderStatus for creation
     * @return created orderStatus
     */
    OrderStatus addOrderStatus(OrderStatus orderStatus);

    /**
     * method for getting orderStatus by specific id
     *
     * @param id - orderStatus's id
     * @return orderStatus by id
     */
    OrderStatus getOrderStatusById(long id);

    /**
     * method for getting all orderStatus
     *
     * @return orderStatus's list
     */
    List<OrderStatus> getAllOrderStatus();

    /**
     * method for update orderStatus
     *
     * @return orderStatus's list
     */
    OrderStatus updateOrderStatus(OrderStatus orderStatus);

    /**
     * method for delete orderStatus by specific id
     *
     * @param id for deleting orderStatus
     */
    OrderStatus deleteOrderStatusById(long id);
}

